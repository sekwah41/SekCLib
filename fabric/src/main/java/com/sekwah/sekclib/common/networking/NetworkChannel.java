package com.sekwah.sekclib.common.networking;

import net.minecraft.resources.ResourceLocation;

public class NetworkChannel {
    private final ResourceLocation channelId;

    private NetworkChannel(ResourceLocation channelId) {
        this.channelId = channelId;
    }

    public <T> void sendToServer(T message) {

    }

    public <T> void sendToPlayer(T message) {

    }

    public <T> void sentToTrackingAndSelf(T message) {

    }


}
