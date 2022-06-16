package net.techtastic.witcheryrestitched.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class ModItemGroup {
    public static final ItemGroup WITCHERYRESTITCHED = FabricItemGroupBuilder.build(new Identifier(WitcheryRestitched.MOD_ID, "witcheryrestitched"),
            () -> new ItemStack(ModItems.CLAY_JAR));
}
