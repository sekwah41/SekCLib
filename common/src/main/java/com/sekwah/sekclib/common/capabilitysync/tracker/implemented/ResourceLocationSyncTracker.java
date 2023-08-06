package com.sekwah.sekclib.common.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationSyncTracker implements SyncTrackerSerializer<ResourceLocation> {

    @Override
    public void encode(ResourceLocation objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeUtf(objectToSend.toString());
    }

    @Override
    public ResourceLocation decode(FriendlyByteBuf inBuffer) {
        return new ResourceLocation(inBuffer.readUtf());
    }
}
