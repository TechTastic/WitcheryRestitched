package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;

public class KeyRingItem extends Item {
    public KeyRingItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();

        if (!nbt.contains("witcheryrestitched:keyCount")) {
            nbt.putInt("witcheryrestitched:keyCount", 0);
        }

        return super.onStackClicked(stack, slot, clickType, player);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        NbtCompound nbt = stack.getOrCreateNbt();

        if (!nbt.contains("witcheryrestitched:keyCount")) {
            nbt.putInt("witcheryrestitched:keyCount", 0);
        }

        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();

        if (!nbt.contains("witcheryrestitched:keyCount")) {
            nbt.putInt("witcheryrestitched:keyCount", 0);
        }

        super.onCraft(stack, world, player);
    }
}
