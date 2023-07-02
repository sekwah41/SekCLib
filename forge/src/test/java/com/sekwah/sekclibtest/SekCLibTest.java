package com.sekwah.sekclibtest;

import net.minecraftforge.fml.common.Mod;

@Mod(SekCLibTest.MOD_ID)
@Mod.EventBusSubscriber(modid = SekCLibTest.MOD_ID)
public class SekCLibTest {
    public static final String MOD_ID = "sekclibtest";

    public SekCLibTest() {
        System.out.println("HELLO");
    }
}
