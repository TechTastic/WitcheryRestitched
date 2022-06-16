package net.techtastic.witcheryrestitched;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.techtastic.witcheryrestitched.block.ModBlocks;

public class WitcheryRestitchedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAST_IRON_OVEN, RenderLayer.getSolid());

    }
}
