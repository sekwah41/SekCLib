package com.sekwah.sekclib.registries;

import com.sekwah.sekclib.SekCLibForge;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.common.SekCLibCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

public class SekCLibRegistries {

    public static ForgeRegistry<CapabilityEntry> CAPABILITY_REGISTRY;

    /**
     * There may be a better way of doing this though this is to sync the id's between client and server
     * and allow for much shorter packets to be sent.
     *
     * Do not manually register to these registries, it will be handled all within SekCLib
     *
     * @param event
     */
    public static void registerRegistries(NewRegistryEvent event) {
        RegistryBuilder<CapabilityEntry> capabilityEntries = new RegistryBuilder<>();
        capabilityEntries.setName(new ResourceLocation(SekCLibCommon.MOD_ID, "capability_sync"));
        capabilityEntries.disableSaving();

        event.create(capabilityEntries, (registry) -> {
            CAPABILITY_REGISTRY = (ForgeRegistry<CapabilityEntry>) registry;
        }).get();
    }

}
