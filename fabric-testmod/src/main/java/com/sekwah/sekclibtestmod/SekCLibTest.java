package com.sekwah.sekclibtestmod;

import com.sekwah.sekclibtest.SekCLibTestCommon;
import net.fabricmc.api.ModInitializer;

public class SekCLibTest implements ModInitializer {

    public static final String MOD_ID = "sekclibtestmod";

    @Override
    public void onInitialize() {
        SekCLibTestCommon common = new SekCLibTestCommon();
    }
}
