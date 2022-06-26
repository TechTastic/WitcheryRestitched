package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.techtastic.witcheryrestitched.item.ModItems;

public class GarlicCropBlock extends CropBlock {
    public GarlicCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.GARLIC;
    }
}
