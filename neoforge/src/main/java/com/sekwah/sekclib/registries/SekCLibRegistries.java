package com.sekwah.sekclib.registries;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Stable runtime IDs for capability sync entries, shared in registration order by clients and servers. */
public final class SekCLibRegistries {
    private static final Map<Identifier, CapabilityEntry> BY_KEY = new LinkedHashMap<>();
    private static final List<CapabilityEntry> BY_ID = new ArrayList<>();

    private SekCLibRegistries() {}

    public static synchronized void register(Identifier key, CapabilityEntry entry) {
        if (BY_KEY.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate capability sync entry: " + key);
        }
        BY_KEY.put(key, entry);
        BY_ID.add(entry);
    }

    public static synchronized CapabilityEntry getValue(int id) {
        return BY_ID.get(id);
    }

    public static synchronized int getId(CapabilityEntry entry) {
        return BY_ID.indexOf(entry);
    }

    public static synchronized List<CapabilityEntry> getValues() {
        return List.copyOf(BY_ID);
    }
}
