package net.techtastic.witcheryrestitched.mixin;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.util.ModdedBedBlockInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedBlockMixin {
    @Inject(at = @At("HEAD"), method = "onUse")
    private void WitcheryRestitched$onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable info) {
        BedBlockEntity bed = (BedBlockEntity) world.getBlockEntity(pos);
        ModdedBedBlockInterface Ibed = (ModdedBedBlockInterface) bed;

        Ibed.WitcheryRestitched$setUsed(true);
        Ibed.WitcheryRestitched$setUserUuid(player.getUuid());
        Ibed.WitcheryRestitched$setUserName(player.getDisplayName().getString());
        bed.markDirty();
    }

    @Inject(at = @At("HEAD"), method = "getRenderType", cancellable = true)
    private void WitcheryRestitched$getRenderType(BlockState state, CallbackInfoReturnable info) {
        info.setReturnValue(BlockRenderType.MODEL);
    }
}