package net.techtastic.witcheryrestitched.block.custom.rowan;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedTrapdoor;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.rowan.RowanTrapdoorBlockEntity;
import org.jetbrains.annotations.Nullable;

public class RowanKeyedTrapdoor extends KeyedTrapdoor {
    public RowanKeyedTrapdoor(Settings settings) {
        super(ModBlockEntities.ROWAN_TRAPDOOR_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RowanTrapdoorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
