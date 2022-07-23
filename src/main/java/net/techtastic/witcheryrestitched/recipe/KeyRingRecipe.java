package net.techtastic.witcheryrestitched.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KeyRingRecipe extends SpecialCraftingRecipe {
    public KeyRingRecipe(Identifier id) {
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
        if (itemStack2.isOf(ModItems.KEY_RING)) {
            ItemStack tmp = itemStack1;
            itemStack1 = itemStack2;
            itemStack2 = tmp;
        }

        return (itemStack1.isOf(ModItems.KEY_RING) && itemStack2.isOf(ModItems.KEY)) ||
                (itemStack1.isOf(ModItems.KEY) && itemStack2.isOf(ModItems.KEY));
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        List<ItemStack> itemStacks = getItems(inventory);
        ItemStack itemStack1 = itemStacks.get(0);
        ItemStack itemStack2 = itemStacks.get(1);
        if (itemStack1.isOf(ModItems.KEY) && itemStack2.isOf(ModItems.KEY)) {
            ItemStack ring = new ItemStack(ModItems.KEY_RING, 1);
            UUID key1 = itemStack1.getNbt().getUuid("witcheryrestitched:keyUuid");
            UUID key2 = itemStack2.getNbt().getUuid("witcheryrestitched:keyUuid");
            NbtCompound nbt = ring.getOrCreateNbt();
            nbt.putInt("witcheryrestitched:keyCount", 2);
            nbt.putUuid("witcheryrestitched:keyUuid1", key1);
            nbt.putUuid("witcheryrestitched:keyUuid2", key2);

            return ring;
        } else if (itemStack2.isOf(ModItems.KEY_RING)) {
            ItemStack tmp = itemStack1;
            itemStack1 = itemStack2;
            itemStack2 = tmp;
        }

        ItemStack ring = itemStack1.copy();

        NbtCompound nbt = ring.getNbt();
        nbt.putInt("witcheryrestitched:keyCount", nbt.getInt("witcheryrestitched:keyCount") + 1);
        nbt.putUuid("witcheryrestitched:keyUuid" + nbt.getInt("witcheryrestitched:keyCount"),
                itemStack2.getNbt().getUuid("witcheryrestitched:keyUuid"));

        return ring;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height > 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.KEY_RING_RECIPE;
    }
}
