package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CandelabraBlock extends Block {

    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    protected final ParticleEffect particle;

    public CandelabraBlock(Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
        setDefaultState(getStateManager().getDefaultState().with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(1, 0, 1, 15, 14, 15);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (player.getStackInHand(hand).getItem() == Items.FLINT_AND_STEEL || player.getStackInHand(hand).getItem() == Items.FIRE_CHARGE) {
                world.setBlockState(pos, state.with(LIT, true));

                return ActionResult.SUCCESS;
            } else if (player.getStackInHand(hand).isEmpty() && player.isSneaking()) {
                world.setBlockState(pos, state.with(LIT, false));

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.FAIL;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.equals(state.getBlock().getDefaultState())) {
            double d = (double) pos.getX() + 0.5;
            double e = (double) pos.getY() + 1.1;
            double f = (double) pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);

            d = (double) pos.getX() + 0.5;
            e = (double) pos.getY() + 1.05;
            f = (double) pos.getZ() + 0.2;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);

            d = (double) pos.getX() + 0.5;
            e = (double) pos.getY() + 1.05;
            f = (double) pos.getZ() + 0.8;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);

            d = (double) pos.getX() + 0.2;
            e = (double) pos.getY() + 1.05;
            f = (double) pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);

            d = (double) pos.getX() + 0.8;
            e = (double) pos.getY() + 1.05;
            f = (double) pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
        }
    }
}
