package net.techtastic.witcheryrestitched.item.client;

import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.item.custom.CastIronOvenItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CastIronOvenItemModel extends AnimatedGeoModel<CastIronOvenItem> {
    @Override
    public Identifier getModelResource(CastIronOvenItem object) {
        return new Identifier(WitcheryRestitched.MOD_ID, "geo/cast_iron_oven.geo.json");
    }

    @Override
    public Identifier getTextureResource(CastIronOvenItem object) {
        return new Identifier(WitcheryRestitched.MOD_ID, "textures/item/cast_iron_oven.png");
    }

    @Override
    public Identifier getAnimationResource(CastIronOvenItem animatable) {
        return new Identifier(WitcheryRestitched.MOD_ID, "animations/cast_iron_oven.animation.json");
    }
}
