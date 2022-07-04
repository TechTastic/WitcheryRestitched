package net.techtastic.witcheryrestitched.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.techtastic.witcheryrestitched.util.ModTags;

public class ModInputSlot extends Slot {
    public ModInputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(ModTags.Items.CAST_IRON_OVEN_INPUT);
    }
}
