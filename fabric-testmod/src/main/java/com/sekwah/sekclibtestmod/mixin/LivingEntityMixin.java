package com.sekwah.sekclibtestmod.mixin;

import com.sekwah.sekclibtestmod.capabilities.CapabilityHandler;
import com.sekwah.sekclibtestmod.capabilities.DiamondData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "hurt", at = @At("RETURN"))
    private void sekclibtest$markDiamond(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            DiamondData data = CapabilityHandler.DIAMOND_DATA.getNullable((LivingEntity) (Object) this);
            if (data != null) data.setDiamond(true);
        }
    }
}
