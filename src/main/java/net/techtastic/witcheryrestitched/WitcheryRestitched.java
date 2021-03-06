package net.techtastic.witcheryrestitched;

import net.fabricmc.api.ModInitializer;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.recipe.ModRecipes;
import net.techtastic.witcheryrestitched.screen.ModScreenHandlers;
import net.techtastic.witcheryrestitched.util.ModRegistries;
import net.techtastic.witcheryrestitched.world.feature.ModConfiguredFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WitcheryRestitched implements ModInitializer {
	public static final String MOD_ID = "witcheryrestitched";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModRegistries.registerModStuffs();

		ModBlockEntities.registerAllBlockEntities();
		ModRecipes.registerRecipes();

		ModScreenHandlers.registerAllScreenHandlers();
	}
}
