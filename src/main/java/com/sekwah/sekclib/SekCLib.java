package com.sekwah.sekclib;

import com.mojang.logging.LogUtils;
import com.sekwah.sekclib.capabilitysync.capability.ISyncData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import com.sekwah.sekclib.network.SekCPacketHandler;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(SekCLib.MOD_ID)
public class SekCLib {

    public static final String MOD_ID = "sekclib";

    public static final Logger LOGGER = LogUtils.getLogger();

    public SekCLib(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(SekCLibRegistries::registerRegistries);

    }

    private void setup(final FMLCommonSetupEvent event) {
        SekCPacketHandler.init();
        SekCLibRegistries.CAPABILITY_REGISTRY.unfreeze();
        ModLoader.get().postEvent(new RegisterSyncTrackerTypeEvent());
        ModLoader.get().postEvent(new RegisterCapabilitySyncEvent());
        SekCLibRegistries.CAPABILITY_REGISTRY.freeze();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ISyncData.class);
    }

}
