package com.sekwah.sekclib.common.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class BoolSyncTracker implements SyncTrackerSerializer<Boolean> {

    @Override
    public void encode(Boolean objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeBoolean(objectToSend);
    }

    @Override
    public Boolean decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readBoolean();
    }
}
