package net.techtastic.witcheryrestitched.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void WitcheryRestitched$interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() == ModItems.TAGLOCK) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }
}
