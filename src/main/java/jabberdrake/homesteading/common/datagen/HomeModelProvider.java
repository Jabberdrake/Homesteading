package jabberdrake.homesteading.common.datagen;

import com.google.common.collect.Range;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.block.ChiliCropBlock;
import jabberdrake.homesteading.common.block.CornCropBlock;
import jabberdrake.homesteading.common.block.FlaxCropBlock;
import jabberdrake.homesteading.common.registry.HomeObjects;
import jabberdrake.homesteading.common.util.HomesteadingProperties;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.stream.IntStream;

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
        BlockStateModelGenerator.BlockTexturePool marblePoll = blockStateModelGenerator.registerCubeAllModelTexturePool(HomeObjects.MARBLE);
        marblePoll.stairs(HomeObjects.MARBLE_STAIRS);
        marblePoll.slab(HomeObjects.MARBLE_SLAB);
        marblePoll.wall(HomeObjects.MARBLE_WALL);

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

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.CAST_IRON_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.RAW_TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.TIN_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.ZINC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_ZINC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.RAW_ZINC_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.ZINC_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.BISMUTH_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_BISMUTH_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.ANDESITE_BISMUTH_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.RAW_BISMUTH_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.BISMUTH_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.DEEPSLATE_SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(HomeObjects.MARBLE_SILVER_ORE);
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
        blockStateModelGenerator.registerCrop(HomeObjects.CHILI_CROP, ChiliCropBlock.AGE, 0, 1, 2);
        registerTallCrop(blockStateModelGenerator, HomeObjects.CORN_CROP, false, CornCropBlock.AGE, 1, 1, 2, 2);
        registerTallCrop(blockStateModelGenerator, HomeObjects.FLAX_CROP, false, FlaxCropBlock.AGE, 1, 1, 1, 1, 2, 2, 2, 2);

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
        itemModelGenerator.register(HomeObjects.CORN, Models.GENERATED);

        // Items: Food

        // Items: Dishes

        // Items: Drinks

        // Items: Foodstuffs, Other

        // Items: Metals
        itemModelGenerator.register(HomeObjects.RAW_COPPER_CHUNK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.COPPER_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_IRON_CHUNK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.CAST_IRON_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.CAST_IRON_NUGGET, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.IRON_BLOOM, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.IRON_BLOOM_CHUNK, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_GOLD_CHUNK, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.RAW_TIN_CHUNK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.TIN_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_ZINC, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.RAW_ZINC_CHUNK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.ZINC_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.ZINC_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_BISMUTH, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.RAW_BISMUTH_CHUNK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.BISMUTH_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.BISMUTH_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.RAW_SILVER, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.RAW_SILVER_CHUNK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.SILVER_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.BRONZE_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.BRONZE_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.BRASS_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.BRASS_NUGGET, Models.GENERATED);

        itemModelGenerator.register(HomeObjects.ELECTRUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.ELECTRUM_NUGGET, Models.GENERATED);


        // Items: Ingredients, Other
        itemModelGenerator.register(HomeObjects.PEAT_BRICK, Models.GENERATED);
        itemModelGenerator.register(HomeObjects.FLAX, Models.GENERATED);

        // Items: Misc

    }

    public static void registerTallCrop(BlockStateModelGenerator generator, Block crop, boolean useCropModel, Property<Integer> ageProperty, int... heightsPerStage) {

        int maxStage = heightsPerStage.length - 1;
        int maxHeight = 0;
        int segments = 0;
        for (int height : heightsPerStage) {
            segments += height;
            if (height > maxHeight)
                maxHeight = height;
        }

        if (ageProperty.getValues().size() != segments) {
            throw new IllegalArgumentException();
        }

        int finalMaxHeight = maxHeight;
        BlockStateVariantMap variantMap = BlockStateVariantMap.create(ageProperty).register(age -> {
            int stage = 0;
            int height = 1;
            seek: {
                int segment = 0;
                for (int h = 1; h <= finalMaxHeight; h++) {
                    for (int s = 0; s <= maxStage; s++) {
                        // check this condition for bugs and shit
                        if (heightsPerStage[s] < h) {
                            continue;
                        } else if (segment == age) {
                            stage = s;
                            height = h;
                            break seek;
                        } else {
                            segment++;
                        }
                    }
                }
            }
            Identifier identifier = generator.createSubModel(crop, "_stage" + stage + "_height" + height, useCropModel ? Models.CROP : Models.TINTED_CROSS, useCropModel ? TextureMap::crop : TextureMap::cross);
            return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
        });

        generator.registerItemModel(crop.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(variantMap));

    }
}
