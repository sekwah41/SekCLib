package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.SekCLibNeoForge;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTracker;
import com.sekwah.sekclib.network.SekCPacketHandler;
import com.sekwah.sekclib.network.s2c.ClientCapabilitySyncPacket;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;

import java.util.ArrayList;
import java.util.List;

public final class CapabilityBroadcaster {
    private CapabilityBroadcaster() {
    }

    public static void checkCapData(LivingEntity entity) {
        var syncData = entity.getCapability(SyncDataCapabilityHandler.SYNC_DATA, null);
        if (syncData != null) {
            for (CapabilityTracker tracker : syncData.getCapabilityTrackers()) {
                Object data = getCapability(entity, tracker.getCapability());
                if (data == null) continue;
                for (SyncTracker syncTracker : tracker.getSyncTrackers()) {
                    try {
                        syncTracker.tick(data);
                    } catch (Throwable e) {
                        SekCLibNeoForge.LOGGER.error("There was a problem updating a sync tracker", e);
                    }
                }
            }
        }
        broadcastCapChanges(entity, false);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object getCapability(Entity entity, EntityCapability capability) {
        return entity.getCapability(capability, null);
    }

    private static List<CapabilityInfo> collectEntries(LivingEntity entity, boolean returnAll) {
        List<CapabilityInfo> result = new ArrayList<>();
        var syncData = entity.getCapability(SyncDataCapabilityHandler.SYNC_DATA, null);
        if (syncData == null) return result;
        for (CapabilityTracker tracker : syncData.getCapabilityTrackers()) {
            CapabilityEntry entry = tracker.getCapabilityEntry();
            CapabilityInfo info = new CapabilityInfo(SekCLibRegistries.getId(entry), entry);
            Object capability = getCapability(entity, tracker.getCapability());
            for (SyncTracker syncTracker : tracker.getSyncTrackers()) {
                if (returnAll && capability != null) {
                    try {
                        syncTracker.captureCurrent(capability);
                    } catch (Throwable e) {
                        SekCLibNeoForge.LOGGER.error("There was a problem capturing a sync tracker for a full resync", e);
                    }
                }
                if (returnAll || syncTracker.isMarkedForSend()) {
                    if (syncTracker.getSyncEntry().isSyncGlobally()) info.changedEntries.add(syncTracker);
                    else info.changedPrivateEntries.add(syncTracker);
                }
            }
            if (!info.changedEntries.isEmpty() || !info.changedPrivateEntries.isEmpty()) result.add(info);
        }
        return result;
    }

    public static void broadcastCapability(Player tracked, Player receiver) {
    }

    public static void broadcastCapChanges(LivingEntity entity, boolean sendAll) {
        List<CapabilityInfo> data = collectEntries(entity, sendAll);
        if (data.isEmpty()) return;
        if (entity instanceof ServerPlayer serverPlayer) {
            ClientCapabilitySyncPacket selfData = new ClientCapabilitySyncPacket(entity, data, true);
            if (!selfData.capabilityData.isEmpty()) SekCPacketHandler.sendToPlayer(selfData, serverPlayer);
        }
        ClientCapabilitySyncPacket others = new ClientCapabilitySyncPacket(entity, data, false);
        if (!others.capabilityData.isEmpty()) SekCPacketHandler.sendToTracking(others, entity);
    }

    public static void broadcastCapToPlayer(LivingEntity entity, ServerPlayer player) {
        List<CapabilityInfo> data = collectEntries(entity, true);
        if (data.isEmpty()) return;
        ClientCapabilitySyncPacket packet = new ClientCapabilitySyncPacket(entity, data, false);
        if (!packet.capabilityData.isEmpty()) SekCPacketHandler.sendToPlayer(packet, player);
    }
}
