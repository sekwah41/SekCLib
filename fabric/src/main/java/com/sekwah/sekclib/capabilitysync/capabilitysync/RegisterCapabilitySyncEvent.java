package com.sekwah.sekclib.capabilitysync.capabilitysync;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.resources.ResourceLocation;

public final class RegisterCapabilitySyncEvent {
    public void registerPlayerCap(ResourceLocation id, ComponentKey<? extends Component> component, Class<?> componentClass) {
        CapabilitySyncRegistry.register(id, component, componentClass);
    }

    public void registerComponent(ResourceLocation id, ComponentKey<? extends Component> component, Class<?> componentClass) {
        registerPlayerCap(id, component, componentClass);
    }
}
