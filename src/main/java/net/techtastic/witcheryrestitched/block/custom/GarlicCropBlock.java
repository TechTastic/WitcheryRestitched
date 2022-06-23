package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.techtastic.witcheryrestitched.item.ModItems;

public class GarlicCropBlock extends CropBlock {
    public GarlicCropBlock(Settings settings) {
        super(settings);
    }

    public static final int MAX_AGE = 3;

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.GARLIC;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }
}
