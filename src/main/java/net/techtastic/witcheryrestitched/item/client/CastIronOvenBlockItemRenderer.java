package net.techtastic.witcheryrestitched.item.client;

import net.techtastic.witcheryrestitched.item.custom.CastIronOvenItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CastIronOvenBlockItemRenderer extends GeoItemRenderer<CastIronOvenItem> {
    public CastIronOvenBlockItemRenderer(AnimatedGeoModel<CastIronOvenItem> modelProvider) {
        super(new CastIronOvenItemModel());
    }
}
