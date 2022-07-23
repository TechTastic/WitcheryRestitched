package net.techtastic.witcheryrestitched.recipe;

import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModRecipes {

    public static final SpecialRecipeSerializer<KeyRingRecipe> KEY_RING_RECIPE = new SpecialRecipeSerializer<>(KeyRingRecipe::new);

    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, CastIronOvenRecipe.Serializer.ID),
                CastIronOvenRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(WitcheryRestitched.MOD_ID, CastIronOvenRecipe.Type.ID),
                CastIronOvenRecipe.Type.INSTANCE);


        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, "key_ring"), KEY_RING_RECIPE);
    }
}
