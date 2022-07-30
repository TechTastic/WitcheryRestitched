package net.techtastic.witcheryrestitched.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChaliceFilledRecipe extends SpecialCraftingRecipe {
    public ChaliceFilledRecipe(Identifier id) {
        super(id);
    }

    private List<ItemStack> getItems(CraftingInventory inventory) {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                itemStacks.add(itemStack);
            }
        }
        return itemStacks;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        List<ItemStack> itemStacks = getItems(inventory);
        if (itemStacks.size() != 2) return false;
        ItemStack itemStack1 = itemStacks.get(0);
        ItemStack itemStack2 = itemStacks.get(1);
        if (itemStack2.isOf(ModItems.CHALICE)) {
            ItemStack tmp = itemStack1;
            itemStack1 = itemStack2;
            itemStack2 = tmp;
        }

        if (itemStack1.getOrCreateNbt().getInt("witcheryrestitched:soup") == 0) {
            return itemStack1.isOf(ModItems.CHALICE) && itemStack2.isOf(ModItems.REDSTONE_SOUP);
        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        List<ItemStack> itemStacks = getItems(inventory);
        ItemStack chalice = itemStacks.get(0);
        ItemStack redstoneSoup = itemStacks.get(1);
        if (redstoneSoup.isOf(ModItems.CHALICE)) {
            ItemStack tmp = chalice;
            chalice = redstoneSoup;
            redstoneSoup = tmp;
        }

        ItemStack filledChalice = chalice.copy();
        filledChalice.getOrCreateNbt().putInt("witcheryrestitched:soup", 1);

        return filledChalice;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height > 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CHALICE_FILLED_RECIPE;
    }
}
