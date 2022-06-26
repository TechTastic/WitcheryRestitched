package net.techtastic.witcheryrestitched;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.client.CastIronOvenRenderer;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.item.client.CastIronOvenBlockItemRenderer;
import net.techtastic.witcheryrestitched.item.custom.TaglockItem;
import net.techtastic.witcheryrestitched.util.ModModelPredicateProvider;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import static net.techtastic.witcheryrestitched.item.ModItems.TAGLOCK;

public class WitcheryRestitchedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAST_IRON_OVEN, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CROP_GARLIC, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CROP_MANDRAKE_ROOT, RenderLayer.getCutout());

        ModModelPredicateProvider.registerModModels();

        GeoItemRenderer.registerItemRenderer(ModItems.CAST_IRON_OVEN_ITEM, new CastIronOvenBlockItemRenderer());
        BlockEntityRendererRegistry.register(ModBlockEntities.CAST_IRON_OVEN_ENTITY, CastIronOvenRenderer::new);

    }
}
