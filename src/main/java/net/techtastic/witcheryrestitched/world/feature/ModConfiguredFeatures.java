package net.techtastic.witcheryrestitched.world.feature;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {

    // ROWAN
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ROWAN_TREE =
            ConfiguredFeatures.register("rowan_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.ROWAN_LOG),
                    new StraightTrunkPlacer(4, 2, 2),
                    BlockStateProvider.of(ModBlocks.ROWAN_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)).build());

    //builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 4, 2, 0, 2)

    public static final RegistryEntry<PlacedFeature> ROWAN_CHECKED =
            PlacedFeatures.register("rowan_checked", ROWAN_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.ROWAN_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> ROWAN_SPAWN =
            ConfiguredFeatures.register("rowan_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(ROWAN_CHECKED, 0.5f)),
                            ROWAN_CHECKED));

    // ALDER

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ALDER_TREE =
            ConfiguredFeatures.register("alder_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.ALDER_LOG),
                    new LargeOakTrunkPlacer(3, 5, 0),
                    BlockStateProvider.of(ModBlocks.ALDER_LEAVES),
                    new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).build());

    public static final RegistryEntry<PlacedFeature> ALDER_CHECKED =
            PlacedFeatures.register("alder_checked", ALDER_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.ALDER_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> ALDER_SPAWN =
            ConfiguredFeatures.register("alder_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(ALDER_CHECKED, 0.5f)),
                            ALDER_CHECKED));


    // HAWTHORN

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> HAWTHORN_TREE =
            ConfiguredFeatures.register("hawthorn_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.HAWTHORN_LOG),
                    new LargeOakTrunkPlacer(4, 14, 4),
                    BlockStateProvider.of(ModBlocks.HAWTHORN_LEAVES),
                    new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).build());

    public static final RegistryEntry<PlacedFeature> HAWTHORN_CHECKED =
            PlacedFeatures.register("hawthorn_checked", HAWTHORN_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.HAWTHORN_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> HAWTHORN_SPAWN =
            ConfiguredFeatures.register("hawthorn_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(HAWTHORN_CHECKED, 0.5f)),
                            HAWTHORN_CHECKED));

    public static void registerConfiguredFeatures() {
        WitcheryRestitched.LOGGER.info("Registering ModConfiguredFeatures for " + WitcheryRestitched.MOD_ID);
    }
}
