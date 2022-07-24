package net.techtastic.witcheryrestitched.block.custom.hawthorn;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedPressurePlateBlock;
import net.techtastic.witcheryrestitched.block.entity.hawthorn.HawthornPlateBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class HawthornKeyedPressurePlateBlock extends KeyedPressurePlateBlock {
    public HawthornKeyedPressurePlateBlock(ActivationRule type, Settings settings) {
        super(ModBlockEntities.HAWTHORN_PLATE_BLOCK_ENTITY, type, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HawthornPlateBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
