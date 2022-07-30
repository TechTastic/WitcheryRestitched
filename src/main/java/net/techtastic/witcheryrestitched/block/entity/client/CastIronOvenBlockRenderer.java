package net.techtastic.witcheryrestitched.block.entity.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.custom.CastIronOvenBlock;
import net.techtastic.witcheryrestitched.block.entity.CastIronOvenBlockEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CastIronOvenBlockRenderer extends GeoBlockRenderer<CastIronOvenBlockEntity> {
    public CastIronOvenBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new CastIronOvenBlockModel());
    }

    @Override
    public RenderLayer getRenderType(CastIronOvenBlockEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        //return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
