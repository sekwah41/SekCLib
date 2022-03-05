package com.sekwah.sekclib;

import com.sekwah.sekclib.capabilitysync.capability.ISyncData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import com.sekwah.sekclib.network.SekCPacketHandler;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SekCLib.MOD_ID)
public class SekCLib {

    public static final String MOD_ID = "sekclib";

    public static final Logger LOGGER = LogManager.getLogger("SekC Lib");

    public SekCLib() {

        //ModLoadingContext loadingContext = ModLoadingContext.get();
        //loadingContext.registerConfig(ModConfig.Type.COMMON, NarutoConfig.MOD_CONFIG, "naruto-mod.toml");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::registerCapabilities);
        eventBus.addListener(this::setup);
        eventBus.addListener(SekCLibRegistries::registerRegistries);

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
