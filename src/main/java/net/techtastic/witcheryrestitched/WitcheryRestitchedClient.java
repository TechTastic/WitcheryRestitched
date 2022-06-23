package net.techtastic.witcheryrestitched;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.item.custom.TaglockItem;
import net.techtastic.witcheryrestitched.util.ModModelPredicateProvider;

import static net.techtastic.witcheryrestitched.item.ModItems.TAGLOCK;

public class WitcheryRestitchedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAST_IRON_OVEN, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARLIC_CROP, RenderLayer.getCutout());

        ModModelPredicateProvider.registerModModels();

    }
}
