package net.techtastic.witcheryrestitched.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> ROWAN_PLACED = PlacedFeatures.register("rowan_placed",
            ModConfiguredFeatures.ROWAN_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));

    public static final RegistryEntry<PlacedFeature> ALDER_PLACED = PlacedFeatures.register("alder_placed",
            ModConfiguredFeatures.ALDER_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));

    public static final RegistryEntry<PlacedFeature> HAWTHORN_PLACED = PlacedFeatures.register("hawthorn_placed",
            ModConfiguredFeatures.HAWTHORN_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
}
