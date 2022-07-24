package net.techtastic.witcheryrestitched.block.entity.hawthorn;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.entity.DoorBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;

public class HawthornDoorBlockEntity extends DoorBlockEntity {
    public HawthornDoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HAWTHORN_DOOR_BLOCK_ENTITY, pos, state);
    }
}
