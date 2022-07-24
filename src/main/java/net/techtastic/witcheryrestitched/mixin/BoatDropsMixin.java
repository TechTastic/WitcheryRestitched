package net.techtastic.witcheryrestitched.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.util.CustomBoatType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatDropsMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> ci) {
        if (((BoatEntity)(Object)this).getBoatType() == CustomBoatType.ROWAN) {
            ci.setReturnValue(ModItems.ROWAN_BOAT);
        } else if (((BoatEntity)(Object)this).getBoatType() == CustomBoatType.ALDER) {
            ci.setReturnValue(ModItems.ALDER_BOAT);
        } else if (((BoatEntity)(Object)this).getBoatType() == CustomBoatType.HAWTHORN) {
            ci.setReturnValue(ModItems.HAWTHORN_BOAT);
        }
    }
}
