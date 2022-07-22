package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
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
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.entity.DoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.FenceGateBlockEntity;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class KeyedFenceGate extends FenceGateBlockWithEntity {

    private BlockEntityType<?> type;

    public KeyedFenceGate(BlockEntityType<?> type, Settings settings) {
        super(settings);
        this.type = type;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FenceGateBlockEntity(this.type, pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ModItems.KEY)) {
            NbtCompound nbt = stack.getOrCreateNbt();

            if (nbt.contains("witcheryrestitched:keyUuid")) {
                FenceGateBlockEntity door = (FenceGateBlockEntity) world.getBlockEntity(pos);

                UUID keyUuid = nbt.getUuid("witcheryrestitched:keyUuid");

                if (keyUuid.equals(door.getDoorUUID())) {
                    return super.onUse(state, world, pos, player, hand, hit);
                }
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        FenceGateBlockEntity door = (FenceGateBlockEntity) world.getBlockEntity(pos);
        UUID newUuid = UUID.randomUUID();
        door.setDoorUuid(newUuid);

        ItemStack key = new ItemStack(ModItems.KEY, 1);
        NbtCompound nbt = key.getOrCreateNbt();
        nbt.putDouble("witcheryrestitched:keyX", pos.getX());
        nbt.putDouble("witcheryrestitched:keyY", pos.getY());
        nbt.putDouble("witcheryrestitched:keyZ", pos.getZ());
        nbt.putUuid("witcheryrestitched:keyUuid", newUuid);

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

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {}

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
