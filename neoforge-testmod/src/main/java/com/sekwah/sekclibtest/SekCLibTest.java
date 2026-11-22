package com.sekwah.sekclibtest;

import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import com.sekwah.sekclibtest.capabilities.CapabilityHandler;
import com.sekwah.sekclibtest.capabilities.DiamondData;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SekCLibTest.MOD_ID)
public class SekCLibTest {
    public static final String MOD_ID = "sekclibtest";

    public SekCLibTest(ModContainer modContainer) {
        new SekCLibTestCommon();
        IEventBus eventBus = modContainer.getEventBus();
        eventBus.addListener(CapabilityHandler::registerCapabilities);
        eventBus.addListener(this::registerCapabilitySync);
        NeoForge.EVENT_BUS.addListener(CapabilityHandler::livingHurt);
        NeoForge.EVENT_BUS.addListener(CapabilityHandler::onEntityLeaveLevel);
    }

    public void registerCapabilitySync(RegisterCapabilitySyncEvent event) {
        event.registerPlayerCap(Identifier.fromNamespaceAndPath(MOD_ID, "test_data"), CapabilityHandler.DIAMOND_DATA, DiamondData.class);
    }
}
