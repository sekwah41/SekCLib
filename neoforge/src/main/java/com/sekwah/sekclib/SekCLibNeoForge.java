package com.sekwah.sekclib;

import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented.BaseSyncTrackers;
import com.sekwah.sekclib.common.SekCLibCommon;
import com.sekwah.sekclib.network.SekCPacketHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SekCLibCommon.MOD_ID)
public class SekCLibNeoForge {

    private static IEventBus modEventBus;

    public static final Logger LOGGER = LogManager.getLogger("SekC Lib");

    public SekCLibNeoForge(ModContainer modContainer) {

        //ModLoadingContext loadingContext = ModLoadingContext.get();
        //loadingContext.registerConfig(ModConfig.Type.COMMON, NarutoConfig.MOD_CONFIG, "naruto-mod.toml");

        IEventBus eventBus = modContainer.getEventBus();
        modEventBus = eventBus;
        eventBus.addListener(SyncDataCapabilityHandler::registerCapabilities);
        eventBus.addListener(BaseSyncTrackers::registerSyncTrackerEvent);
        eventBus.addListener(SekCPacketHandler::registerPayloads);
        eventBus.addListener(this::setup);
        NeoForge.EVENT_BUS.addListener(SyncDataCapabilityHandler::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(SyncDataCapabilityHandler::onEntityTick);
        NeoForge.EVENT_BUS.addListener(SyncDataCapabilityHandler::onEntityLeaveLevel);
        NeoForge.EVENT_BUS.addListener(SyncDataCapabilityHandler::dimensionChange);
        NeoForge.EVENT_BUS.addListener(SyncDataCapabilityHandler::playerTracking);

    }

    private void setup(final FMLCommonSetupEvent event) {
        SekCLibNeoForge.postModEvent(new RegisterSyncTrackerTypeEvent());
        SekCLibNeoForge.postModEvent(new RegisterCapabilitySyncEvent());
    }


    private static void postModEvent(net.neoforged.bus.api.Event event) {
        modEventBus.post(event);
    }
}
