package net.techtastic.witcheryrestitched.block.custom;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.techtastic.witcheryrestitched.block.entity.CauldronBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.DistilleryBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.util.IAltarPowerSink;
import org.jetbrains.annotations.Nullable;

public class CauldronBlock extends BlockWithEntity implements IAltarPowerSink {
    public CauldronBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        CauldronBlockEntity cauldron = (CauldronBlockEntity) world.getBlockEntity(pos);

        if (player.getStackInHand(hand).isOf(Items.WATER_BUCKET)) {
            if (cauldron.fluidStorage.getAmount() < cauldron.fluidStorage.getCapacity()) {
                cauldron.fluidStorage.amount = cauldron.fluidStorage.getAmount() + FluidConstants.BUCKET;
                if (world.isClient()) {
                    player.sendMessage(Text.of(cauldron.fluidStorage.getAmount() + ""));
                }

                return ActionResult.CONSUME;
            }

            return ActionResult.FAIL;
        } else {
            if (world.isClient()) {
                player.sendMessage(Text.of(cauldron.getItems().toString()));
                player.sendMessage(Text.of(cauldron.isBoiling() + ""));
                player.sendMessage(Text.of(cauldron.fluidStorage.getAmount() + ""));
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity instanceof ItemEntity) {
            ItemEntity ie = (ItemEntity) entity;
            ItemStack stack = ie.getStack().copy();
            CauldronBlockEntity cauldron = (CauldronBlockEntity) world.getBlockEntity(pos);

            if (stack.isOf(ModItems.GYPSUM) || stack.isOf(ModItems.QUICKLIME)) {
                ie.setDespawnImmediately();

                cauldron.fluidStorage.amount = (long) 0;
                cauldron.inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
                cauldron.setBoiling(false);

                cauldron.markDirty();

                if (stack.isOf(ModItems.QUICKLIME)) {
                    world.createExplosion(entity, pos.getX(), pos.getY() + 1, pos.getZ(), 0.5F, Explosion.DestructionType.NONE);
                }
            }

            if (cauldron.isBoiling()) {

                ie.setDespawnImmediately();

                for (int i = 0; i < cauldron.getItems().size(); i++) {
                    if (cauldron.getStack(i).isEmpty()) {
                        cauldron.setStack(i, stack);
                        break;
                    }
                }

                cauldron.markDirty();
            }
        } else {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CauldronBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CAULDRON, CauldronBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
