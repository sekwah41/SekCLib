package com.sekwah.sekclib.capabilitysync.capabilitysync;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.capabilities.EntityCapability;

public class RegisterCapabilitySyncEvent extends Event implements IModBusEvent {

    /**
     *
     * @param resourceSyncName - This is needed to help sync with the client.
     * @param capability       - The capability to pull the data from.
     * @param clazz            - The class to pull the annotation data from.
     */
    public void registerPlayerCap(Identifier resourceSyncName, EntityCapability<?, ?> capability, Class<?> clazz) {
        CapabilitySyncRegistry.registerPlayerCap(resourceSyncName, capability, clazz);
    }

}
