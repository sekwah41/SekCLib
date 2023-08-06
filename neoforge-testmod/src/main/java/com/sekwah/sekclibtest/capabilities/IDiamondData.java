package com.sekwah.sekclibtest.capabilities;

import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IDiamondData extends INBTSerializable<Tag> {
    boolean isDiamond();
    void setDiamond(boolean isDiamond);
}
