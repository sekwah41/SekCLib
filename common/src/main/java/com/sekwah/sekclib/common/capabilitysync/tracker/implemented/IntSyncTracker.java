package com.sekwah.sekclib.common.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class IntSyncTracker implements SyncTrackerSerializer<Integer> {

    @Override
    public void encode(Integer objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(objectToSend);
    }

    @Override
    public Integer decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readInt();
    }

}
