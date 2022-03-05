package com.sekwah.sekclib.network.s2c;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Packets to send to the client
 */
public class ServerUpdateCustomData {

    public final String uuid;
    public final String url;
    public final boolean isTransparent;

    public ServerUpdateCustomData(String uuid, String url, boolean isTransparent) {
        this.uuid = uuid;
        this.url = url;
        this.isTransparent = isTransparent;
    }

    public static void encode(ServerUpdateCustomData msg, PacketBuffer outBuffer) {
        outBuffer.writeString(msg.uuid);
        outBuffer.writeString(msg.url);
        outBuffer.writeBoolean(msg.isTransparent);
    }

    public static ServerUpdateCustomData decode(PacketBuffer inBuffer) {

        String uuid = inBuffer.readString();
        String url = inBuffer.readString();
        boolean isTransparent = inBuffer.readBoolean();

        return new ServerUpdateCustomData(uuid, url, isTransparent);
    }

    public static class Handler {
        public static void handle(ServerUpdateCustomData msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    ClientSkinManager.setSkin(UUID.fromString(msg.uuid), msg.url, msg.isTransparent));*/
            ctx.get().setPacketHandled(true);
        }
    }
}
