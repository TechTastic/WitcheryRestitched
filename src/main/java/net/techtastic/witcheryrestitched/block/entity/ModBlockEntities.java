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
    public static BlockEntityType<FenceGateBlockEntity> ROWAN_FENCE_GATE_BLOCK_ENTITY;
    public static BlockEntityType<ButtonBlockEntity> ROWAN_BUTTON_BLOCK_ENTITY;
    public static BlockEntityType<PlateBlockEntity> ROWAN_PLATE_BLOCK_ENTITY;
    public static BlockEntityType<TrapdoorBlockEntity> ROWAN_TRAPDOOR_BLOCK_ENTITY;

    public static BlockEntityType<DoorBlockEntity> ALDER_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<FenceGateBlockEntity> ALDER_FENCE_GATE_BLOCK_ENTITY;
    public static BlockEntityType<ButtonBlockEntity> ALDER_BUTTON_BLOCK_ENTITY;
    public static BlockEntityType<PlateBlockEntity> ALDER_PLATE_BLOCK_ENTITY;
    public static BlockEntityType<TrapdoorBlockEntity> ALDER_TRAPDOOR_BLOCK_ENTITY;

    public static BlockEntityType<DoorBlockEntity> HAWTHORN_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<FenceGateBlockEntity> HAWTHORN_FENCE_GATE_BLOCK_ENTITY;
    public static BlockEntityType<ButtonBlockEntity> HAWTHORN_BUTTON_BLOCK_ENTITY;
    public static BlockEntityType<PlateBlockEntity> HAWTHORN_PLATE_BLOCK_ENTITY;
    public static BlockEntityType<TrapdoorBlockEntity> HAWTHORN_TRAPDOOR_BLOCK_ENTITY;

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

            //ROWAN

            ROWAN_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_door"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new DoorBlockEntity(ROWAN_DOOR_BLOCK_ENTITY, pos, state),
                            ModBlocks.ROWAN_DOOR).build(null));

            ROWAN_FENCE_GATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_fence_gate"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new FenceGateBlockEntity(ROWAN_FENCE_GATE_BLOCK_ENTITY, pos, state),
                            ModBlocks.ROWAN_FENCE_GATE).build(null));

            ROWAN_BUTTON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_button"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new ButtonBlockEntity(ROWAN_BUTTON_BLOCK_ENTITY, pos, state),
                            ModBlocks.ROWAN_BUTTON).build(null));

            ROWAN_PLATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_pressure_plate"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new PlateBlockEntity(ROWAN_PLATE_BLOCK_ENTITY, pos, state),
                            ModBlocks.ROWAN_PRESSURE_PLATE).build(null));

            ROWAN_TRAPDOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_trapdoor"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new TrapdoorBlockEntity(ROWAN_TRAPDOOR_BLOCK_ENTITY, pos, state),
                            ModBlocks.ROWAN_TRAPDOOR).build(null));

            // ALDER

            ALDER_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_door"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new DoorBlockEntity(ROWAN_DOOR_BLOCK_ENTITY, pos, state),
                            ModBlocks.ALDER_DOOR).build(null));

            ALDER_FENCE_GATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_fence_gate"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new FenceGateBlockEntity(ALDER_FENCE_GATE_BLOCK_ENTITY, pos, state),
                            ModBlocks.ALDER_FENCE_GATE).build(null));

            ALDER_BUTTON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_button"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new ButtonBlockEntity(ALDER_BUTTON_BLOCK_ENTITY, pos, state),
                            ModBlocks.ALDER_BUTTON).build(null));

            ALDER_PLATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_pressure_plate"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new PlateBlockEntity(ALDER_PLATE_BLOCK_ENTITY, pos, state),
                            ModBlocks.ALDER_PRESSURE_PLATE).build(null));

            ALDER_TRAPDOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_trapdoor"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new TrapdoorBlockEntity(ALDER_TRAPDOOR_BLOCK_ENTITY, pos, state),
                            ModBlocks.ALDER_TRAPDOOR).build(null));

            // HAWTHORN

            HAWTHORN_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_door"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new DoorBlockEntity(ROWAN_DOOR_BLOCK_ENTITY, pos, state),
                            ModBlocks.HAWTHORN_DOOR).build(null));

            HAWTHORN_FENCE_GATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_fence_gate"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new FenceGateBlockEntity(HAWTHORN_FENCE_GATE_BLOCK_ENTITY, pos, state),
                            ModBlocks.HAWTHORN_FENCE_GATE).build(null));

            HAWTHORN_BUTTON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_button"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new ButtonBlockEntity(HAWTHORN_BUTTON_BLOCK_ENTITY, pos, state),
                            ModBlocks.HAWTHORN_BUTTON).build(null));

            HAWTHORN_PLATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_pressure_plate"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new PlateBlockEntity(HAWTHORN_PLATE_BLOCK_ENTITY, pos, state),
                            ModBlocks.HAWTHORN_PRESSURE_PLATE).build(null));

            HAWTHORN_TRAPDOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_trapdoor"),
                    FabricBlockEntityTypeBuilder.create((pos, state) -> new TrapdoorBlockEntity(HAWTHORN_TRAPDOOR_BLOCK_ENTITY, pos, state),
                            ModBlocks.HAWTHORN_TRAPDOOR).build(null));
        }
}
