package net.techtastic.witcheryrestitched.block.custom.alder;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedDoor;
import net.techtastic.witcheryrestitched.block.entity.alder.AlderDoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AlderKeyedDoor extends KeyedDoor {
    public AlderKeyedDoor(Settings settings) {
        super(ModBlockEntities.ALDER_DOOR_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlderDoorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
