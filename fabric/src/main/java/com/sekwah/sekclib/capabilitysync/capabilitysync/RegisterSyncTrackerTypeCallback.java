package com.sekwah.sekclib.capabilitysync.capabilitysync;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface RegisterSyncTrackerTypeCallback {
    Event<RegisterSyncTrackerTypeCallback> EVENT = EventFactory.createArrayBacked(RegisterSyncTrackerTypeCallback.class, listeners -> event -> {
        for (var listener : listeners) listener.register(event);
    });

    void register(RegisterSyncTrackerTypeEvent event);
}
