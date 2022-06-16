package net.techtastic.witcheryrestitched.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModItems {

    /* JAR ITEMS */

    public static final Item UNFIRED_CLAY_JAR = registerItem("jars/unfired_clay_jar",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item CLAY_JAR = registerItem("jars/clay_jar",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item FOUL_FUME = registerItem("jars/foul_fume",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item EXHALE_OF_THE_HORNED_ONE = registerItem("jars/exhale_of_the_horned_one",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item BREATH_OF_THE_GODDESS = registerItem("jars/breath_of_the_goddess",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item HINT_OF_REBIRTH = registerItem("jars/hint_of_rebirth",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WHIFF_OF_MAGIC = registerItem("jars/whiff_of_magic",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item REEK_OF_MISFORTUNE = registerItem("jars/reek_of_misfortune",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ODOR_OF_PURITY = registerItem("jars/odor_of_purity",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item OIL_OF_VITRIOL = registerItem("jars/oil_of_vitriol",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item TEAR_OF_THE_GODDESS = registerItem("jars/tear_of_the_goddess",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item DIAMOND_VAPOR = registerItem("jars/diamond_vapor",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item ENDER_DEW = registerItem("jars/ender_dew",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item DEMONIC_BLOOD = registerItem("jars/demonic_blood",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item DROP_OF_LUCK = registerItem("jars/drop_of_luck",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item PURIFIED_MILK = registerItem("jars/purified_milk",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item FOCUSED_WILL = registerItem("jars/focused_will",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item CONDENSED_FEAR = registerItem("jars/condensed_fear",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item MELLIFLUOUS_HUNGER = registerItem("jars/mellifluous_hunger",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    /* PLANTS */

    public static final Item BELLADONNA_FLOWER = registerItem("plants/belladonna_flower",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

        public static final Item MANDRAKE_ROOT = registerItem("plants/mandrake_root",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WATER_ARTICHOKE_GLOBE = registerItem("plants/water_artichoke_globe",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

        public static final Item GARLIC = registerItem("plants/garlic",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

    public static final Item WOLFSBANE_FLOWER = registerItem("plants/wolfsbane_flower",
            new Item(new FabricItemSettings().group(ModItemGroup.WITCHERYRESTITCHED)));

        public static final Item WORMWOOD = registerItem("plants/wormwood",
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

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(WitcheryRestitched.MOD_ID, name), item);
    }
    public static void registerModItems() {
        WitcheryRestitched.LOGGER.info("Registering mod items for " + WitcheryRestitched.MOD_ID);
    }

}
