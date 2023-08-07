package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.SekCLibForge;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import com.sekwah.sekclib.common.SekCLibCommon;
import com.sekwah.sekclib.common.capabilitysync.tracker.implemented.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SekCLibCommon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaseSyncTrackers {
    @SubscribeEvent
    public static void registerSyncTrackerEvent(RegisterSyncTrackerTypeEvent event) {
        // TODO add some form of way of registering the trackers from the common mod
        event.registerSyncTracker(int.class, new IntSyncTracker());
        event.registerSyncTracker(float.class, new FloatSyncTracker());
        event.registerSyncTracker(ResourceLocation.class, new ResourceLocationSyncTracker());
        event.registerSyncTracker(String.class, new StringSyncTracker());
        event.registerSyncTracker(boolean.class, new BoolSyncTracker());
        event.registerSyncTracker(byte.class, new ByteSyncTracker());
        event.registerSyncTracker(Vec3.class, new Vec3SyncTracker());
    }
}
