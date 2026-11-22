package com.sekwah.sekclib.common.capabilitysync.tracker;

import net.minecraft.network.FriendlyByteBuf;

public interface SyncTrackerSerializer<T> {
    void encode(T objectToSend, FriendlyByteBuf outBuffer);

    T decode(FriendlyByteBuf inBuffer);
}
