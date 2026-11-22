package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityBroadcaster;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.common.SekCLibCommon;
import com.sekwah.sekclib.common.capabilitysync.SyncEntry;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTracker;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;

public final class SyncDataCapabilityHandler {
    public static final EntityCapability<ISyncData, Void> SYNC_DATA = EntityCapability.createVoid(
            Identifier.fromNamespaceAndPath(SekCLibCommon.MOD_ID, "sync_data"), ISyncData.class);
    private static final Map<Entity, SyncData> SYNC_DATA_BY_ENTITY = new HashMap<>();

    private SyncDataCapabilityHandler() {
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        for (EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE) {
            registerForEntityType(event, entityType);
        }
    }

    private static <E extends Entity> void registerForEntityType(RegisterCapabilitiesEvent event, EntityType<E> entityType) {
        event.registerEntity(SYNC_DATA, entityType, (entity, context) -> getOrCreate(entity));
    }

    private static synchronized SyncData getOrCreate(Entity entity) {
        return SYNC_DATA_BY_ENTITY.computeIfAbsent(entity, SyncDataCapabilityHandler::createSyncData);
    }

    private static SyncData createSyncData(Entity entity) {
        SyncData syncData = new SyncData();
        for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
            Object capabilityValue = getCapability(entity, capabilityEntry);
            if (capabilityValue == null || capabilityValue.getClass() != capabilityEntry.getCapabilityClass()) {
                continue;
            }
            CapabilityTracker tracker = new CapabilityTracker(capabilityEntry);
            for (SyncEntry entry : capabilityEntry.getSyncEntries()) {
                tracker.addSyncTracker(new SyncTracker(entry));
            }
            syncData.addCapabilityTracker(tracker);
        }
        return syncData;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object getCapability(Entity entity, CapabilityEntry capabilityEntry) {
        return entity.getCapability((EntityCapability) capabilityEntry.getCapability(), null);
    }

    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CapabilityBroadcaster.checkCapData(serverPlayer);
        }
    }

    public static synchronized void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        SYNC_DATA_BY_ENTITY.remove(event.getEntity());
    }

    public static void onEntityTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity livingEntity && !(livingEntity instanceof Player)) {
            CapabilityBroadcaster.checkCapData(livingEntity);
        }
    }

    public static void dimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CapabilityBroadcaster.broadcastCapChanges(serverPlayer, true);
        }
    }

    public static void playerTracking(PlayerEvent.StartTracking event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer && event.getTarget() instanceof LivingEntity livingEntity) {
            CapabilityBroadcaster.broadcastCapToPlayer(livingEntity, serverPlayer);
        }
    }
}
