package com.sekwah.sekclibtestmod.capabilities;

import com.sekwah.sekclibtestmod.SekCLibTest;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.resources.ResourceLocation;

public final class CapabilityHandler {
    public static final ComponentKey<DiamondData> DIAMOND_DATA = ComponentRegistry.getOrCreate(
            new ResourceLocation(SekCLibTest.MOD_ID, "diamond_data"), DiamondData.class);

    private CapabilityHandler() {
    }
}
