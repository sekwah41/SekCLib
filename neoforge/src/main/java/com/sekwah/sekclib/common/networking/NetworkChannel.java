package com.sekwah.sekclib.common.networking;

import net.minecraft.resources.Identifier;

public class NetworkChannel {
    private final Identifier channelId;

    private NetworkChannel(Identifier channelId) {
        this.channelId = channelId;
    }

    public <T> void sendToServer(T message) {

    }

    public <T> void sendToPlayer(T message) {

    }

    public <T> void sentToTrackingAndSelf(T message) {

    }


}
