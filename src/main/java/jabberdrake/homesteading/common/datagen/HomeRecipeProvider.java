package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.registry.HomeObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class HomeRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> SMELTABLE_TO_TIN_INGOT = List.of(
            HomeObjects.TIN_ORE,
            HomeObjects.DEEPSLATE_TIN_ORE,
            HomeObjects.RAW_TIN);

    private static final List<ItemConvertible> SMELTABLE_TO_ZINC_INGOT = List.of(
            HomeObjects.ZINC_ORE,
            HomeObjects.DEEPSLATE_ZINC_ORE,
            HomeObjects.RAW_ZINC);

    private static final List<ItemConvertible> SMELTABLE_TO_SILVER_INGOT = List.of(
            HomeObjects.SILVER_ORE,
            HomeObjects.DEEPSLATE_SILVER_ORE,
            HomeObjects.RAW_SILVER);

    public HomeRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //SMELTING/BLASTING: Block of Copper (FROM Block of Raw Copper)
        offerSmelting(exporter, List.of(Items.RAW_COPPER_BLOCK), RecipeCategory.MISC, Items.COPPER_BLOCK, 6.3f, 1500, "copper_block");
        offerBlasting(exporter, List.of(Items.RAW_COPPER_BLOCK), RecipeCategory.MISC, Items.COPPER_BLOCK, 6.3f, 1500, "copper_block");

        //SMELTING/BLASTING: Block of Iron (FROM Block of Raw Iron)
        offerSmelting(exporter, List.of(Items.RAW_IRON_BLOCK), RecipeCategory.MISC, Items.IRON_BLOCK, 6.3f, 1500, "iron_block");
        offerBlasting(exporter, List.of(Items.RAW_IRON_BLOCK), RecipeCategory.MISC, Items.IRON_BLOCK, 6.3f, 1500, "iron_block");

        //SMELTING/BLASTING: Block of Gold (FROM Block of Raw Gold)
        offerSmelting(exporter, List.of(Items.RAW_GOLD_BLOCK), RecipeCategory.MISC, Items.GOLD_BLOCK, 6.3f, 1500, "gold_block");
        offerBlasting(exporter, List.of(Items.RAW_GOLD_BLOCK), RecipeCategory.MISC, Items.GOLD_BLOCK, 6.3f, 1500, "gold_block");

        //SMELTING/BLASTING: Block of Tin (FROM Block of Raw Tin)
        offerSmelting(exporter, List.of(HomeObjects.RAW_TIN_BLOCK), RecipeCategory.MISC, HomeObjects.TIN_BLOCK, 6.3f, 1500, "tin_block");
        offerBlasting(exporter, List.of(HomeObjects.RAW_TIN_BLOCK), RecipeCategory.MISC, HomeObjects.TIN_BLOCK, 6.3f, 1500, "tin_block");

        //SMELTING/BLASTING: Block of Zinc (FROM Block of Raw Zinc)
        offerSmelting(exporter, List.of(HomeObjects.RAW_ZINC_BLOCK), RecipeCategory.MISC, HomeObjects.ZINC_BLOCK, 6.3f, 1500, "zinc_block");
        offerBlasting(exporter, List.of(HomeObjects.RAW_ZINC_BLOCK), RecipeCategory.MISC, HomeObjects.ZINC_BLOCK, 6.3f, 1500, "zinc_block");

        //SMELTING/BLASTING: Block of Silver (FROM Block of Raw Silver)
        offerSmelting(exporter, List.of(HomeObjects.RAW_SILVER_BLOCK), RecipeCategory.MISC, HomeObjects.SILVER_BLOCK, 6.3f, 1500, "silver_block");
        offerBlasting(exporter, List.of(HomeObjects.RAW_SILVER_BLOCK), RecipeCategory.MISC, HomeObjects.SILVER_BLOCK, 6.3f, 1500, "silver_block");

        //SMELTING/BLASTING: Tin Ingot (FROM SMELTABLE_TO_TIN_INGOT)
        offerSmelting(exporter, SMELTABLE_TO_TIN_INGOT, RecipeCategory.MISC, HomeObjects.TIN_INGOT, 0.7f, 200, "tin_ingot");
        offerBlasting(exporter, SMELTABLE_TO_TIN_INGOT, RecipeCategory.MISC, HomeObjects.TIN_INGOT, 0.7f, 100, "tin_ingot");

        //SMELTING/BLASTING: Tin Nugget (FROM Raw Tin Bit)
        offerSmelting(exporter, List.of(HomeObjects.RAW_TIN_BIT), RecipeCategory.MISC, HomeObjects.TIN_NUGGET, 0.07f, 22, "tin_nugget");
        offerBlasting(exporter, List.of(HomeObjects.RAW_TIN_BIT), RecipeCategory.MISC, HomeObjects.TIN_NUGGET, 0.07f, 11, "tin_nugget");

        //SMELTING/BLASTING: Copper Nugget (FROM Raw Copper Bit)
        offerSmelting(exporter, List.of(HomeObjects.RAW_COPPER_BIT), RecipeCategory.MISC, HomeObjects.COPPER_NUGGET, 0.07f, 22, "copper_nugget");
        offerBlasting(exporter, List.of(HomeObjects.RAW_COPPER_BIT), RecipeCategory.MISC, HomeObjects.COPPER_NUGGET, 0.07f, 11, "copper_nugget");

        //SMELTING/BLASTING: Gold Nugget (FROM Raw Gold Bit)
        offerSmelting(exporter, List.of(HomeObjects.RAW_GOLD_BIT), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.07f, 22, "gold_nugget");
        offerBlasting(exporter, List.of(HomeObjects.RAW_GOLD_BIT), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.07f, 11, "gold_nugget");

        //SMELTING/BLASTING: Iron Nugget (FROM Raw Iron Bit)
        offerSmelting(exporter, List.of(HomeObjects.RAW_IRON_BIT), RecipeCategory.MISC, Items.IRON_NUGGET, 0.07f, 22, "iron_nugget");
        offerBlasting(exporter, List.of(HomeObjects.RAW_IRON_BIT), RecipeCategory.MISC, Items.IRON_NUGGET, 0.07f, 11, "iron_nugget");

        //SMELTING/BLASTING: Zinc Ingot (FROM SMELTABLE_TO_ZINC_INGOT)
        offerSmelting(exporter, SMELTABLE_TO_ZINC_INGOT, RecipeCategory.MISC, HomeObjects.ZINC_INGOT, 0.7f, 200, "zinc_ingot");
        offerBlasting(exporter, SMELTABLE_TO_ZINC_INGOT, RecipeCategory.MISC, HomeObjects.ZINC_INGOT, 0.7f, 100, "zinc_ingot");

        //SMELTING/BLASTING: Zinc Nugget (FROM Raw Zinc Bit)
        offerSmelting(exporter, List.of(HomeObjects.RAW_ZINC_BIT), RecipeCategory.MISC, HomeObjects.ZINC_NUGGET, 0.07f, 22, "zinc_nugget");
        offerBlasting(exporter, List.of(HomeObjects.RAW_ZINC_BIT), RecipeCategory.MISC, HomeObjects.ZINC_NUGGET, 0.07f, 11, "zinc_nugget");

        //SMELTING/BLASTING: Silver Ingot (FROM SMELTABLE_TO_Silver_INGOT)
        offerSmelting(exporter, SMELTABLE_TO_SILVER_INGOT, RecipeCategory.MISC, HomeObjects.SILVER_INGOT, 0.7f, 200, "silver_ingot");
        offerBlasting(exporter, SMELTABLE_TO_SILVER_INGOT, RecipeCategory.MISC, HomeObjects.SILVER_INGOT, 0.7f, 100, "silver_ingot");

        //SMELTING/BLASTING: Silver Nugget (FROM Raw Silver Bit)
        offerSmelting(exporter, List.of(HomeObjects.RAW_SILVER_BIT), RecipeCategory.MISC, HomeObjects.SILVER_NUGGET, 0.07f, 22, "silver_nugget");
        offerBlasting(exporter, List.of(HomeObjects.RAW_SILVER_BIT), RecipeCategory.MISC, HomeObjects.SILVER_NUGGET, 0.07f, 11, "silver_nugget");

        //CRAFTING: Copper Ingot (FROM Copper Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.COPPER_NUGGET, RecipeCategory.MISC, Items.COPPER_INGOT, null, null);

        //CRAFTING: Block of Cast Iron (COMPACTING, FROM Cast Iron Ingot) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.CAST_IRON_INGOT, RecipeCategory.DECORATIONS, HomeObjects.CAST_IRON_BLOCK, null, null);

        //CRAFTING: Block of Raw Tin (COMPACTING, FROM Raw Tin) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.RAW_TIN, RecipeCategory.DECORATIONS, HomeObjects.RAW_TIN_BLOCK, null, null);

        //CRAFTING: Block of Tin (COMPACTING, FROM Tin Ingot) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.TIN_INGOT, RecipeCategory.DECORATIONS, HomeObjects.TIN_BLOCK, null, null);

        //CRAFTING: Tin Ingot (FROM Tin Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.TIN_NUGGET, RecipeCategory.MISC, HomeObjects.TIN_INGOT, null, null);

        //CRAFTING: Raw Tin (FROM Raw Tin Bit) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.RAW_TIN_BIT, RecipeCategory.MISC, HomeObjects.RAW_TIN, null, null);

        //CRAFTING: Raw Copper (FROM Raw Copper Bit) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.RAW_COPPER_BIT, RecipeCategory.MISC, Items.RAW_COPPER, null, null);

        //CRAFTING: Block of Raw Zinc (COMPACTING, FROM Raw Zinc) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.RAW_ZINC, RecipeCategory.DECORATIONS, HomeObjects.RAW_ZINC_BLOCK, null, null);

        //CRAFTING: Block of Zinc (COMPACTING, FROM Zinc Ingot) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.ZINC_INGOT, RecipeCategory.DECORATIONS, HomeObjects.ZINC_BLOCK, null, null);

        //CRAFTING: Zinc Ingot (FROM Zinc Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.ZINC_NUGGET, RecipeCategory.MISC, HomeObjects.ZINC_INGOT, null, null);

        //CRAFTING: Raw Zinc (FROM Raw Zinc Bit) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.RAW_ZINC_BIT, RecipeCategory.MISC, HomeObjects.RAW_ZINC, null, null);

        //CRAFTING: Block of Raw Silver (COMPACTING, FROM Raw Silver) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.RAW_SILVER, RecipeCategory.DECORATIONS, HomeObjects.RAW_SILVER_BLOCK, null, null);

        //CRAFTING: Block of Silver (COMPACTING, FROM Silver Ingot) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.SILVER_INGOT, RecipeCategory.DECORATIONS, HomeObjects.SILVER_BLOCK, null, null);

        //CRAFTING: Silver Ingot (FROM Silver Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.SILVER_NUGGET, RecipeCategory.MISC, HomeObjects.SILVER_INGOT, null, null);

        //CRAFTING: Raw Tin (FROM Raw Tin Bit) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.RAW_SILVER_BIT, RecipeCategory.MISC, HomeObjects.RAW_SILVER, null, null);

        //CRAFTING: Raw Gold (FROM Raw Gold Bit) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.RAW_GOLD_BIT, RecipeCategory.MISC, Items.RAW_GOLD, null, null);

        //CRAFTING: Raw Iron (FROM Raw Iron Bit) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.RAW_IRON_BIT, RecipeCategory.MISC, Items.RAW_IRON, null, null);

        //CRAFTING: Cast Iron Ingot (FROM Cast Iron Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.CAST_IRON_NUGGET, RecipeCategory.MISC, HomeObjects.CAST_IRON_INGOT, null, null);

        //CRAFTING: Bronze Ingot (FROM Bronze Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.BRONZE_NUGGET, RecipeCategory.MISC, HomeObjects.BRONZE_INGOT, null, null);

        //CRAFTING: Brass Ingot (FROM Brass Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.BRASS_NUGGET, RecipeCategory.MISC, HomeObjects.BRASS_INGOT, null, null);

        //CRAFTING: Brass Ingot (FROM Brass Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjects.ELECTRUM_NUGGET, RecipeCategory.MISC, HomeObjects.ELECTRUM_INGOT, null, null);

        //CRAFTING: Iron Prospecting Pick
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HomeObjects.IRON_PROSPECTING_PICK, 1)
                .pattern(" ##")
                .pattern("#$ ")
                .pattern(" $ ")
                .input('#', Items.IRON_INGOT)
                .input('$', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        //CRAFTING: Peat Block (COMPACTING)
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjects.PEAT_BLOCK, HomeObjects.PEAT_BRICK);
    }

    public static void offer3x3ReversibleCompactingRecipes(
            RecipeExporter exporter,
            RecipeCategory reverseCategory,
            ItemConvertible baseItem,
            RecipeCategory compactingCategory,
            ItemConvertible compactItem,
            @Nullable String compactingGroup,
            @Nullable String reverseGroup
    ) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9)
                .input(compactItem)
                .group(reverseGroup)
                .criterion(hasItem(compactItem), conditionsFromItem(compactItem))
                .offerTo(exporter, Homesteading.createIdentifier(getRecipeName(baseItem) + "_from_" + getRecipeName(compactItem)));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(compactingGroup)
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
                .offerTo(exporter, Homesteading.createIdentifier(getRecipeName(compactItem) + "_from_" + getRecipeName(baseItem)));
    }

    public static void offer2x2ReversibleCompactingRecipes(
            RecipeExporter exporter,
            RecipeCategory reverseCategory,
            ItemConvertible baseItem,
            RecipeCategory compactingCategory,
            ItemConvertible compactItem,
            @Nullable String compactingGroup,
            @Nullable String reverseGroup
    ) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9)
                .input(compactItem)
                .group(reverseGroup)
                .criterion(hasItem(compactItem), conditionsFromItem(compactItem))
                .offerTo(exporter, Homesteading.createIdentifier(getRecipeName(baseItem) + "_from_" + getRecipeName(compactItem)));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("##")
                .pattern("##")
                .group(compactingGroup)
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
                .offerTo(exporter, Homesteading.createIdentifier(getRecipeName(compactItem) + "_from_" + getRecipeName(baseItem)));
    }
}
