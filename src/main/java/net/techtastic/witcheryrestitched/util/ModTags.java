package net.techtastic.witcheryrestitched.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModTags {
    public static class Blocks {


        private static TagKey<Block> createTag(String name) {
            return  TagKey.of(Registry.BLOCK_KEY, new Identifier(WitcheryRestitched.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name) {
            return  TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FOUL_FUME_INPUT = createTag("foul_fume_input");
        public static final TagKey<Item> CAST_IRON_OVEN_INPUT = createTag("cast_iron_oven_input");

        private static TagKey<Item> createTag(String name) {
            return  TagKey.of(Registry.ITEM_KEY, new Identifier(WitcheryRestitched.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return  TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}
