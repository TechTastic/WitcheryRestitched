package net.techtastic.witcheryrestitched.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.item.ModItems;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        registerTaglock(ModItems.TAGLOCK);
        registerToeOfFrog(ModItems.TOE_OF_FROG);
        registerChalice(ModBlocks.CHALICE.asItem());
        registerKeyRing(ModItems.KEY_RING);
    }

    private static void registerTaglock(Item taglock) {
        FabricModelPredicateProviderRegistry.register(taglock, new Identifier("isfull"),
                (stack, world, entity, seed) -> {
                    return stack.getOrCreateNbt().getInt("witcheryrestitched:isfull");
                });
    }

    private static void registerChalice(Item chalice) {
        FabricModelPredicateProviderRegistry.register(chalice, new Identifier("soup"),
                (stack, world, entity, seed) -> {
                    return stack.getOrCreateNbt().getInt("witcheryrestitched:soup");
                });
    }

    private static void registerKeyRing(Item keyRing) {
        FabricModelPredicateProviderRegistry.register(keyRing, new Identifier("keys"),
                (stack, world, entity, seed) -> {
                    return 0.1F * stack.getOrCreateNbt().getInt("witcheryrestitched:keyCount");
                });
    }

    private static void registerToeOfFrog(Item toe) {
        FabricModelPredicateProviderRegistry.register(toe, new Identifier("fv"),
                (stack, world, entity, seed) -> {
                    return stack.getOrCreateNbt().getInt("witcheryrestitched:frogvariant");
                });
    }


}
