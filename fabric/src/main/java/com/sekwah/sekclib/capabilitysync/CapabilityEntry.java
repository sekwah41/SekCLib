package com.sekwah.sekclib.capabilitysync;

import com.sekwah.sekclib.common.capabilitysync.SyncEntry;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public final class CapabilityEntry {
    private final ResourceLocation id;
    private final ComponentKey<? extends Component> component;
    private final Class<?> componentClass;
    private final List<SyncEntry> entries = new ArrayList<>();

    public CapabilityEntry(ResourceLocation id, ComponentKey<? extends Component> component, Class<?> componentClass) {
        this.id = id;
        this.component = component;
        this.componentClass = componentClass;
    }

    public ResourceLocation getId() {
        return id;
    }

    public ComponentKey<? extends Component> getComponent() {
        return component;
    }

    public Class<?> getCapabilityClass() {
        return componentClass;
    }

    public List<SyncEntry> getSyncEntries() {
        return entries;
    }

    public void addSyncEntry(SyncEntry entry) {
        entries.add(entry);
    }
}
