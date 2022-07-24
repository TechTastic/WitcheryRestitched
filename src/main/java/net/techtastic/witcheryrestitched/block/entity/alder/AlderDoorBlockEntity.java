package net.techtastic.witcheryrestitched.block.entity.alder;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.entity.DoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;

public class AlderDoorBlockEntity extends DoorBlockEntity {
    public AlderDoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALDER_DOOR_BLOCK_ENTITY, pos, state);
    }
}
