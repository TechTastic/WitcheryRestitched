package net.techtastic.witcheryrestitched.block.custom.rowan;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedDoor;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedFenceGate;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.rowan.RowanFenceGateBlockEntity;
import org.jetbrains.annotations.Nullable;

public class RowanKeyedFenceGate extends KeyedFenceGate {
    public RowanKeyedFenceGate(Settings settings) {
        super(ModBlockEntities.ROWAN_FENCE_GATE_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RowanFenceGateBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
