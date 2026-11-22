package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;

public final class RegisterSyncTrackerTypeEvent {
    public void registerSyncTracker(Class<?> type, SyncTrackerSerializer<?> serializer) {
        CapabilitySyncRegistry.registerTracker(type, serializer);
    }
}
