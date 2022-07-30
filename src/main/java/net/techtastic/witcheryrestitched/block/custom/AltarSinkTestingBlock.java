package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.entity.AltarBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.AltarPowerSinkEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.util.IAltarPowerSink;
import org.jetbrains.annotations.Nullable;

public class AltarSinkTestingBlock extends BlockWithEntity implements BlockEntityProvider, IAltarPowerSink {
    public AltarSinkTestingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {


        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos nearestAltar = locateNearestAltar(world, pos);

        if (nearestAltar != null) {
            AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(nearestAltar);
            boolean isWithinRange = isWithinAltarRange(world, pos, altar);
            boolean drewPower = attemptAltarPowerDraw(world, altar, 50);

            if (world.isClient()) {
                player.sendMessage(Text.of(nearestAltar.toString()));
                player.sendMessage(Text.of(isWithinRange + ""));
                if (drewPower) {
                    player.sendMessage(Text.of("Drew 50 Power!"));
                }
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AltarPowerSinkEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }
}
