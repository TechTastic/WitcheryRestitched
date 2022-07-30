package net.techtastic.witcheryrestitched;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.client.CastIronOvenBlockRenderer;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.item.client.CastIronOvenBlockItemRenderer;
import net.techtastic.witcheryrestitched.screen.AltarScreen;
import net.techtastic.witcheryrestitched.screen.CastIronOvenScreen;
import net.techtastic.witcheryrestitched.screen.DistilleryScreen;
import net.techtastic.witcheryrestitched.screen.ModScreenHandlers;
import net.techtastic.witcheryrestitched.util.ModModelPredicateProvider;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class WitcheryRestitchedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CROP_GARLIC, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CROP_MANDRAKE_ROOT, RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAST_IRON_OVEN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHALICE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CANDELABRA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFINITY_EGG, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_TRAPDOOR, RenderLayer.getCutout());

        ModModelPredicateProvider.registerModModels();

        ScreenRegistry.register(ModScreenHandlers.CAST_IRON_OVEN_SCREEN_HANDLER, CastIronOvenScreen::new);
        ScreenRegistry.register(ModScreenHandlers.ALTAR_SCREEN_HANDLER, AltarScreen::new);
        ScreenRegistry.register(ModScreenHandlers.DISTILLERY_SCREEN_HANDLER, DistilleryScreen::new);

        // CAST IRON OVEN

        GeoItemRenderer.registerItemRenderer(ModItems.CAST_IRON_OVEN_ITEM, new CastIronOvenBlockItemRenderer());
        BlockEntityRendererRegistry.register(ModBlockEntities.CAST_IRON_OVEN, CastIronOvenBlockRenderer::new);
    }
}
