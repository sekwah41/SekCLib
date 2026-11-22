package com.sekwah.sekclibtestmod.capabilities;

import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.nbt.CompoundTag;

public final class DiamondData implements IDiamondData, Component {
    @Sync(syncGlobally = true)
    public boolean diamondMode;

    @Override
    public boolean isDiamond() {
        return diamondMode;
    }

    @Override
    public void setDiamond(boolean diamond) {
        diamondMode = diamond;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        diamondMode = tag.getBoolean("diamond_mode");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("diamond_mode", diamondMode);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DiamondData data && data.diamondMode == diamondMode;
    }
}
