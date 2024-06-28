package jabberdrake.homesteading;
import jabberdrake.homesteading.common.recipe.crucible.CrucibleRecipe;
import jabberdrake.homesteading.common.recipe.crucible.CrucibleRecipeSerializer;
import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Homesteading implements ModInitializer {
	public static final String MOD_ID = "homesteading";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RecipeType<CrucibleRecipe> CRUCIBLE_RECIPE = RecipeType.register(String.valueOf(new Identifier(MOD_ID, "crucible")));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing!");
		HomeObjectRegistry.init();

		Registry.register(Registries.RECIPE_SERIALIZER, CrucibleRecipe.ID, CrucibleRecipeSerializer.INSTANCE);

		FuelRegistry.INSTANCE.add(HomeObjectRegistry.PEAT_BRICK, 400);
		FuelRegistry.INSTANCE.add(HomeObjectRegistry.PEAT_BLOCK, 1600);
	}

	public static Identifier createIdentifier(String value) {
		return new Identifier(MOD_ID, value);
	}
}