package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class KeyedButtonBlock extends WoodenButtonBlock {
    public KeyedButtonBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ModItems.KEY)) {
            NbtCompound nbt = stack.getOrCreateNbt();

            if (nbt.contains("witcheryrestitched:keyX") && nbt.contains("witcheryrestitched:keyY") && nbt.contains("witcheryrestitched:keyZ")) {
                BlockPos test = new BlockPos(nbt.getDouble("witcheryrestitched:keyX"), nbt.getDouble("witcheryrestitched:keyY"), nbt.getDouble("witcheryrestitched:keyZ"));

                if (test.equals(pos)) {
                    return super.onUse(state, world, pos, player, hand, hit);
                }
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        ItemStack key = new ItemStack(ModItems.KEY, 1);
        NbtCompound nbt = key.getOrCreateNbt();
        nbt.putDouble("witcheryrestitched:keyX", pos.getX());
        nbt.putDouble("witcheryrestitched:keyY", pos.getY());
        nbt.putDouble("witcheryrestitched:keyZ", pos.getZ());

        if (placer.isPlayer()) {
            PlayerEntity player = (PlayerEntity) placer;

            player.giveItemStack(key);
        } else {
            if (placer.getActiveItem().isEmpty()) {
                placer.setStackInHand(placer.getActiveHand(), key);
            } else {
                placer.dropStack(key);
            }

        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
