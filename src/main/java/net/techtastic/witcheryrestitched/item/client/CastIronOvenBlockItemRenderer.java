package net.techtastic.witcheryrestitched.item.client;

import net.techtastic.witcheryrestitched.item.custom.CastIronOvenItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CastIronOvenBlockItemRenderer extends GeoItemRenderer<CastIronOvenItem> {
    public CastIronOvenBlockItemRenderer() {
        super(new CastIronOvenBlockItemModel());
    }
}
