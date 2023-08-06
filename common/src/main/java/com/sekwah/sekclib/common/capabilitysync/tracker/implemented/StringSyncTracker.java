package com.sekwah.sekclib.common.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class StringSyncTracker implements SyncTrackerSerializer<String> {

    @Override
    public void encode(String objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeUtf(objectToSend);
    }

    @Override
    public String decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readUtf();
    }
}
