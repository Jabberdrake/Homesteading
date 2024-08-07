package jabberdrake.homesteading;

import jabberdrake.homesteading.common.recipe.crucible.CrucibleRecipe;
import jabberdrake.homesteading.common.recipe.crucible.CrucibleRecipeSerializer;
import jabberdrake.homesteading.common.registry.HomeBlockEntities;
import jabberdrake.homesteading.common.registry.HomeObjects;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Homesteading implements ModInitializer {
	public static final String MOD_ID = "homesteading";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RecipeType<CrucibleRecipe> CRUCIBLE_RECIPE = RecipeType.register(String.valueOf(createIdentifier("crucible")));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing!");
		HomeObjects.init();
		HomeBlockEntities.init();

		Registry.register(Registries.RECIPE_SERIALIZER, CrucibleRecipe.ID, CrucibleRecipeSerializer.INSTANCE);

		FuelRegistry.INSTANCE.add(HomeObjects.PEAT_BRICK, 400);
		FuelRegistry.INSTANCE.add(HomeObjects.PEAT_BLOCK, 1600);

		StrippableBlockRegistry.register(HomeObjects.HAZEL_LOG, HomeObjects.STRIPPED_HAZEL_LOG);
		StrippableBlockRegistry.register(HomeObjects.HAZEL_WOOD, HomeObjects.STRIPPED_HAZEL_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(HomeObjects.HAZEL_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(HomeObjects.HAZEL_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(HomeObjects.STRIPPED_HAZEL_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(HomeObjects.STRIPPED_HAZEL_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(HomeObjects.HAZEL_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(HomeObjects.HAZEL_LEAVES, 30, 60);
	}

	public static Identifier createIdentifier(String value) {
		return new Identifier(MOD_ID, value);
	}
}