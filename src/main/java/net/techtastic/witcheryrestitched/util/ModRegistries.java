package net.techtastic.witcheryrestitched.util;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
        registerFlammableBlock();
        registerStrippables();
    }

    private static void registerFuels() {
        WitcheryRestitched.LOGGER.info("Registering Fuels for " + WitcheryRestitched.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModBlocks.ROWAN_LOG,15);
        registry.add(ModBlocks.STRIPPED_ROWAN_LOG,15);
        registry.add(ModBlocks.ROWAN_WOOD,15);
        registry.add(ModBlocks.STRIPPED_ROWAN_WOOD,15);
        registry.add(ModBlocks.ROWAN_PLANKS,15);
        registry.add(ModBlocks.ROWAN_SAPLING,5);

        registry.add(ModBlocks.ALDER_LOG,15);
        registry.add(ModBlocks.STRIPPED_ALDER_LOG,15);
        registry.add(ModBlocks.ALDER_WOOD,15);
        registry.add(ModBlocks.STRIPPED_ALDER_WOOD,15);
        registry.add(ModBlocks.ALDER_PLANKS,15);
        registry.add(ModBlocks.ALDER_SAPLING,5);

        registry.add(ModBlocks.HAWTHORN_LOG,15);
        registry.add(ModBlocks.STRIPPED_HAWTHORN_LOG,15);
        registry.add(ModBlocks.HAWTHORN_WOOD,15);
        registry.add(ModBlocks.STRIPPED_HAWTHORN_WOOD,15);
        registry.add(ModBlocks.HAWTHORN_PLANKS,15);
        registry.add(ModBlocks.HAWTHORN_SAPLING,5);
    }

    private static void registerCommands() {
        //CommandRegistrationCallback.EVENT.register(~CommandName~Command::register);
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.ROWAN_LOG, ModBlocks.STRIPPED_ROWAN_LOG);
        StrippableBlockRegistry.register(ModBlocks.ROWAN_WOOD, ModBlocks.STRIPPED_ROWAN_WOOD);

        StrippableBlockRegistry.register(ModBlocks.ALDER_LOG, ModBlocks.STRIPPED_ALDER_LOG);
        StrippableBlockRegistry.register(ModBlocks.ALDER_WOOD, ModBlocks.STRIPPED_ALDER_WOOD);

        StrippableBlockRegistry.register(ModBlocks.HAWTHORN_LOG, ModBlocks.STRIPPED_HAWTHORN_LOG);
        StrippableBlockRegistry.register(ModBlocks.HAWTHORN_WOOD, ModBlocks.STRIPPED_HAWTHORN_WOOD);
    }

    private static void registerFlammableBlock() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.ROWAN_LOG, 5, 5);
        instance.add(ModBlocks.ROWAN_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_ROWAN_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_ROWAN_WOOD, 5, 5);
        instance.add(ModBlocks.ROWAN_PLANKS, 5, 20);
        instance.add(ModBlocks.ROWAN_LEAVES, 30, 60);

        instance.add(ModBlocks.ALDER_LOG, 5, 5);
        instance.add(ModBlocks.ALDER_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_ALDER_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_ALDER_WOOD, 5, 5);
        instance.add(ModBlocks.ALDER_PLANKS, 5, 20);
        instance.add(ModBlocks.ALDER_LEAVES, 30, 60);

        instance.add(ModBlocks.HAWTHORN_LOG, 5, 5);
        instance.add(ModBlocks.HAWTHORN_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_HAWTHORN_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_HAWTHORN_WOOD, 5, 5);
        instance.add(ModBlocks.HAWTHORN_PLANKS, 5, 20);
        instance.add(ModBlocks.HAWTHORN_LEAVES, 30, 60);
    }

    private static void registerEvents() {
        //ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }
}
