package net.techtastic.witcheryrestitched.block.custom.keyed;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.custom.withentity.TrapdoorBlockWithEntity;
import net.techtastic.witcheryrestitched.block.entity.TrapdoorBlockEntity;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class KeyedTrapdoor extends TrapdoorBlockWithEntity {
    private BlockEntityType<?> type;
    public KeyedTrapdoor(BlockEntityType<?> type, Settings settings) {
        super(settings);
        this.type = type;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrapdoorBlockEntity(this.type, pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ModItems.KEY)) {
            NbtCompound nbt = stack.getOrCreateNbt();

            if (nbt.contains("witcheryrestitched:keyUuid")) {
                TrapdoorBlockEntity trapdoor = (TrapdoorBlockEntity) world.getBlockEntity(pos);

                UUID keyUuid = nbt.getUuid("witcheryrestitched:keyUuid");

                if (keyUuid.equals(trapdoor.getTrapdoorUUID())) {
                    return super.onUse(state, world, pos, player, hand, hit);
                }
            }
        } else if (stack.isOf(ModItems.KEY_RING)) {
            NbtCompound nbt = stack.getOrCreateNbt();
            for (int i = 1; i < nbt.getInt("witcheryrestitched:keyCount") + 1; i++) {
                TrapdoorBlockEntity trapdoor = (TrapdoorBlockEntity) world.getBlockEntity(pos);

                UUID keyUuid = nbt.getUuid("witcheryrestitched:keyUuid" + i);

                if (keyUuid.equals(trapdoor.getTrapdoorUUID())) {
                    return super.onUse(state, world, pos, player, hand, hit);
                }
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        TrapdoorBlockEntity trapdoor = (TrapdoorBlockEntity) world.getBlockEntity(pos);

        ItemStack key = new ItemStack(ModItems.KEY, 1);
        NbtCompound nbt = key.getOrCreateNbt();
        nbt.putDouble("witcheryrestitched:keyX", pos.getX());
        nbt.putDouble("witcheryrestitched:keyY", pos.getY());
        nbt.putDouble("witcheryrestitched:keyZ", pos.getZ());
        nbt.putUuid("witcheryrestitched:keyUuid", trapdoor.getTrapdoorUUID());

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

        trapdoor.markDirty();
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {}

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
