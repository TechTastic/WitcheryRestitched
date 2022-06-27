package net.techtastic.witcheryrestitched.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(WitcheryRestitched.MOD_ID, CastIronOvenRecipe.Serializer.ID),
                CastIronOvenRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(WitcheryRestitched.MOD_ID, CastIronOvenRecipe.Type.ID),
                CastIronOvenRecipe.Type.INSTANCE);
    }
}
