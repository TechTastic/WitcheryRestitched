package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

import static net.techtastic.witcheryrestitched.block.custom.AltarBlock.MULTIBLOCK;

public class ChaliceBlock extends Block {
    public ChaliceBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(SOUP, false));
    }

    public static final BooleanProperty SOUP = BooleanProperty.of("soup");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SOUP);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(6.5, 0, 6.5, 9.5, 7, 9.5);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasNbt()) {
            NbtCompound nbt = itemStack.getNbt();
            if (nbt.getInt("witcheryrestitched:soup") == 1) {
                world.setBlockState(pos, state.with(SOUP, true));
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem().equals(ModItems.REDSTONE_SOUP) && state.equals(state.getBlock().getDefaultState()) && true) {
            player.getStackInHand(hand).decrement(1);
            world.setBlockState(pos, state.with(SOUP, true));

            return ActionResult.SUCCESS;
        } else if (player.getStackInHand(hand).getItem().equals(Items.GLASS_BOTTLE) && !state.equals(state.getBlock().getDefaultState()) && true) {
            player.getStackInHand(hand).decrement(1);
            world.setBlockState(pos, state.with(SOUP, false));
            ItemStack soup = new ItemStack(ModItems.REDSTONE_SOUP);
            soup.setCount(1);
            player.giveItemStack(soup);

            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public MutableText getName() {
        return super.getName();
    }
}
