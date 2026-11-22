package com.sekwah.sekclib;

import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerUpdater;
import com.sekwah.sekclib.network.SekCPacketHandler;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public final class FabricClientNetworking implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CapabilitySyncRegistry.initializeRuntime();
        CapabilitySyncRegistry.initializeExtensions();
        ClientPlayNetworking.registerGlobalReceiver(SekCPacketHandler.CAPABILITY_SYNC, (client, handler, buf, sender) -> {
            int entityId = buf.readInt(), capCount = buf.readInt();
            List<DecodedCapability> decoded = new ArrayList<>();
            for (int i = 0; i < capCount; i++) {
                int capId = buf.readInt(), count = buf.readInt();
                var entry = CapabilitySyncRegistry.getValue(capId);
                List<DecodedValue> values = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    int trackerId = buf.readByte();
                    boolean hasData = buf.readBoolean();
                    var sync = entry.getSyncEntries().get(trackerId);
                    values.add(new DecodedValue(sync, hasData ? sync.getSerializer().decode(buf) : null));
                }
                decoded.add(new DecodedCapability(entry, values));
            }
            client.execute(() -> apply(client, entityId, decoded));
        });
        ClientPlayNetworking.registerGlobalReceiver(SekCPacketHandler.VELOCITY, (client, handler, buf, sender) -> {
            int x = buf.readShort(), y = buf.readShort(), z = buf.readShort();
            client.execute(() -> {
                if (client.player != null) {
                    if (y > 0) client.player.setOnGround(false);
                    client.player.setDeltaMovement(x / 8000.0, y / 8000.0, z / 8000.0);
                }
            });
        });
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void apply(Minecraft client, int entityId, List<DecodedCapability> data) {
        if (client.level == null) return;
        Entity entity = client.level.getEntity(entityId);
        if (!(entity instanceof LivingEntity living)) return;
        for (DecodedCapability cap : data) {
            Object component = ((ComponentKey) cap.entry.getComponent()).getNullable(living);
            if (component == null) continue;
            for (DecodedValue value : cap.values)
                try {
                    Object old = value.entry.getGetter().invoke(component);
                    if (value.entry.getSerializer() instanceof SyncTrackerUpdater updater)
                        updater.updateTracker(value.value, old);
                    value.entry.getSetter().invoke(component, value.value);
                } catch (Throwable t) {
                    System.err.println("[SekCLib] Could not apply synced component field: " + t);
                }
        }
    }

    private record DecodedCapability(com.sekwah.sekclib.capabilitysync.CapabilityEntry entry,
                                     List<DecodedValue> values) {
    }

    private record DecodedValue(com.sekwah.sekclib.common.capabilitysync.SyncEntry entry, Object value) {
    }
}
