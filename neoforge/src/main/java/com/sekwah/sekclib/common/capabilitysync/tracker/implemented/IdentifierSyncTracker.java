package com.sekwah.sekclib.common.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;

public class IdentifierSyncTracker implements SyncTrackerSerializer<Identifier> {

    @Override
    public void encode(Identifier objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeUtf(objectToSend.toString());
    }

    @Override
    public Identifier decode(FriendlyByteBuf inBuffer) {
        return Identifier.parse(inBuffer.readUtf());
    }
}
