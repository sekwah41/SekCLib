package com.sekwah.sekclib;

import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityBroadcaster;
import com.sekwah.sekclib.handler.HandlerManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class SekCLibFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CapabilitySyncRegistry.initializeRuntime();
        ServerLifecycleEvents.SERVER_STARTING.register(server -> CapabilitySyncRegistry.initializeExtensions());
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            for (Entity entity : world.getAllEntities())
                if (entity instanceof LivingEntity living) CapabilityBroadcaster.checkCapData(living);
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            CapabilityBroadcaster.broadcastCapChanges(player, true);
        });
        EntityTrackingEvents.START_TRACKING.register((entity, player) -> {
            if (entity instanceof LivingEntity living) CapabilityBroadcaster.broadcastCapToPlayer(living, player);
        });
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, from, to) -> CapabilityBroadcaster.broadcastCapChanges(player, true));
        HandlerManager.init();
    }
}
