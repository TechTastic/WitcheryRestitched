package net.techtastic.witcheryrestitched.block.custom.alder;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedPressurePlateBlock;
import net.techtastic.witcheryrestitched.block.entity.alder.AlderPlateBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AlderKeyedPressurePlateBlock extends KeyedPressurePlateBlock {
    public AlderKeyedPressurePlateBlock(ActivationRule type, Settings settings) {
        super(ModBlockEntities.ALDER_PLATE_BLOCK_ENTITY, type, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlderPlateBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
