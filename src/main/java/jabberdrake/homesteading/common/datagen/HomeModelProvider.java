package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class HomeModelProvider extends FabricModelProvider {

    public HomeModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Blocks: Stone
        BlockStateModelGenerator.BlockTexturePool calcitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CALCITE);
        calcitePool.stairs(HomeObjects.CALCITE_STAIRS);
        calcitePool.slab(HomeObjects.CALCITE_SLAB);
        calcitePool.button(HomeObjects.CALCITE_BUTTON);
        calcitePool.pressurePlate(HomeObjects.CALCITE_PRESSURE_PLATE);
        calcitePool.wall(HomeObjects.CALCITE_WALL);

        // Blocks: Refined Wood
        blockStateModelGenerator.registerLog(HomeObjects.STRIPPED_HAZEL_LOG).log(HomeObjects.STRIPPED_HAZEL_LOG).wood(HomeObjects.STRIPPED_HAZEL_WOOD);
        BlockStateModelGenerator.BlockTexturePool hazelPlanksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(HomeObjects.HAZEL_PLANKS);
        hazelPlanksPool.stairs(HomeObjects.HAZEL_STAIRS);
        hazelPlanksPool.slab(HomeObjects.HAZEL_SLAB);
        hazelPlanksPool.button(HomeObjects.HAZEL_BUTTON);
        hazelPlanksPool.pressurePlate(HomeObjects.HAZEL_PRESSURE_PLATE);
        hazelPlanksPool.fence(HomeObjects.HAZEL_FENCE);
        hazelPlanksPool.fenceGate(HomeObjects.HAZEL_FENCE_GATE);
        blockStateModelGenerator.registerDoor(HomeObjects.HAZEL_DOOR);
        blockStateModelGenerator.registerTrapdoor(HomeObjects.HAZEL_TRAPDOOR);

        // Blocks: Ores and Metals
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.BASALT_GOLD_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.RAW_TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.TIN_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.ZINC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_ZINC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.RAW_ZINC_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.ZINC_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.RAW_SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.SILVER_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.BRONZE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.BRASS_BLOCK);

        // Blocks: Building, Other

        // Blocks: Functional

        // Blocks: Soil & Similar
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.PEAT_BLOCK);

        // Blocks: Logs & Leaves
        blockStateModelGenerator.registerLog(HomeObjects.HAZEL_LOG).log(HomeObjects.HAZEL_LOG).wood(HomeObjects.HAZEL_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.HAZEL_LEAVES);

        // Blocks: Natural, Other

        // Blocks: Misc
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Items: Functional

        // Items: Seeds

        // Items: Saplings

        // Items: Natural, Other

        // Items: Tools
        itemModelGenerator.register(HomeObjects.IRON_PROSPECTING_PICK, Models.HANDHELD);

        // Items: Weapons

        // Items: Parts

        // Items: Armor

        // Items: Equipment, Other

        // Items: Crops
        itemModelGenerator.register(HomeObjects.CHILI_PEPPER, Models.GENERATED);

        // Items: Food

        // Items: Dishes

        // Items: Drinks

        // Items: Foodstuffs, Other

        // Items: Metals
        itemModelGenerator.register(HomeObjects.COPPER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.RAW_COPPER_BIT, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_IRON_BIT, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_GOLD_BIT, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.TIN_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_ZINC, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.ZINC_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.ZINC_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_SILVER, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.SILVER_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.BRONZE_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.BRONZE_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.BRASS_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.BRASS_NUGGET, Models.GENERATED);

        // Items: Ingredients, Other
        itemModelGenerator.register(HomeObjects.PEAT_BRICK, Models.GENERATED);

        // Items: Misc

    }
}
