package net.techtastic.witcheryrestitched.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.alder.*;
import net.techtastic.witcheryrestitched.block.entity.hawthorn.*;
import net.techtastic.witcheryrestitched.block.entity.rowan.*;

public class ModBlockEntities {
    public static BlockEntityType<CastIronOvenBlockEntity> CAST_IRON_OVEN;
    public static BlockEntityType<AltarBlockEntity> ALTAR;
    public static BlockEntityType<ArthanaBlockEntity> ARTHANA;

    // ALTAR SINKS

    public static BlockEntityType<AltarPowerSinkEntity> ALTAR_POWER_SINK_ENTITY;
    public static BlockEntityType<DistilleryBlockEntity> DISTILLERY;
    public static BlockEntityType<CauldronBlockEntity> CAULDRON;

    // WOOD STUFFS

    public static BlockEntityType<RowanDoorBlockEntity> ROWAN_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<RowanFenceGateBlockEntity> ROWAN_FENCE_GATE_BLOCK_ENTITY;
    public static BlockEntityType<RowanButtonBlockEntity> ROWAN_BUTTON_BLOCK_ENTITY;
    public static BlockEntityType<RowanPlateBlockEntity> ROWAN_PLATE_BLOCK_ENTITY;
    public static BlockEntityType<RowanTrapdoorBlockEntity> ROWAN_TRAPDOOR_BLOCK_ENTITY;

    public static BlockEntityType<AlderDoorBlockEntity> ALDER_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<AlderFenceGateBlockEntity> ALDER_FENCE_GATE_BLOCK_ENTITY;
    public static BlockEntityType<AlderButtonBlockEntity> ALDER_BUTTON_BLOCK_ENTITY;
    public static BlockEntityType<AlderPlateBlockEntity> ALDER_PLATE_BLOCK_ENTITY;
    public static BlockEntityType<AlderTrapdoorBlockEntity> ALDER_TRAPDOOR_BLOCK_ENTITY;

    public static BlockEntityType<HawthornDoorBlockEntity> HAWTHORN_DOOR_BLOCK_ENTITY;
    public static BlockEntityType<HawthornFenceGateBlockEntity> HAWTHORN_FENCE_GATE_BLOCK_ENTITY;
    public static BlockEntityType<HawthornButtonBlockEntity> HAWTHORN_BUTTON_BLOCK_ENTITY;
    public static BlockEntityType<HawthornPlateBlockEntity> HAWTHORN_PLATE_BLOCK_ENTITY;
    public static BlockEntityType<HawthornTrapdoorBlockEntity> HAWTHORN_TRAPDOOR_BLOCK_ENTITY;

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

            // ALTAR POWER SINKS

            ALTAR_POWER_SINK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "altar_sink_testing"),
                    FabricBlockEntityTypeBuilder.create(AltarPowerSinkEntity::new,
                            ModBlocks.ALTAR_SINK_TESTING).build(null));

            DISTILLERY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "distillery"),
                    FabricBlockEntityTypeBuilder.create(DistilleryBlockEntity::new,
                            ModBlocks.DISTILLERY).build(null));

            CAULDRON = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "cauldron"),
                    FabricBlockEntityTypeBuilder.create(CauldronBlockEntity::new,
                            ModBlocks.CAULDRON).build(null));

            // WOOD STUFFS
                // ROWAN

            ROWAN_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_door"),
                    FabricBlockEntityTypeBuilder.create(RowanDoorBlockEntity::new, ModBlocks.ROWAN_DOOR).build(null));

            ROWAN_FENCE_GATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_fence_gate"),
                    FabricBlockEntityTypeBuilder.create(RowanFenceGateBlockEntity::new, ModBlocks.ROWAN_FENCE_GATE).build(null));

            ROWAN_BUTTON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_button"),
                    FabricBlockEntityTypeBuilder.create(RowanButtonBlockEntity::new, ModBlocks.ROWAN_BUTTON).build(null));

            ROWAN_PLATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_pressure_plate"),
                    FabricBlockEntityTypeBuilder.create(RowanPlateBlockEntity::new, ModBlocks.ROWAN_PRESSURE_PLATE).build(null));

            ROWAN_TRAPDOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "rowan_trapdoor"),
                    FabricBlockEntityTypeBuilder.create(RowanTrapdoorBlockEntity::new, ModBlocks.ROWAN_TRAPDOOR).build(null));

                // ALDER

            ALDER_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_door"),
                    FabricBlockEntityTypeBuilder.create(AlderDoorBlockEntity::new, ModBlocks.ALDER_DOOR).build(null));

            ALDER_FENCE_GATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_fence_gate"),
                    FabricBlockEntityTypeBuilder.create(AlderFenceGateBlockEntity::new, ModBlocks.ALDER_FENCE_GATE).build(null));

            ALDER_BUTTON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_button"),
                    FabricBlockEntityTypeBuilder.create(AlderButtonBlockEntity::new, ModBlocks.ALDER_BUTTON).build(null));

            ALDER_PLATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_pressure_plate"),
                    FabricBlockEntityTypeBuilder.create(AlderPlateBlockEntity::new, ModBlocks.ALDER_PRESSURE_PLATE).build(null));

            ALDER_TRAPDOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "alder_trapdoor"),
                    FabricBlockEntityTypeBuilder.create(AlderTrapdoorBlockEntity::new, ModBlocks.ALDER_TRAPDOOR).build(null));

                // HAWTHORN

            HAWTHORN_DOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_door"),
                    FabricBlockEntityTypeBuilder.create(HawthornDoorBlockEntity::new, ModBlocks.HAWTHORN_DOOR).build(null));

            HAWTHORN_FENCE_GATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_fence_gate"),
                    FabricBlockEntityTypeBuilder.create(HawthornFenceGateBlockEntity::new, ModBlocks.HAWTHORN_FENCE_GATE).build(null));

            HAWTHORN_BUTTON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_button"),
                    FabricBlockEntityTypeBuilder.create(HawthornButtonBlockEntity::new, ModBlocks.HAWTHORN_BUTTON).build(null));

            HAWTHORN_PLATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_pressure_plate"),
                    FabricBlockEntityTypeBuilder.create(HawthornPlateBlockEntity::new, ModBlocks.HAWTHORN_PRESSURE_PLATE).build(null));

            HAWTHORN_TRAPDOOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(WitcheryRestitched.MOD_ID, "hawthorn_trapdoor"),
                    FabricBlockEntityTypeBuilder.create(HawthornTrapdoorBlockEntity::new, ModBlocks.HAWTHORN_TRAPDOOR).build(null));
        }
}
