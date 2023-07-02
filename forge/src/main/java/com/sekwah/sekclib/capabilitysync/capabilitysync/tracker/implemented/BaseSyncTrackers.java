package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SekCLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaseSyncTrackers {
    @SubscribeEvent
    public static void registerSyncTrackerEvent(RegisterSyncTrackerTypeEvent event) {
        event.registerSyncTracker(int.class, new IntSyncTracker());
        event.registerSyncTracker(float.class, new FloatSyncTracker());
        event.registerSyncTracker(ResourceLocation.class, new ResourceLocationSyncTracker());
        event.registerSyncTracker(String.class, new StringSyncTracker());
        event.registerSyncTracker(boolean.class, new BoolSyncTracker());
        event.registerSyncTracker(byte.class, new ByteSyncTracker());
        event.registerSyncTracker(Vec3.class, new Vec3SyncTracker());
    }
}
