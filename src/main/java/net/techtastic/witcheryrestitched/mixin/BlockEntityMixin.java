package net.techtastic.witcheryrestitched.mixin;

import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @Inject(at = @At("HEAD"), method = "readNbt")
    protected void WitcheryRestitched$readCustomNbt(NbtCompound nbt, CallbackInfo info) {}

    @Inject(at = @At("HEAD"), method = "writeNbt")
    protected void WitcheryRestitched$writeCustomNbt(NbtCompound nbt, CallbackInfo info) {}

    @Inject(at = @At("RETURN"), method = "toInitialChunkDataNbt", cancellable = true)
    protected void WitcheryRestitched$toInitialChunkDataCustomNbt(CallbackInfoReturnable<NbtCompound> cir) {}

}
