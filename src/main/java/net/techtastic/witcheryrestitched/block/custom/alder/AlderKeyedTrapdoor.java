package net.techtastic.witcheryrestitched.block.custom.alder;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedTrapdoor;
import net.techtastic.witcheryrestitched.block.entity.alder.AlderTrapdoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AlderKeyedTrapdoor extends KeyedTrapdoor {
    public AlderKeyedTrapdoor(Settings settings) {
        super(ModBlockEntities.ALDER_TRAPDOOR_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlderTrapdoorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
