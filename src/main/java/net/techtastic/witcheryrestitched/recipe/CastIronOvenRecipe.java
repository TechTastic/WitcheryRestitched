package net.techtastic.witcheryrestitched.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.awt.*;

public class CastIronOvenRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final ItemStack secondaryOutput;
    private final DefaultedList<Ingredient> recipeItems;

    public CastIronOvenRecipe(Identifier id, ItemStack output, ItemStack secondaryOutput, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.secondaryOutput = secondaryOutput;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) { return false; }

        if (recipeItems.get(0).test(inventory.getStack(1))) {
            return recipeItems.get(0).test(inventory.getStack(1));
        }

        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    public ItemStack getSecondaryOutput() {
        return secondaryOutput.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CastIronOvenRecipe> {
        private Type() { };
        public static final Type INSTANCE = new Type();
        public static final String ID = "cast_iron_oven";
    }

    public static class Serializer implements RecipeSerializer<CastIronOvenRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "cast_iron_oven";
        //recipe name given in JSON file

        @Override
        public CastIronOvenRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            ItemStack secondaryOutput = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "secondaryoutput"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CastIronOvenRecipe(id, output, secondaryOutput, inputs);
        }

        @Override
        public CastIronOvenRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            ItemStack secondaryOutput = buf.readItemStack();
            return new CastIronOvenRecipe(id, output, secondaryOutput, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, CastIronOvenRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
            buf.writeItemStack(recipe.getSecondaryOutput());
        }
    }
}
