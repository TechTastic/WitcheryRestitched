package net.techtastic.witcheryrestitched.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.custom.*;
import net.techtastic.witcheryrestitched.item.ModItemGroup;
import net.techtastic.witcheryrestitched.world.feature.tree.AlderSaplingGenerator;
import net.techtastic.witcheryrestitched.world.feature.tree.RowanSaplingGenerator;

public class ModBlocks {

    public static final Block CHALICE = registerBlock("chalice",
            new ChaliceBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

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

        // HAWTHORN

    public static final Block HAWTHORN_LOG = registerBlock("hawthorn_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f).requiresTool()), ModItemGroup.WITCHERYRESTITCHED);


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
