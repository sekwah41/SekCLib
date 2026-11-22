package com.sekwah.sekclibtestmod.mixin.client;

import com.sekwah.sekclibtestmod.SekCLibTest;
import com.sekwah.sekclibtestmod.capabilities.CapabilityHandler;
import com.sekwah.sekclibtestmod.capabilities.IDiamondData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

    @Shadow
    protected M model;
    private ResourceLocation diamond = new ResourceLocation(SekCLibTest.MOD_ID, "textures/entity/diamond_block.png");

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    protected void getEntityTexture(T target, boolean isBodyVisible, boolean forceTransparent, boolean appearGlowing, CallbackInfoReturnable<RenderType> cir) {
        IDiamondData diamondData = CapabilityHandler.DIAMOND_DATA.getNullable(target);
        if (diamondData != null && diamondData.isDiamond()) {
            if (forceTransparent) {
                cir.setReturnValue(RenderType.entityTranslucentCull(diamond));
            } else if (isBodyVisible) {
                cir.setReturnValue(this.model.renderType(diamond));
            } else {
                cir.setReturnValue(appearGlowing ? RenderType.outline(diamond) : null);
            }
            cir.cancel();
        }
    }
}
