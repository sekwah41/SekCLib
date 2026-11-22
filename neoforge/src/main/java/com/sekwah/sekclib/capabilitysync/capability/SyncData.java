package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;

import java.util.ArrayList;
import java.util.List;

public final class SyncData implements ISyncData {
    private final List<CapabilityTracker> capabilityTrackers = new ArrayList<>();

    public void addCapabilityTracker(CapabilityTracker tracker) {
        capabilityTrackers.add(tracker);
    }

    @Override
    public List<CapabilityTracker> getCapabilityTrackers() {
        return capabilityTrackers;
    }
}
