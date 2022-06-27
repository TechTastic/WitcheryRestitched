package net.techtastic.witcheryrestitched.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;

public class ModBlockEntities {
        public static BlockEntityType<CastIronOvenBlockEntity> CAST_IRON_OVEN;

        public static void registerAllBlockEntities() {
            CAST_IRON_OVEN = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "cast_iron_oven"),
                    FabricBlockEntityTypeBuilder.create(CastIronOvenBlockEntity::new,
                            ModBlocks.CAST_IRON_OVEN).build(null));
        }
}
