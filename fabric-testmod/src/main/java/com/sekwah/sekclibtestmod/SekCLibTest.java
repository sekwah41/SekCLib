package com.sekwah.sekclibtestmod;

import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncCallback;
import com.sekwah.sekclibtest.SekCLibTestCommon;
import com.sekwah.sekclibtestmod.capabilities.CapabilityHandler;
import com.sekwah.sekclibtestmod.capabilities.DiamondData;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class SekCLibTest implements ModInitializer {

    public static final String MOD_ID = "sekclibtest";

    @Override
    public void onInitialize() {
        SekCLibTestCommon common = new SekCLibTestCommon();
        RegisterCapabilitySyncCallback.EVENT.register(event -> event.registerComponent(
                new ResourceLocation(MOD_ID, "diamond_data"), CapabilityHandler.DIAMOND_DATA, DiamondData.class));
    }
}
