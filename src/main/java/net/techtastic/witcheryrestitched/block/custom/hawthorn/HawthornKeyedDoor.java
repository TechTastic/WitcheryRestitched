package net.techtastic.witcheryrestitched.block.custom.hawthorn;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedDoor;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.rowan.RowanDoorBlockEntity;
import org.jetbrains.annotations.Nullable;

public class HawthornKeyedDoor extends KeyedDoor {
    public HawthornKeyedDoor(Settings settings) {
        super(ModBlockEntities.HAWTHORN_DOOR_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RowanDoorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
