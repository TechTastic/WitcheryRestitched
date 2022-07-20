package net.techtastic.witcheryrestitched.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MOD_LOGS = createTag("mod_logs");
        public static final TagKey<Block> MOD_LEAVES = createTag("mod_leaves");
        public static final TagKey<Block> NATURE_BLOCKS = createTag("nature_blocks");

        public static final TagKey<Block> ROWAN_LOGS = createTag("rowan_logs");
        public static final TagKey<Block> ALDER_LOGS = createTag("alder_logs");
        public static final TagKey<Block> HAWTHORN_LOGS = createTag("hawthorn_logs");

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

        public static final TagKey<Item> MOD_LOGS = createTag("mod_logs");

        public static final TagKey<Item> ROWAN_LOGS = createTag("rowan_logs");
        public static final TagKey<Item> ALDER_LOGS = createTag("alder_logs");
        public static final TagKey<Item> HAWTHORN_LOGS = createTag("hawthorn_logs");

        private static TagKey<Item> createTag(String name) {
            return  TagKey.of(Registry.ITEM_KEY, new Identifier(WitcheryRestitched.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return  TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}
