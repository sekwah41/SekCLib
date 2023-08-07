package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.SekCLibForge;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityBroadcaster;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.common.SekCLibCommon;
import com.sekwah.sekclib.common.capabilitysync.SyncEntry;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = SekCLibCommon.MOD_ID)
public class SyncDataCapabilityHandler {

    public static final Capability<ISyncData> SYNC_DATA = CapabilityManager.get(new CapabilityToken<>(){});

    public static void createSyncData(AttachCapabilitiesEvent<Entity> event) {
        SyncData syncData = new SyncData();

        Map<ResourceLocation, ICapabilityProvider> registeredCaps = event.getCapabilities();


        for (Map.Entry<ResourceLocation, ICapabilityProvider> capEntry : registeredCaps.entrySet()) {
            for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
                Class<? extends ICapabilityProvider> capClass = capEntry.getValue().getClass();
                if (capabilityEntry.getCapabilityClass() == capClass) {
                    CapabilityTracker capabilityTracker = new CapabilityTracker(capabilityEntry);
                    for (SyncEntry entry : capabilityEntry.getSyncEntries()) {
                        SyncTracker syncTracker = new SyncTracker(entry);
                        capabilityTracker.addSyncTracker(syncTracker);
                    }
                    syncData.addCapabilityTracker(capabilityTracker);

                    break;
                }
            }
        }

        event.addCapability(new ResourceLocation(SekCLibCommon.MOD_ID, "sync_data"), syncData);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().level().isClientSide()) {
            createSyncData(event);
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        if(event.phase == TickEvent.Phase.END && event.side.isServer() && event.player instanceof ServerPlayer serverPlayer) {
            CapabilityBroadcaster.checkCapData(serverPlayer);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void entityLivingUpdate(LivingEvent.LivingTickEvent event) {
        CapabilityBroadcaster.checkCapData(event.getEntity());
    }

    @SubscribeEvent
    public static void dimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if(event.getEntity() instanceof ServerPlayer serverPlayer) {
            CapabilityBroadcaster.broadcastCapChanges(serverPlayer, true);
        }
    }

    /**
     * Server side event
     * @param event the event
     */
    @SubscribeEvent
    public static void playerTracking(PlayerEvent.StartTracking event) {
        if(event.getEntity() instanceof ServerPlayer serverPlayer && event.getTarget() instanceof LivingEntity livingEntity) {
            CapabilityBroadcaster.broadcastCapToPlayer(livingEntity, serverPlayer);
        }
    }

}
