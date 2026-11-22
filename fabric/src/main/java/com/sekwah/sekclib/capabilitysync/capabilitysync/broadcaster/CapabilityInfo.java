package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.common.capabilitysync.tracker.ISyncTrackerData;

import java.util.ArrayList;
import java.util.List;

public final class CapabilityInfo {
    public final int capabilityId;
    public final CapabilityEntry capability;
    public final List<ISyncTrackerData> changedEntries = new ArrayList<>();
    public final List<ISyncTrackerData> changedPrivateEntries = new ArrayList<>();

    public CapabilityInfo(int id, CapabilityEntry capability) {
        this.capabilityId = id;
        this.capability = capability;
    }
}
