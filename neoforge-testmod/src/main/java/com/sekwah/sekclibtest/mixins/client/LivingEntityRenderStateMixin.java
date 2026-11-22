package com.sekwah.sekclibtest.mixins.client;

import com.sekwah.sekclibtest.render.LivingEntityRenderStateExt;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin implements LivingEntityRenderStateExt {
    @Unique
    private boolean sekclibtest$isDiamondTexture;

    @Override
    public boolean isDiamondTexture() {
        return sekclibtest$isDiamondTexture;
    }

    @Override
    public void setDiamondTexture(boolean isDiamondTexture) {
        this.sekclibtest$isDiamondTexture = isDiamondTexture;
    }
}
