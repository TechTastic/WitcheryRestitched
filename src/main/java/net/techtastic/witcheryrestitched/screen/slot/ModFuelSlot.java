package net.techtastic.witcheryrestitched.screen.slot;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.util.ModTags;

import java.lang.reflect.WildcardType;

public class ModFuelSlot extends Slot {
    public ModFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {

        if (stack.isIn(ModTags.Items.CAST_IRON_OVEN_INPUT)) {

            ItemStack inputItem = inventory.getStack(1);
            if (inputItem == ItemStack.EMPTY) {

                return false;
            } else if (inputItem == stack && stack.getCount() < stack.getMaxCount()) {
                return false;
            }
        }

        return AbstractFurnaceBlockEntity.canUseAsFuel(stack) || ModFuelSlot.isBucket(stack);
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return ModFuelSlot.isBucket(stack) ? 1 : super.getMaxItemCount(stack);
    }

    public static boolean isBucket(ItemStack stack) { return stack.isOf(Items.BUCKET); }
}
