package net.techtastic.witcheryrestitched.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;

public class ModBlockEntities {
    public static BlockEntityType<CastIronOvenBlockEntity> CAST_IRON_OVEN;
    public static BlockEntityType<AltarBlockEntity> ALTAR;
    public static BlockEntityType<ArthanaBlockEntity> ARTHANA;
    public static BlockEntityType<DoorBlockEntity> ROWAN_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<DoorBlockEntity> ALDER_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<DoorBlockEntity> HAWTHORN_DOOR_BLOCK_ENTITY;

        public static void registerAllBlockEntities() {
            CAST_IRON_OVEN = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "cast_iron_oven"),
                    FabricBlockEntityTypeBuilder.create(CastIronOvenBlockEntity::new,
                            ModBlocks.CAST_IRON_OVEN).build(null));

            ALTAR = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "altar"),
                    FabricBlockEntityTypeBuilder.create(AltarBlockEntity::new,
                            ModBlocks.ALTAR).build(null));

            ARTHANA = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "arthana"),
                    FabricBlockEntityTypeBuilder.create(ArthanaBlockEntity::new,
                            ModBlocks.ARTHANA).build(null));

            ROWAN_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_door"),
                    FabricBlockEntityTypeBuilder.create(DoorBlockEntity::new,
                            ModBlocks.ROWAN_DOOR).build(null));

            ALDER_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_door"),
                    FabricBlockEntityTypeBuilder.create(DoorBlockEntity::new,
                            ModBlocks.ALDER_DOOR).build(null));

            HAWTHORN_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_door"),
                    FabricBlockEntityTypeBuilder.create(DoorBlockEntity::new,
                            ModBlocks.HAWTHORN_DOOR).build(null));
        }
}
