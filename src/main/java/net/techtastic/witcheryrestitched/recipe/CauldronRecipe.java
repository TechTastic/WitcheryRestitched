package net.techtastic.witcheryrestitched.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class CauldronRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final DefaultedList<Ingredient> recipeItems;
    private final ItemStack output;
    private final int power;

    public CauldronRecipe(Identifier id, DefaultedList<Ingredient> recipeItems, ItemStack output, int power) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.output = output;
        this.power = power;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) { return false; }


        for (int i = 0; i < recipeItems.size(); i++) {
            if (!recipeItems.get(i).test(inventory.getStack(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return ItemStack.EMPTY;
    } // Was Needed

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    public int getPower() {return power;}

    @Override
    public ItemStack getOutput() {
        return output;
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

    public static class Type implements RecipeType<CauldronRecipe> {
        private Type() { };
        public static final Type INSTANCE = new Type();
        public static final String ID = "cauldron";
    }

    public static class Serializer implements RecipeSerializer<CauldronRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "cauldron";
        //recipe name given in JSON file

        @Override
        public CauldronRecipe read(Identifier id, JsonObject json) {
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack result = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonObject output = JsonHelper.getObject(json,"output");
            if (output.has("count")) {
                result.setCount(output.get("count").getAsInt());
            }

            int power = JsonHelper.getInt(json, "power", 0);

            return new CauldronRecipe(id, inputs, result, power);
        }

        @Override
        public CauldronRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack result = buf.readItemStack();

            int power = buf.readInt();

            return new CauldronRecipe(id, inputs, result, power);
        }

        @Override
        public void write(PacketByteBuf buf, CauldronRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }

            buf.writeItemStack(recipe.getOutput());

            buf.writeInt(recipe.getPower());
        }
    }
}
