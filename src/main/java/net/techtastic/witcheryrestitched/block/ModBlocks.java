package net.techtastic.witcheryrestitched.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.custom.CastIronOvenBlock;
import net.techtastic.witcheryrestitched.block.custom.GarlicCropBlock;
import net.techtastic.witcheryrestitched.block.custom.MandrakeCropBlock;
import net.techtastic.witcheryrestitched.block.custom.TestAltarBlock;
import net.techtastic.witcheryrestitched.item.ModItemGroup;

public class ModBlocks {

    public static final Block EMPTY_CHALICE = registerBlock("empty_chalice",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque().dynamicBounds()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block CAST_IRON_OVEN = registerBlock("cast_iron_oven",
            new CastIronOvenBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block CROP_GARLIC = registerBlockWithoutBlockItem("crop_garlic",
            new GarlicCropBlock(FabricBlockSettings.of(Material.METAL)), ModItemGroup.WITCHERYRESTITCHED);

    public static final Block CROP_MANDRAKE_ROOT = registerBlockWithoutBlockItem("crop_mandrake_root",
            new MandrakeCropBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    //Test Blocks

    public static final Block TEST_ALTAR = registerBlock("test_altar",
            new TestAltarBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

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
