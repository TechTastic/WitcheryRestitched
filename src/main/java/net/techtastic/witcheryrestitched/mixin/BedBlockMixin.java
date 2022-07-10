package net.techtastic.witcheryrestitched.mixin;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.util.ModdedBedBlockInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BedBlock.class)
public class BedBlockMixin {
    @Inject(at = @At("HEAD"), method = "onUse")
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfo info) {
        BedBlockEntity bed = (BedBlockEntity) world.getBlockEntity(pos);
        ModdedBedBlockInterface Ibed = (ModdedBedBlockInterface) bed;

        Ibed.setUsed(true);
        Ibed.setUserUuid(player.getUuid());
        Ibed.setUserName(player.getDisplayName().getString());
        bed.markDirty();
    }
}