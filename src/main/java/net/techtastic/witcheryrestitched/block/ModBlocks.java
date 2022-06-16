package net.techtastic.witcheryrestitched.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.custom.CastIronOvenBlock;
import net.techtastic.witcheryrestitched.item.ModItemGroup;

public class ModBlocks {

    public static final Block EMPTY_CHALICE = registerBlock("empty_chalice",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque().dynamicBounds()), ModItemGroup.WITCHERYRESTITCHED);
    public static final Block CAST_IRON_OVEN = registerBlock("cast_iron_oven",
            new CastIronOvenBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque()), ModItemGroup.WITCHERYRESTITCHED);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(WitcheryRestitched.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(WitcheryRestitched.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        WitcheryRestitched.LOGGER.info("Registering mod blocks for " + WitcheryRestitched.MOD_ID);
    }
}
