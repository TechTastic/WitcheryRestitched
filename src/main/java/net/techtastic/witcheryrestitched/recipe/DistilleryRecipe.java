package net.techtastic.witcheryrestitched.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class DistilleryRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final DefaultedList<Ingredient> recipeItems;
    private final DefaultedList<Ingredient> outputItems;
    private final int jarCount;

    public DistilleryRecipe(Identifier id, DefaultedList<Ingredient> recipeItems, DefaultedList<Ingredient> outputItems,int jarCount) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.outputItems = outputItems;
        this.jarCount = jarCount;
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

    @Override
    public ItemStack getOutput() {
        return ItemStack.EMPTY;
    } // Was Needed

    public ItemStack getFirstOutput() {
        if (outputItems.size() >= 1) {
            return outputItems.get(0).getMatchingStacks()[0];
        }

        return ItemStack.EMPTY;
    }
    public ItemStack getSecondOutput() {
        if (outputItems.size() >= 2) {
            return outputItems.get(1).getMatchingStacks()[0];
        }
        return ItemStack.EMPTY;
    }
    public ItemStack getThirdOutput() {
        if (outputItems.size() >= 3) {
            return outputItems.get(2).getMatchingStacks()[0];
        }
        return ItemStack.EMPTY;
    }
    public ItemStack getFourthOutput() {
        if (outputItems.size() == 4) {
            return outputItems.get(3).getMatchingStacks()[0];
        }
        return ItemStack.EMPTY;
    }

    public DefaultedList<Ingredient> getResults() {
        return outputItems;
    }

    public int getJarCount() {
        return jarCount;
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

    public static class Type implements RecipeType<DistilleryRecipe> {
        private Type() { };
        public static final Type INSTANCE = new Type();
        public static final String ID = "distillery";
    }

    public static class Serializer implements RecipeSerializer<DistilleryRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "distillery";
        //recipe name given in JSON file

        @Override
        public DistilleryRecipe read(Identifier id, JsonObject json) {
            int jarCount = JsonHelper.getInt(json, "jarCount");

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            JsonArray outputs = JsonHelper.getArray(json, "results");
            DefaultedList<Ingredient> results = DefaultedList.ofSize(outputs.size(), Ingredient.EMPTY);

            for (int i = 0; i < results.size(); i++) {
                Ingredient ing = Ingredient.fromJson(outputs.get(i));

                if (outputs.get(i).getAsJsonObject().has("count")) {
                    ItemStack stack = ing.getMatchingStacks()[0];
                    stack.setCount(outputs.get(i).getAsJsonObject().get("count").getAsInt());

                    ing = Ingredient.ofStacks(stack);
                }

                results.set(i, ing);
            }


            return new DistilleryRecipe(id, inputs, results, jarCount);
        }

        @Override
        public DistilleryRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            DefaultedList<Ingredient> results = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < results.size(); i++) {
                results.set(i, Ingredient.fromPacket(buf));
            }

            int jarCount = buf.readInt();

            return new DistilleryRecipe(id, inputs, results, jarCount);
        }

        @Override
        public void write(PacketByteBuf buf, DistilleryRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }

            buf.writeInt(recipe.getResults().size());
            for (Ingredient ing : recipe.getResults()) {
                ing.write(buf);
            }

            buf.writeInt(recipe.getJarCount());
        }
    }
}
