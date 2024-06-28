package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
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
        calcitePool.stairs(HomeObjectRegistry.CALCITE_STAIRS);
        calcitePool.slab(HomeObjectRegistry.CALCITE_SLAB);
        calcitePool.button(HomeObjectRegistry.CALCITE_BUTTON);
        calcitePool.pressurePlate(HomeObjectRegistry.CALCITE_PRESSURE_PLATE);
        calcitePool.wall(HomeObjectRegistry.CALCITE_WALL);

        // Blocks: Refined Wood
        blockStateModelGenerator.registerLog(HomeObjectRegistry.STRIPPED_HAZEL_LOG).log(HomeObjectRegistry.STRIPPED_HAZEL_LOG).wood(HomeObjectRegistry.STRIPPED_HAZEL_WOOD);
        BlockStateModelGenerator.BlockTexturePool hazelPlanksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(HomeObjectRegistry.HAZEL_PLANKS);
        hazelPlanksPool.stairs(HomeObjectRegistry.HAZEL_STAIRS);
        hazelPlanksPool.slab(HomeObjectRegistry.HAZEL_SLAB);
        hazelPlanksPool.button(HomeObjectRegistry.HAZEL_BUTTON);
        hazelPlanksPool.pressurePlate(HomeObjectRegistry.HAZEL_PRESSURE_PLATE);
        hazelPlanksPool.fence(HomeObjectRegistry.HAZEL_FENCE);
        hazelPlanksPool.fenceGate(HomeObjectRegistry.HAZEL_FENCE_GATE);
        blockStateModelGenerator.registerDoor(HomeObjectRegistry.HAZEL_DOOR);
        blockStateModelGenerator.registerTrapdoor(HomeObjectRegistry.HAZEL_TRAPDOOR);

        // Blocks: Ores and Metals
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjectRegistry.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjectRegistry.DEEPSLATE_TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjectRegistry.RAW_TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjectRegistry.TIN_BLOCK);

        // Blocks: Building, Other

        // Blocks: Functional

        // Blocks: Soil & Similar
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjectRegistry.PEAT_BLOCK);

        // Blocks: Logs & Leaves
        blockStateModelGenerator.registerLog(HomeObjectRegistry.HAZEL_LOG).log(HomeObjectRegistry.HAZEL_LOG).wood(HomeObjectRegistry.HAZEL_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjectRegistry.HAZEL_LEAVES);

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
        itemModelGenerator.register(HomeObjectRegistry.IRON_PROSPECTING_PICK, Models.HANDHELD);

        // Items: Weapons

        // Items: Parts

        // Items: Armor

        // Items: Equipment, Other

        // Items: Crops
        itemModelGenerator.register(HomeObjectRegistry.CHILI_PEPPER, Models.GENERATED);

        // Items: Food

        // Items: Dishes

        // Items: Drinks

        // Items: Foodstuffs, Other

        // Items: Metals
        itemModelGenerator.register(HomeObjectRegistry.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(HomeObjectRegistry.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjectRegistry.TIN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(HomeObjectRegistry.COPPER_NUGGET, Models.GENERATED);

        // Items: Ingredients, Other
        itemModelGenerator.register(HomeObjectRegistry.PEAT_BRICK, Models.GENERATED);

        // Items: Misc

    }
}
