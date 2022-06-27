package net.techtastic.witcheryrestitched.screen.slot;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;
import net.techtastic.witcheryrestitched.item.ModItems;

public class ModInputSlot extends Slot {
    public ModInputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(Items.OAK_SAPLING) || stack.isOf(Items.OAK_WOOD) ||
                stack.isOf(Items.BIRCH_SAPLING) || stack.isOf(Items.BIRCH_WOOD) ||
                stack.isOf(Items.SPRUCE_SAPLING) || stack.isOf(Items.SPRUCE_WOOD) ||
                stack.isOf(Items.JUNGLE_SAPLING) || stack.isOf(Items.JUNGLE_WOOD) ||
                stack.isOf(Items.ACACIA_SAPLING) || stack.isOf(Items.ACACIA_WOOD) ||
                stack.isOf(Items.DARK_OAK_SAPLING) || stack.isOf(Items.DARK_OAK_WOOD) ||
                /*stack.isOf(ModItems.ROWAN_SAPLING) || stack.isOf(ModItems.ROWAN_WOOD) ||
                stack.isOf(ModItems.HAWTHORN_SAPLING) || stack.isOf(ModItems.HAWTHORN_WOOD) ||
                stack.isOf(ModItems.ALDER_SAPLING) || stack.isOf(ModItems.ALDER_WOOD) ||*/
                stack.getItem().getDefaultStack().isIn(ConventionalItemTags.FOODS);
    }
}
