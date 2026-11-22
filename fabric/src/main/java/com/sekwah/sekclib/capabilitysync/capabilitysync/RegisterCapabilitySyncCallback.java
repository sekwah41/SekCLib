package com.sekwah.sekclib.capabilitysync.capabilitysync;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface RegisterCapabilitySyncCallback {
    Event<RegisterCapabilitySyncCallback> EVENT = EventFactory.createArrayBacked(RegisterCapabilitySyncCallback.class, listeners -> event -> {
        for (var listener : listeners) listener.register(event);
    });

    void register(RegisterCapabilitySyncEvent event);
}
