package com.sekwah.sekclibtestmod.mixin.client;

import com.sekwah.sekclibtestmod.SekCLibTest;
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

    @Shadow protected M model;
    private ResourceLocation diamond = new ResourceLocation(SekCLibTest.MOD_ID, "textures/entity/diamond_block.png");

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    protected void getEntityTexture(T target, boolean p_115323_, boolean p_115324_, boolean p_115325_, CallbackInfoReturnable<RenderType> cir) {
        /*IDiamondData data = TestLookupApi.DIAMOND_DATA_ENTITY.find(target, null);
        if(data != null && data.isDiamond()) {
            ResourceLocation resourcelocation = diamond;
            if (p_115324_) {
                cir.setReturnValue(RenderType.itemEntityTranslucentCull(resourcelocation));
            } else if (p_115323_) {
                cir.setReturnValue(this.model.renderType(resourcelocation));
            } else {
                cir.setReturnValue(p_115325_ ? RenderType.outline(resourcelocation) : null);
            }
            cir.cancel();
        }*/
    }
}
