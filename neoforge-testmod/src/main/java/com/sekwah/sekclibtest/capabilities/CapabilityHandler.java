package com.sekwah.sekclibtest.capabilities;

import com.sekwah.sekclibtest.SekCLibTest;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.HashMap;
import java.util.Map;

public final class CapabilityHandler {
    public static final EntityCapability<IDiamondData, Void> DIAMOND_DATA = EntityCapability.createVoid(
            Identifier.fromNamespaceAndPath(SekCLibTest.MOD_ID, "diamond_data"), IDiamondData.class);
    private static final Map<Entity, DiamondData> DATA = new HashMap<>();

    private CapabilityHandler() {}

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        registerEntityCapability(event);
    }

    private static <E extends Entity> void registerEntityCapability(RegisterCapabilitiesEvent event) {
        var zombie = BuiltInRegistries.ENTITY_TYPE.get(Identifier.fromNamespaceAndPath("minecraft", "zombie")).orElseThrow().value();
        registerForType(event, zombie);
    }

    private static <E extends Entity> void registerForType(RegisterCapabilitiesEvent event, net.minecraft.world.entity.EntityType<E> entityType) {
        event.registerEntity(DIAMOND_DATA, entityType, (entity, context) -> getOrCreate(entity));
    }

    private static synchronized DiamondData getOrCreate(Entity entity) {
        return DATA.computeIfAbsent(entity, ignored -> new DiamondData());
    }

    public static synchronized void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        DATA.remove(event.getEntity());
    }

    public static void livingHurt(LivingDamageEvent.Post event) {
        IDiamondData data = event.getEntity().getCapability(DIAMOND_DATA, null);
        if (data != null) data.setDiamond(true);
    }
}
