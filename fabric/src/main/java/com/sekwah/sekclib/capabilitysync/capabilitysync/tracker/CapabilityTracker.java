package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTracker;

import java.util.ArrayList;
import java.util.List;

public final class CapabilityTracker {
    private final CapabilityEntry entry;
    private final List<SyncTracker> trackers = new ArrayList<>();

    public CapabilityTracker(CapabilityEntry entry) {
        this.entry = entry;
        entry.getSyncEntries().forEach(e -> trackers.add(new SyncTracker(e)));
    }

    public CapabilityEntry getCapabilityEntry() {
        return entry;
    }

    public List<SyncTracker> getSyncTrackers() {
        return trackers;
    }
}
