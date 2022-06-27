package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.entity.CastIronOvenBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class CastIronOvenBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public CastIronOvenBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = VoxelShapes.combineAndSimplify(Block.createCuboidShape(1, 0, 1, 15, 11, 15), Block.createCuboidShape(7, 11, 13, 9, 15, 16), BooleanBiFunction.OR);
    private static final VoxelShape SHAPE_E = VoxelShapes.combineAndSimplify(Block.createCuboidShape(1, 0, 1, 15, 11, 15), Block.createCuboidShape(0, 11, 7, 3, 15, 9), BooleanBiFunction.OR);
    private static final VoxelShape SHAPE_S = VoxelShapes.combineAndSimplify(Block.createCuboidShape(1, 0, 1, 15, 11, 15), Block.createCuboidShape(7, 11, 0, 9, 15, 3), BooleanBiFunction.OR);
    private static final VoxelShape SHAPE_W = VoxelShapes.combineAndSimplify(Block.createCuboidShape(1, 0, 1, 15, 11, 15), Block.createCuboidShape(13, 11, 7, 16, 15, 9), BooleanBiFunction.OR);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /* BLOCK ENTITY */

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CastIronOvenBlockEntity) {
                ItemScatterer.spawn(world, pos, (CastIronOvenBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CastIronOvenBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CAST_IRON_OVEN, CastIronOvenBlockEntity::tick);
    }
}
