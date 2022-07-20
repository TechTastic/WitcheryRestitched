package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;

public class ChaliceBlockItem extends AliasedBlockItem {
    public ChaliceBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("witcheryrestitched:soup")) {
            nbt.putInt("witcheryrestitched:soup", 0);
        }

        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("witcheryrestitched:soup")) {
            nbt.putInt("witcheryrestitched:soup", 0);
        }

        return super.onStackClicked(stack, slot, clickType, player);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("witcheryrestitched:soup")) {
            nbt.putInt("witcheryrestitched:soup", 0);
        }

        super.onCraft(stack, world, player);
    }
}
