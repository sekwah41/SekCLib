package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

public class RegisterSyncTrackerTypeEvent extends Event implements IModBusEvent {

    public void registerSyncTracker(Class clazz, SyncTrackerSerializer syncTracker) {
        CapabilitySyncRegistry.registerSyncTrackerType(clazz, syncTracker);
    }
}
