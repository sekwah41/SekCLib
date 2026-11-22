package com.sekwah.sekclibtest.mixins.client;

import com.sekwah.sekclibtest.SekCLibTest;
import com.sekwah.sekclibtest.capabilities.CapabilityHandler;
import com.sekwah.sekclibtest.capabilities.IDiamondData;
import com.sekwah.sekclibtest.render.LivingEntityRenderStateExt;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {

    @Shadow
    protected M model;
    private Identifier diamond = Identifier.fromNamespaceAndPath(SekCLibTest.MOD_ID, "textures/entity/diamond_block.png");

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    protected void getEntityTexture(LivingEntityRenderState state, boolean isBodyVisible, boolean forceTransparent, boolean appearGlowing, CallbackInfoReturnable<net.minecraft.client.renderer.rendertype.RenderType> cir) {
        if (((LivingEntityRenderStateExt) state).isDiamondTexture()) {
            if (forceTransparent) {
                cir.setReturnValue(RenderTypes.entityTranslucentCull(diamond));
            } else if (isBodyVisible) {
                cir.setReturnValue(this.model.renderType(diamond));
            } else {
                cir.setReturnValue(appearGlowing ? RenderTypes.outline(diamond) : null);
            }
            cir.cancel();
        }
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("HEAD"))
    public void extractRenderState(LivingEntity entity, LivingEntityRenderState state, float partialTicks, CallbackInfo ci) {
        IDiamondData diamondData = entity.getCapability(CapabilityHandler.DIAMOND_DATA, null);
        ((LivingEntityRenderStateExt) state).setDiamondTexture(diamondData != null && diamondData.isDiamond());
    }
}
