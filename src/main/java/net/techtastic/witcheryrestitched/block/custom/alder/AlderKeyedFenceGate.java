package net.techtastic.witcheryrestitched.block.custom.alder;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedDoor;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedFenceGate;
import net.techtastic.witcheryrestitched.block.entity.alder.AlderFenceGateBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AlderKeyedFenceGate extends KeyedFenceGate {
    public AlderKeyedFenceGate(Settings settings) {
        super(ModBlockEntities.ALDER_FENCE_GATE_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlderFenceGateBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
