package net.techtastic.witcheryrestitched.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.item.custom.TaglockItem;
import net.techtastic.witcheryrestitched.item.custom.ToeOfFrogItem;

public class ModItems {

    /* BOOKS */

    public static final Item BOOK_BREWS_AND_INFUSIONS = registerItem("book_brews_and_infusions",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_CIRCLE_MAGIC = registerItem("book_circle_magic",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_COLLECTING_FUMES = registerItem("book_collecting_fumes",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_CONJURATION_AND_FETISHES = registerItem("book_conjuration_and_fetishes",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_DISTILLING = registerItem("book_distilling",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_HERBOLOGY = registerItem("book_herbology",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_OBSERVATIONS_OF_AN_IMMORTAL = registerItem("book_observations_of_an_immortal",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_OF_BIOMES = registerItem("book_of_biomes",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BOOK_SYMBOLOGY = registerItem("book_symbology",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    /* JAR ITEMS */

    public static final Item UNFIRED_CLAY_JAR = registerItem("unfired_clay_jar",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));
    public static final Item CLAY_JAR = registerItem("clay_jar",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item FOUL_FUME = registerItem("foul_fume",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item EXHALE_OF_THE_HORNED_ONE = registerItem("exhale_of_the_horned_one",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BREATH_OF_THE_GODDESS = registerItem("breath_of_the_goddess",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item HINT_OF_REBIRTH = registerItem("hint_of_rebirth",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WHIFF_OF_MAGIC = registerItem("whiff_of_magic",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item REEK_OF_MISFORTUNE = registerItem("reek_of_misfortune",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ODOR_OF_PURITY = registerItem("odor_of_purity",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item OIL_OF_VITRIOL = registerItem("oil_of_vitriol",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item TEAR_OF_THE_GODDESS = registerItem("tear_of_the_goddess",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item DIAMOND_VAPOR = registerItem("diamond_vapor",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ENDER_DEW = registerItem("ender_dew",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item DEMONIC_BLOOD = registerItem("demonic_blood",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item DROP_OF_LUCK = registerItem("drop_of_luck",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item PURIFIED_MILK = registerItem("purified_milk",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item FOCUSED_WILL = registerItem("focused_will",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item CONDENSED_FEAR = registerItem("condensed_fear",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item MELLIFLUOUS_HUNGER = registerItem("mellifluous_hunger",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    /* PLANTS */

    public static final Item BELLADONNA_FLOWER = registerItem("belladonna_flower",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item MANDRAKE_ROOT = registerItem("mandrake_root",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item MANDRAKE_SEEDS = registerItem("mandrake_seeds",
            new AliasedBlockItem(ModBlocks.CROP_MANDRAKE_ROOT, new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WATER_ARTICHOKE_GLOBE = registerItem("water_artichoke_globe",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item GARLIC = registerItem("garlic",
            new AliasedBlockItem(ModBlocks.CROP_GARLIC, new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WOLFSBANE_FLOWER = registerItem("wolfsbane_flower",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WORMWOOD = registerItem("wormwood",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    /* MISC */

    public static final Item WOOD_ASH = registerItem("wood_ash",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item QUICKLIME = registerItem("quicklime",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item GYPSUM = registerItem("gypsum",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ICY_NEEDLE = registerItem("icy_needle",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item MUTANDIS = registerItem("mutandis",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item MUTANDIS_EXTREMIS = registerItem("mutandis_extremis",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WAYSTONE = registerItem("waystone",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BONE_NEEDLE = registerItem("bone_needle",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ATTUNED_STONE = registerItem("attuned_stone",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ATTUNED_STONE_CHARGED = registerItem("attuned_stone_charged",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WITCHS_LADDER = registerItem("witchs_ladder",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WITCHS_LADDER_SHRIEKING = registerItem("witchs_ladder_shrieking",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item REFINED_EVIL = registerItem("refined_evil",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    /* BOTTLES */

    public static final Item TAGLOCK = registerItem("taglock",
            new TaglockItem(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item REDSTONE_SOUP = registerItem("redstone_soup",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item FLYING_OINTMENT = registerItem("flying_ointment",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item GHOST_OF_THE_LIGHT = registerItem("ghost_of_the_light",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item SOUL_OF_THE_WORLD = registerItem("soul_of_the_world",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item INFERNAL_ANIMUS = registerItem("infernal_animus",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item SPIRIT_OF_OTHERWHERE = registerItem("spirit_of_otherwhere",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    /* ANIMAL PARTS */

    public static final Item WOOL_OF_BAT = registerItem("wool_of_bat",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item CONDENSED_BAT_BALL = registerItem("condensed_bat_ball",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item TONGUE_OF_DOG = registerItem("tongue_of_dog",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item CREEPER_HEART = registerItem("creeper_heart",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item TOE_OF_FROG = registerItem("toe_of_frog",
            new ToeOfFrogItem(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item OWLET_WING = registerItem("owlet_wing",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ENT_TWIG = registerItem("ent_twig",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item HEART_OF_GOLD = registerItem("heart_of_gold",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(WitcheryRestitched.MOD_ID, name), item);
    }
    public static void registerModItems() {
        WitcheryRestitched.LOGGER.info("Registering mod items for " + WitcheryRestitched.MOD_ID);
    }

}
