package com.sekwah.sekclibtest.capabilities;

import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiamondData implements IDiamondData, ICapabilityProvider {


    private final LazyOptional<IDiamondData> holder = LazyOptional.of(() -> this);

    @Sync(syncGlobally = true)
    private boolean diamondMode = false;

    private static final String DIAMOND_MODE_TAG = "diamond_mode";

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityHandler.DIAMOND_DATA.orEmpty(cap, holder);
    }

    @Override
    public boolean isDiamond() {
        return this.diamondMode;
    }

    @Override
    public void setDiamond(boolean isDiamond) {
        this.diamondMode = isDiamond;
    }

    @Override
    public Tag serializeNBT() {
        final CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean(DIAMOND_MODE_TAG, this.diamondMode);

        return compoundTag;
    }

    @Override
    public void deserializeNBT(Tag tag) {
        if (tag instanceof CompoundTag compoundTag) {
            this.diamondMode = compoundTag.getBoolean(DIAMOND_MODE_TAG);
        }
    }

}
