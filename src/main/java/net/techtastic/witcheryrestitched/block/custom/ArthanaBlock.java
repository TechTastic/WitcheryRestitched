package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.AltarBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ArthanaBlockEntity;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class ArthanaBlock extends BlockWithEntity implements BlockEntityProvider {

    private PlayerEntity interactingPlayer;
    private ArthanaBlockEntity arthana;
    public ArthanaBlock(Settings settings) {
        super(settings);
    }

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Block.createCuboidShape(3, 0, 1, 14, 1, 13);
    private static final VoxelShape SHAPE_E = Block.createCuboidShape(3, 0, 2, 15, 1, 13);
    private static final VoxelShape SHAPE_S = Block.createCuboidShape(3, 0, 3, 14, 1, 15);
    private static final VoxelShape SHAPE_W = Block.createCuboidShape(1, 0, 2, 13, 1, 13);

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

    // BLOCK ENTITY

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ArthanaBlockEntity(pos, state);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.interactingPlayer = player;
        this.arthana = (ArthanaBlockEntity) world.getBlockEntity(pos);

        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        this.interactingPlayer.giveItemStack(this.arthana.getItemStack());

        super.onBroken(world, pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            ArthanaBlockEntity arthana = (ArthanaBlockEntity) world.getBlockEntity(pos);

            NbtCompound nbt = new NbtCompound();
            arthana.readNbt(nbt);

            ItemStack newArthana = new ItemStack(ModItems.ARTHANA);
            newArthana.setNbt(nbt);
            newArthana.setCount(1);

            world.setBlockState(pos, Blocks.AIR.getDefaultState());

            player.giveItemStack(newArthana);

            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }
}
