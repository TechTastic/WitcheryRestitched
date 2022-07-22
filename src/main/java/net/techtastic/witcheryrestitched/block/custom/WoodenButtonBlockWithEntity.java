package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WoodenButtonBlockWithEntity extends AbstractButtonBlockWithEntity{
    public WoodenButtonBlockWithEntity(BlockEntityType<?> type, Settings settings) {
        super(true, settings);
    }

    protected SoundEvent getClickSound(boolean powered) {
        return powered ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }
}
