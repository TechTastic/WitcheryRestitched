package net.techtastic.witcheryrestitched.world.feature.tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.techtastic.witcheryrestitched.world.feature.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class HawthornSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.HAWTHORN_TREE;
    }
}
