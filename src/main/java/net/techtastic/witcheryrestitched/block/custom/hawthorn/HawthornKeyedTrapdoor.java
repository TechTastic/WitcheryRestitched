package net.techtastic.witcheryrestitched.block.custom.hawthorn;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedTrapdoor;
import net.techtastic.witcheryrestitched.block.entity.hawthorn.HawthornTrapdoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class HawthornKeyedTrapdoor extends KeyedTrapdoor {
    public HawthornKeyedTrapdoor(Settings settings) {
        super(ModBlockEntities.HAWTHORN_TRAPDOOR_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HawthornTrapdoorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
