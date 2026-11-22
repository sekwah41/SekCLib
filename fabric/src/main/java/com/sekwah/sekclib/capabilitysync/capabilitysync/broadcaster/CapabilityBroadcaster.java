package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTracker;
import com.sekwah.sekclib.network.SekCPacketHandler;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.*;

public final class CapabilityBroadcaster {
    private static final Map<LivingEntity, List<CapabilityTracker>> TRACKERS = Collections.synchronizedMap(new WeakHashMap<>());

    private CapabilityBroadcaster() {
    }

    private static List<CapabilityTracker> trackers(LivingEntity entity) {
        return TRACKERS.computeIfAbsent(entity, e -> {
            List<CapabilityTracker> result = new ArrayList<>();
            for (CapabilityEntry cap : CapabilitySyncRegistry.entries()) {
                Object component = ((ComponentKey) cap.getComponent()).getNullable(e);
                if (component != null && component.getClass() == cap.getCapabilityClass())
                    result.add(new CapabilityTracker(cap));
            }
            return result;
        });
    }

    private static List<CapabilityInfo> collect(LivingEntity entity, boolean all) {
        List<CapabilityInfo> result = new ArrayList<>();
        for (CapabilityTracker tracker : trackers(entity)) {
            CapabilityInfo info = new CapabilityInfo(CapabilitySyncRegistry.getId(tracker.getCapabilityEntry()), tracker.getCapabilityEntry());
            Object component = tracker.getCapabilityEntry().getComponent().getNullable(entity);
            for (SyncTracker sync : tracker.getSyncTrackers())
                if (all || sync.isMarkedForSend()) {
                    if (all && component != null) {
                        try {
                            sync.captureCurrent(component);
                        } catch (Throwable t) {
                            System.err.println("[SekCLib] Failed to capture sync tracker for a full resync: " + t);
                        }
                    }
                    if (sync.getSyncEntry().isSyncGlobally()) info.changedEntries.add(sync);
                    else info.changedPrivateEntries.add(sync);
                }
            if (!info.changedEntries.isEmpty() || !info.changedPrivateEntries.isEmpty()) result.add(info);
        }
        return result;
    }

    public static void checkCapData(LivingEntity entity) {
        for (CapabilityTracker tracker : trackers(entity)) {
            Object component = tracker.getCapabilityEntry().getComponent().getNullable(entity);
            if (component == null) continue;
            for (SyncTracker sync : tracker.getSyncTrackers())
                try {
                    sync.tick(component);
                } catch (Throwable t) {
                    System.err.println("[SekCLib] Failed to update sync tracker: " + t);
                }
        }
        broadcastCapChanges(entity, false);
    }

    public static void broadcastCapChanges(LivingEntity entity, boolean sendAll) {
        List<CapabilityInfo> data = collect(entity, sendAll);
        if (data.isEmpty()) return;
        if (entity instanceof ServerPlayer self) SekCPacketHandler.sendToPlayer(entity, data, true, self);
        SekCPacketHandler.sendToTracking(entity, data, false);
    }

    public static void broadcastCapToPlayer(LivingEntity entity, ServerPlayer player) {
        List<CapabilityInfo> data = collect(entity, true);
        if (!data.isEmpty()) SekCPacketHandler.sendToPlayer(entity, data, false, player);
    }
}
