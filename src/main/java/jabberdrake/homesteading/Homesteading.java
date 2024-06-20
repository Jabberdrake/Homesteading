package jabberdrake.homesteading;

import jabberdrake.homesteading.common.block.ModBlocks;
import jabberdrake.homesteading.common.registry.ModItemGroups;
import jabberdrake.homesteading.common.registry.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Homesteading implements ModInitializer {
	public static final String MOD_ID = "homesteading";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing!");
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
	}
}