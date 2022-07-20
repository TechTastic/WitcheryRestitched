package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KeyItem extends Item {
    public KeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (nbt.contains("witcheryrestitched:keyX") && nbt.contains("witcheryrestitched:keyY") && nbt.contains("witcheryrestitched:keyZ")) {
            int x = (int) nbt.getDouble("witcheryrestitched:keyX");
            int y = (int) nbt.getDouble("witcheryrestitched:keyY");
            int z = (int) nbt.getDouble("witcheryrestitched:keyZ");
            String firstText = world.getBlockState(new BlockPos(x, y, z)).getBlock().getName().getString() + " at";
            String secondText = "X: " + x + ", Y: " + y + ", Z:" + z;

            tooltip.add(Text.of(firstText).copy().formatted(Formatting.DARK_PURPLE));
            tooltip.add(Text.of(secondText).copy().formatted(Formatting.DARK_PURPLE));
        }
    }
}
