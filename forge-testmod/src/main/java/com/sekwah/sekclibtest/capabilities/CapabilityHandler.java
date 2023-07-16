package com.sekwah.sekclibtest.capabilities;

import com.sekwah.sekclibtest.SekCLibTest;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SekCLibTest.MOD_ID)
public class CapabilityHandler {
    public static final Capability<IDiamondData> DIAMOND_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(new ResourceLocation(SekCLibTest.MOD_ID, "diamond_infected_data"), new DiamondData());
    }

    @SubscribeEvent
    public static void livingHurt(LivingHurtEvent event) {
        var entity = event.getEntity();
        entity.getCapability(DIAMOND_DATA).ifPresent(testData -> {
            testData.setDiamond(true);
        });
    }
}
