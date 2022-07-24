package net.techtastic.witcheryrestitched.block.entity.rowan;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.entity.DoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;

public class RowanDoorBlockEntity extends DoorBlockEntity {
    public RowanDoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ROWAN_DOOR_BLOCK_ENTITY, pos, state);
    }
}
