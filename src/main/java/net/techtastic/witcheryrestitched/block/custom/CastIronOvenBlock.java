package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CastIronOvenBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public CastIronOvenBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(1, 0, 1, 2, 2, 2),
            Block.createCuboidShape(14, 0, 1, 15, 2, 2),
            Block.createCuboidShape(13, 1, 1, 14, 2, 15),
            Block.createCuboidShape(3, 2, 3, 13, 8, 13),
            Block.createCuboidShape(2, 8, 2, 14, 9, 14),
            Block.createCuboidShape(3, 9, 3, 13, 10, 13),
            Block.createCuboidShape(7, 10, 11, 9, 14, 13),
            Block.createCuboidShape(7, 12, 13, 9, 14, 16),
            Block.createCuboidShape(2, 1, 1, 3, 2, 15),
            Block.createCuboidShape(3, 1, 2, 13, 2, 3),
            Block.createCuboidShape(3, 1, 13, 13, 2, 14),
            Block.createCuboidShape(14, 0, 14, 15, 2, 15),
            Block.createCuboidShape(1, 0, 14, 2, 2, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(1, 0, 14, 2, 2, 15),
            Block.createCuboidShape(1, 0, 1, 2, 2, 2),
            Block.createCuboidShape(1, 1, 2, 15, 2, 3),
            Block.createCuboidShape(3, 2, 3, 13, 8, 13),
            Block.createCuboidShape(2, 8, 2, 14, 9, 14),
            Block.createCuboidShape(3, 9, 3, 13, 10, 13),
            Block.createCuboidShape(11, 10, 7, 13, 14, 9),
            Block.createCuboidShape(13, 12, 7, 16, 14, 9),
            Block.createCuboidShape(1, 1, 13, 15, 2, 14),
            Block.createCuboidShape(2, 1, 3, 3, 2, 13),
            Block.createCuboidShape(13, 1, 3, 14, 2, 13),
            Block.createCuboidShape(14, 0, 1, 15, 2, 2),
            Block.createCuboidShape(14, 0, 14, 15, 2, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(14, 0, 14, 15, 2, 15),
            Block.createCuboidShape(1, 0, 14, 2, 2, 15),
            Block.createCuboidShape(2, 1, 1, 3, 2, 15),
            Block.createCuboidShape(3, 2, 3, 13, 8, 13),
            Block.createCuboidShape(2, 8, 2, 14, 9, 14),
            Block.createCuboidShape(3, 9, 3, 13, 10, 13),
            Block.createCuboidShape(7, 10, 3, 9, 14, 5),
            Block.createCuboidShape(7, 12, 0, 9, 14, 3),
            Block.createCuboidShape(13, 1, 1, 14, 2, 15),
            Block.createCuboidShape(3, 1, 13, 13, 2, 14),
            Block.createCuboidShape(3, 1, 2, 13, 2, 3),
            Block.createCuboidShape(1, 0, 1, 2, 2, 2),
            Block.createCuboidShape(14, 0, 1, 15, 2, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(14, 0, 1, 15, 2, 2),
            Block.createCuboidShape(14, 0, 14, 15, 2, 15),
            Block.createCuboidShape(1, 1, 13, 15, 2, 14),
            Block.createCuboidShape(3, 2, 3, 13, 8, 13),
            Block.createCuboidShape(2, 8, 2, 14, 9, 14),
            Block.createCuboidShape(3, 9, 3, 13, 10, 13),
            Block.createCuboidShape(3, 10, 7, 5, 14, 9),
            Block.createCuboidShape(0, 12, 7, 3, 14, 9),
            Block.createCuboidShape(1, 1, 2, 15, 2, 3),
            Block.createCuboidShape(13, 1, 3, 14, 2, 13),
            Block.createCuboidShape(2, 1, 3, 3, 2, 13),
            Block.createCuboidShape(1, 0, 14, 2, 2, 15),
            Block.createCuboidShape(1, 0, 1, 2, 2, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
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
}
