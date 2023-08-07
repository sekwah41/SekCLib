package com.sekwah.sekclibtest;

import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import com.sekwah.sekclibtest.capabilities.CapabilityHandler;
import com.sekwah.sekclibtest.capabilities.IDiamondData;
import com.sekwah.sekclibtest.capabilities.DiamondData;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SekCLibTest.MOD_ID)
@Mod.EventBusSubscriber(modid = SekCLibTest.MOD_ID)
public class SekCLibTest {
    public static final String MOD_ID = "sekclibtest";

    public SekCLibTest() {

        SekCLibTestCommon common = new SekCLibTestCommon();

        // You can do it this way or use a EventBusSubscriber that points to the MOD bus.
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::registerCapabilities);
        eventBus.addListener(this::registerCapabilitySync);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IDiamondData.class);
    }

    public void registerCapabilitySync(RegisterCapabilitySyncEvent event) {
        event.registerPlayerCap(new ResourceLocation(SekCLibTest.MOD_ID, "test_data"), CapabilityHandler.DIAMOND_DATA, DiamondData.class);
    }
}
