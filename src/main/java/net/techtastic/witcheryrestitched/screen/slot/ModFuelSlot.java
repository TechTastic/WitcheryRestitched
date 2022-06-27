package net.techtastic.witcheryrestitched.screen.slot;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class ModFuelSlot extends Slot {
    public ModFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (stack.isOf(Items.OAK_SAPLING) || stack.isOf(Items.OAK_WOOD) ||
                stack.isOf(Items.BIRCH_SAPLING) || stack.isOf(Items.BIRCH_WOOD) ||
                stack.isOf(Items.SPRUCE_SAPLING) || stack.isOf(Items.SPRUCE_WOOD) ||
                stack.isOf(Items.JUNGLE_SAPLING) || stack.isOf(Items.JUNGLE_WOOD) ||
                stack.isOf(Items.ACACIA_SAPLING) || stack.isOf(Items.ACACIA_WOOD) ||
                stack.isOf(Items.DARK_OAK_SAPLING) || stack.isOf(Items.DARK_OAK_WOOD)/* ||
                stack.isOf(ModItems.ROWAN_SAPLING) || stack.isOf(ModItems.ROWAN_WOOD) ||
                stack.isOf(ModItems.HAWTHORN_SAPLING) || stack.isOf(ModItems.HAWTHORN_WOOD) ||
                stack.isOf(ModItems.ALDER_SAPLING) || stack.isOf(ModItems.ALDER_WOOD)*/) {
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
