package com.sekwah.sekclib.capabilitysync;

import com.sekwah.sekclib.common.capabilitysync.SyncEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapabilityEntry {
    private final Capability<?> capability;
    private final Class clazz;

    private final List<SyncEntry> syncEntries = new ArrayList<>();

    private final Map<String, SyncEntry> syncEntryHashMap = new HashMap<>();

    public CapabilityEntry(ResourceLocation resourceSyncName, Capability<?> capability, Class clazz) {
        this.capability = capability;
        this.clazz = clazz;
    }

    public Capability<?> getCapability() {
        return capability;
    }

    public List<SyncEntry> getSyncEntries() {
        return this.syncEntries;
    }

    public SyncEntry getSyncEntryByName(String key) {
        return this.syncEntryHashMap.get(key);
    }

    public void addSyncEntry(SyncEntry entry) {
        if(!this.syncEntryHashMap.containsKey(entry.getName())) {
            this.syncEntries.add(entry);
            this.syncEntryHashMap.put(entry.getName(), entry);
        }
    }

    public Class getCapabilityClass() {
        return clazz;
    }
}
