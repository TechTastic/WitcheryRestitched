package net.techtastic.witcheryrestitched.block.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.block.entity.CastIronOvenEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CastIronOvenRenderer extends GeoBlockRenderer<CastIronOvenEntity> {
    public CastIronOvenRenderer(BlockEntityRendererFactory.Context context) {
        super(new CastIronOvenModel());
    }

    @Override
    public RenderLayer getRenderType(CastIronOvenEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
