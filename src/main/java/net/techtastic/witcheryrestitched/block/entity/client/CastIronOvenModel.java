package net.techtastic.witcheryrestitched.block.entity.client;

import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.custom.CastIronOvenBlock;
import net.techtastic.witcheryrestitched.block.entity.CastIronOvenEntity;
import net.techtastic.witcheryrestitched.item.custom.CastIronOvenItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CastIronOvenModel extends AnimatedGeoModel<CastIronOvenEntity> {
    @Override
    public Identifier getModelResource(CastIronOvenEntity object) {
        return new Identifier(WitcheryRestitched.MOD_ID, "geo/cast_iron_oven.geo.json");
    }

    @Override
    public Identifier getTextureResource(CastIronOvenEntity object) {
        return new Identifier(WitcheryRestitched.MOD_ID, "textures/machines/cast_iron_oven.png");
    }

    @Override
    public Identifier getAnimationResource(CastIronOvenEntity animatable) {
        return new Identifier(WitcheryRestitched.MOD_ID, "animations/cast_iron_oven.animation.json");
    }
}
