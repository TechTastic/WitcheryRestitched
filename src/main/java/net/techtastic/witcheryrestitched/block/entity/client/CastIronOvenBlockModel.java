package net.techtastic.witcheryrestitched.block.entity.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.custom.CastIronOvenBlock;
import net.techtastic.witcheryrestitched.block.entity.CastIronOvenBlockEntity;
import net.techtastic.witcheryrestitched.item.custom.CastIronOvenItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CastIronOvenBlockModel extends AnimatedGeoModel<CastIronOvenBlockEntity> {
    @Override
    public Identifier getModelResource(CastIronOvenBlockEntity object) {
        return new Identifier(WitcheryRestitched.MOD_ID, "geo/cast_iron_oven.geo.json");
    }

    @Override
    public Identifier getTextureResource(CastIronOvenBlockEntity object) {
        return getTexture(object);
    }

    @Override
    public Identifier getAnimationResource(CastIronOvenBlockEntity animatable) {
        return new Identifier(WitcheryRestitched.MOD_ID, "animations/cast_iron_oven.animation.json");
    }

    public Identifier getTexture(CastIronOvenBlockEntity oven) {
        if (oven.isConsumingFuel(oven)) {
            return new Identifier(WitcheryRestitched.MOD_ID, "textures/block/cast_iron_oven_lit.png");
        }
        return new Identifier(WitcheryRestitched.MOD_ID, "textures/block/cast_iron_oven.png");
    }
}
