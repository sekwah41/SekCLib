package com.sekwah.sekclibtest.capabilities;

import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiamondData implements IDiamondData, ICapabilityProvider {


    private final LazyOptional<IDiamondData> holder = LazyOptional.of(() -> this);

    @Sync(syncGlobally = true)
    private boolean diamondMode = false;

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

}
