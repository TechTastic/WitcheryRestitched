package net.techtastic.witcheryrestitched.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.item.ModItems;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        registerTaglock(ModItems.TAGLOCK);
    }

    private static void registerTaglock(Item taglock) {
        FabricModelPredicateProviderRegistry.register(taglock, new Identifier("isfull"),
                (stack, world, entity, seed) -> {
                    return stack.getOrCreateNbt().getInt("witcheryresttiched:isfull");
                });
    }


}
