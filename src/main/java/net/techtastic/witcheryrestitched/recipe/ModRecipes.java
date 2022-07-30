package net.techtastic.witcheryrestitched.recipe;

import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModRecipes {

    public static final SpecialRecipeSerializer<KeyRingRecipe> KEY_RING_RECIPE = new SpecialRecipeSerializer<>(KeyRingRecipe::new);
    public static final SpecialRecipeSerializer<ChaliceFilledRecipe> CHALICE_FILLED_RECIPE = new SpecialRecipeSerializer<>(ChaliceFilledRecipe::new);

    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, CastIronOvenRecipe.Serializer.ID),
                CastIronOvenRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(WitcheryRestitched.MOD_ID, CastIronOvenRecipe.Type.ID),
                CastIronOvenRecipe.Type.INSTANCE);

        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, DistilleryRecipe.Serializer.ID),
                DistilleryRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(WitcheryRestitched.MOD_ID, DistilleryRecipe.Type.ID),
                DistilleryRecipe.Type.INSTANCE);

        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, CauldronRecipe.Serializer.ID),
                CauldronRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(WitcheryRestitched.MOD_ID, CauldronRecipe.Type.ID),
                CauldronRecipe.Type.INSTANCE);


        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, "key_ring"), KEY_RING_RECIPE);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, "chalice_filled"), CHALICE_FILLED_RECIPE);
    }
}
