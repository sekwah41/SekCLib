package com.sekwah.sekclib.network;

import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityInfo;
import com.sekwah.sekclib.common.capabilitysync.tracker.ISyncTrackerData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public final class SekCPacketHandler {
    public static final ResourceLocation CAPABILITY_SYNC = new ResourceLocation("sekclib", "client_capability_sync");
    public static final ResourceLocation VELOCITY = new ResourceLocation("sekclib", "client_velocity");

    private SekCPacketHandler() {
    }

    public static void sendToPlayer(LivingEntity entity, List<CapabilityInfo> data, boolean includePrivate, ServerPlayer player) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        writeCapabilityPacket(buf, entity, data, includePrivate);
        ServerPlayNetworking.send(player, CAPABILITY_SYNC, buf);
    }

    public static void sendToTracking(LivingEntity entity, List<CapabilityInfo> data, boolean includePrivate) {
        for (ServerPlayer player : PlayerLookup.tracking(entity)) sendToPlayer(entity, data, includePrivate, player);
    }

    public static void sendVelocity(ServerPlayer player, int x, int y, int z) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeShort(x);
        buf.writeShort(y);
        buf.writeShort(z);
        ServerPlayNetworking.send(player, VELOCITY, buf);
    }

    private static void writeCapabilityPacket(FriendlyByteBuf buf, Entity entity, List<CapabilityInfo> data, boolean includePrivate) {
        buf.writeInt(entity.getId());
        buf.writeInt(data.size());
        for (CapabilityInfo info : data) {
            buf.writeInt(info.capabilityId);
            int count = info.changedEntries.size() + (includePrivate ? info.changedPrivateEntries.size() : 0);
            buf.writeInt(count);
            writeTrackers(buf, info.changedEntries);
            if (includePrivate) writeTrackers(buf, info.changedPrivateEntries);
        }
    }

    private static void writeTrackers(FriendlyByteBuf buf, List<ISyncTrackerData> trackers) {
        for (ISyncTrackerData tracker : trackers) {
            buf.writeByte(tracker.getSyncEntry().getTrackerId());
            Object value = tracker.getSendValue();
            buf.writeBoolean(value != null);
            if (value != null) tracker.getSyncEntry().getSerializer().encode(value, buf);
        }
    }
}
