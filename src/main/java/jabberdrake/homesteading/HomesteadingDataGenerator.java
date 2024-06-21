package jabberdrake.homesteading;

import jabberdrake.homesteading.common.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class HomesteadingDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(HomeBlockTagProvider::new);
		pack.addProvider(HomeItemTagProvider::new);
		pack.addProvider(HomeLootTableProvider::new);
		pack.addProvider(HomeModelProvider::new);
		pack.addProvider(HomeRecipeProvider::new);
	}
}
