package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;

public class

ToeOfFrogItem extends Item {
    public ToeOfFrogItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!stack.hasNbt()) {
            NbtCompound tof = new NbtCompound();
            tof.putInt("witcheryrestitched:frogvariant", 2);
            stack.setNbt(tof);
        }

        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (!stack.hasNbt()) {
            NbtCompound tof = new NbtCompound();
            tof.putInt("witcheryrestitched:frogvariant", 2);
            stack.setNbt(tof);
        }

        return super.onStackClicked(stack, slot, clickType, player);
    }
}
