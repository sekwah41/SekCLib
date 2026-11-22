package com.sekwah.sekclib.network;

import com.sekwah.sekclib.network.s2c.ClientCapabilitySyncPacket;
import com.sekwah.sekclib.network.s2c.ClientVelocityPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;

public final class SekCPacketHandler {
    private SekCPacketHandler() {
    }

    public static void sendToPlayer(Object packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, (net.minecraft.network.protocol.common.custom.CustomPacketPayload) packet);
    }

    public static void sendToAll(Object packet) {
        PacketDistributor.sendToAllPlayers((net.minecraft.network.protocol.common.custom.CustomPacketPayload) packet);
    }

    public static void sendToTracking(Object packet, Entity entity) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, (net.minecraft.network.protocol.common.custom.CustomPacketPayload) packet);
    }

    public static void registerPayloads(net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");
        registrar.playToClient(ClientVelocityPacket.TYPE, ClientVelocityPacket.STREAM_CODEC, ClientVelocityPacket.Handler::handle);
        registrar.playToClient(ClientCapabilitySyncPacket.TYPE, ClientCapabilitySyncPacket.STREAM_CODEC, ClientCapabilitySyncPacket.Handler::handle);
    }
}
