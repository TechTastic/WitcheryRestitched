package net.techtastic.witcheryrestitched.block.custom.hawthorn;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedDoor;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedFenceGate;
import net.techtastic.witcheryrestitched.block.entity.hawthorn.HawthornFenceGateBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class HawthornKeyedFenceGate extends KeyedFenceGate {
    public HawthornKeyedFenceGate(Settings settings) {
        super(ModBlockEntities.HAWTHORN_FENCE_GATE_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HawthornFenceGateBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
