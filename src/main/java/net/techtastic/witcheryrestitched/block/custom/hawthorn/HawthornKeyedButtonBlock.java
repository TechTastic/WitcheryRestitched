package net.techtastic.witcheryrestitched.block.custom.hawthorn;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedButtonBlock;
import net.techtastic.witcheryrestitched.block.entity.hawthorn.HawthornButtonBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class HawthornKeyedButtonBlock extends KeyedButtonBlock {
    public HawthornKeyedButtonBlock(Settings settings) {
        super(ModBlockEntities.HAWTHORN_BUTTON_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HawthornButtonBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
