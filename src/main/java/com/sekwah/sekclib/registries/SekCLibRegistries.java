package com.sekwah.sekclib.registries;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class SekCLibRegistries {

    public static final ResourceKey<Registry<CapabilityEntry>> CAPABILITY_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(SekCLib.MOD_ID, "capability_entry"));
    public static Registry<CapabilityEntry> CAPABILITY_REGISTRY = new RegistryBuilder<>(CAPABILITY_REGISTRY_KEY)
            .sync(true)
            .create();

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
        capabilityEntries.setName(new ResourceLocation(SekCLib.MOD_ID, "capability_sync"));
        capabilityEntries.disableSaving();

        event.create(capabilityEntries, (registry) -> {
            CAPABILITY_REGISTRY = (ForgeRegistry<CapabilityEntry>) registry;
        }).get();
    }

}
