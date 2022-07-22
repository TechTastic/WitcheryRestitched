package net.techtastic.witcheryrestitched.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.*;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.custom.*;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.item.ModItemGroup;
import net.techtastic.witcheryrestitched.world.feature.tree.AlderSaplingGenerator;
import net.techtastic.witcheryrestitched.world.feature.tree.HawthornSaplingGenerator;
import net.techtastic.witcheryrestitched.world.feature.tree.RowanSaplingGenerator;

public class ModBlocks {

    public static final Block PENTACLE = registerBlock("pentacle",
            new PentacleBlock(FabricBlockSettings.of(Material.METAL).breakInstantly().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block INFINITY_EGG = registerBlock("infinity_egg",
            new Block(FabricBlockSettings.of(Material.METAL).strength(2f).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block CANDELABRA = registerBlock("candelabra",
            new CandelabraBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().nonOpaque()
                    .luminance(state -> state.get(CandelabraBlock.LIT) ? 15 : 0), ParticleTypes.FLAME), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block CHALICE = registerBlockWithoutBlockItem("chalice",
            new ChaliceBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ARTHANA = registerBlockWithoutBlockItem("arthana",
            new ArthanaBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block CAST_IRON_OVEN = registerBlock("cast_iron_oven",
            new CastIronOvenBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    // CROPS

    public static final Block CROP_GARLIC = registerBlockWithoutBlockItem("crop_garlic",
            new GarlicCropBlock(FabricBlockSettings.of(Material.METAL)), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block CROP_MANDRAKE_ROOT = registerBlockWithoutBlockItem("crop_mandrake_root",
            new MandrakeCropBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    // ALTAR BLOCKS
    public static final Block ALTAR = registerBlock("altar_block",
            new AltarBlock(FabricBlockSettings.of(Material.STONE).strength(3f).requiresTool() .nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    // TREES

        // ROWAN

    public static final Block ROWAN_LOG = registerBlock("rowan_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block ROWAN_WOOD = registerBlock("rowan_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block STRIPPED_ROWAN_LOG = registerBlock("stripped_rowan_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block STRIPPED_ROWAN_WOOD = registerBlock("stripped_rowan_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_PLANKS = registerBlock("rowan_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_LEAVES = registerBlock("rowan_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_SAPLING = registerBlock("rowan_sapling",
            new ModSaplingBlock(new RowanSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_PRESSURE_PLATE = registerBlock("rowan_pressure_plate",
            new KeyedPressurePlateBlock(ModBlockEntities.ROWAN_PLATE_BLOCK_ENTITY, PressurePlateBlockWithEntity.ActivationRule.MOBS,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_BUTTON = registerBlock("rowan_button",
            new KeyedButtonBlock(ModBlockEntities.ROWAN_BUTTON_BLOCK_ENTITY,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool().noCollision()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_FENCE = registerBlock("rowan_fence",
            new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_FENCE_GATE = registerBlock("rowan_fence_gate",
            new KeyedFenceGate(ModBlockEntities.ROWAN_FENCE_GATE_BLOCK_ENTITY,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_STAIRS = registerBlock("rowan_stairs",
            new StairsBlock(Blocks.OAK_STAIRS.getDefaultState(),
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_SLAB = registerBlock("rowan_slab",
            new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_DOOR = registerBlock("rowan_door",
            new KeyedDoor(ModBlockEntities.ROWAN_DOOR_BLOCK_ENTITY,
                    FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ROWAN_TRAPDOOR = registerBlock("rowan_trapdoor",
            new KeyedTrapdoor(ModBlockEntities.ROWAN_TRAPDOOR_BLOCK_ENTITY,
                    FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

        // ALDER

    public static final Block ALDER_LOG = registerBlock("alder_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_WOOD = registerBlock("alder_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block STRIPPED_ALDER_LOG = registerBlock("stripped_alder_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block STRIPPED_ALDER_WOOD = registerBlock("stripped_alder_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_PLANKS = registerBlock("alder_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_LEAVES = registerBlock("alder_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_SAPLING = registerBlock("alder_sapling",
            new ModSaplingBlock(new AlderSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_PRESSURE_PLATE = registerBlock("alder_pressure_plate",
            new KeyedPressurePlateBlock(ModBlockEntities.ALDER_PLATE_BLOCK_ENTITY, PressurePlateBlockWithEntity.ActivationRule.MOBS,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_BUTTON = registerBlock("alder_button",
            new KeyedButtonBlock(ModBlockEntities.ALDER_BUTTON_BLOCK_ENTITY,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool().noCollision()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_FENCE = registerBlock("alder_fence",
            new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_FENCE_GATE = registerBlock("alder_fence_gate",
            new KeyedFenceGate(ModBlockEntities.ALDER_FENCE_GATE_BLOCK_ENTITY,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_STAIRS = registerBlock("alder_stairs",
            new StairsBlock(Blocks.OAK_STAIRS.getDefaultState(),
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_SLAB = registerBlock("alder_slab",
            new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_DOOR = registerBlock("alder_door",
            new KeyedDoor(ModBlockEntities.ALDER_DOOR_BLOCK_ENTITY,
                    FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block ALDER_TRAPDOOR = registerBlock("alder_trapdoor",
            new KeyedTrapdoor(ModBlockEntities.ALDER_TRAPDOOR_BLOCK_ENTITY,
                    FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

      // HAWTHORN

    public static final Block HAWTHORN_LOG = registerBlock("hawthorn_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block HAWTHORN_WOOD = registerBlock("hawthorn_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block STRIPPED_HAWTHORN_LOG = registerBlock("stripped_hawthorn_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block STRIPPED_HAWTHORN_WOOD = registerBlock("stripped_hawthorn_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_PLANKS = registerBlock("hawthorn_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_LEAVES = registerBlock("hawthorn_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            new ModSaplingBlock(new HawthornSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_PRESSURE_PLATE = registerBlock("hawthorn_pressure_plate",
            new KeyedPressurePlateBlock(ModBlockEntities.HAWTHORN_PLATE_BLOCK_ENTITY, PressurePlateBlockWithEntity.ActivationRule.MOBS,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_BUTTON = registerBlock("hawthorn_button",
            new KeyedButtonBlock(ModBlockEntities.HAWTHORN_BUTTON_BLOCK_ENTITY,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool().noCollision()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_FENCE = registerBlock("hawthorn_fence",
            new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_FENCE_GATE = registerBlock("hawthorn_fence_gate",
            new KeyedFenceGate(ModBlockEntities.HAWTHORN_FENCE_GATE_BLOCK_ENTITY,
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_STAIRS = registerBlock("hawthorn_stairs",
            new StairsBlock(Blocks.OAK_STAIRS.getDefaultState(),
                    FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_SLAB = registerBlock("hawthorn_slab",
            new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_DOOR = registerBlock("hawthorn_door",
            new KeyedDoor(ModBlockEntities.HAWTHORN_DOOR_BLOCK_ENTITY,
                    FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block HAWTHORN_TRAPDOOR = registerBlock("hawthorn_trapdoor",
            new KeyedTrapdoor(ModBlockEntities.HAWTHORN_TRAPDOOR_BLOCK_ENTITY,
                    FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);



    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(WitcheryRestitched.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(WitcheryRestitched.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.BLOCK, new Identifier(WitcheryRestitched.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        WitcheryRestitched.LOGGER.info("Registering mod blocks for " + WitcheryRestitched.MOD_ID);
    }
}
