package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import com.sekwah.sekclib.common.capabilitysync.tracker.implemented.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

public class BaseSyncTrackers {
    public static void registerSyncTrackerEvent(RegisterSyncTrackerTypeEvent event) {
        // TODO add some form of way of registering the trackers from the common mod
        event.registerSyncTracker(int.class, new IntSyncTracker());
        event.registerSyncTracker(float.class, new FloatSyncTracker());
        event.registerSyncTracker(Identifier.class, new IdentifierSyncTracker());
        event.registerSyncTracker(String.class, new StringSyncTracker());
        event.registerSyncTracker(boolean.class, new BoolSyncTracker());
        event.registerSyncTracker(byte.class, new ByteSyncTracker());
        event.registerSyncTracker(Vec3.class, new Vec3SyncTracker());
    }
}
