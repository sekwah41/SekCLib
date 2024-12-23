package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SyncData implements ISyncData, ICapabilityProvider {

    private List<CapabilityTracker> capabilityTrackers = new ArrayList<>();

    public void addCapabilityTracker(CapabilityTracker syncTracker) {
        capabilityTrackers.add(syncTracker);
    }

    public List<CapabilityTracker> getCapabilityTrackers() {
        return capabilityTrackers;
    }

    @Override
    public @Nullable Object getCapability(Object o, Object o2) {
        return null;
    }
}
