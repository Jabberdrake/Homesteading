package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class HomeRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> SMELTABLE_TO_TIN_INGOT = List.of(
            HomeObjectRegistry.TIN_ORE,
            HomeObjectRegistry.DEEPSLATE_TIN_ORE,
            HomeObjectRegistry.RAW_TIN);

    public HomeRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //SMELTING/BLASTING: Block of Copper (FROM Block of Raw Copper)
        offerSmelting(exporter, List.of(Items.RAW_COPPER_BLOCK), RecipeCategory.MISC, Items.COPPER_BLOCK, 6.3f, 1500, "copper_block");
        offerBlasting(exporter, List.of(Items.RAW_COPPER_BLOCK), RecipeCategory.MISC, Items.COPPER_BLOCK, 6.3f, 1500, "copper_block");

        //SMELTING/BLASTING: Block of Iron (FROM Block of Raw Iron)
        offerSmelting(exporter, List.of(Items.RAW_IRON_BLOCK), RecipeCategory.MISC, Items.IRON_BLOCK, 6.3f, 1500, "iron_block");
        offerBlasting(exporter, List.of(Items.RAW_IRON_BLOCK), RecipeCategory.MISC, Items.IRON_BLOCK, 6.3f, 1500, "iron_block");

        //SMELTING/BLASTING: Block of Gold (FROM Block of Raw Gold)
        offerSmelting(exporter, List.of(Items.RAW_GOLD_BLOCK), RecipeCategory.MISC, Items.GOLD_BLOCK, 6.3f, 1500, "gold_block");
        offerBlasting(exporter, List.of(Items.RAW_GOLD_BLOCK), RecipeCategory.MISC, Items.GOLD_BLOCK, 6.3f, 1500, "gold_block");

        //SMELTING/BLASTING: Tin Ingot (FROM SMELTABLE_TO_TIN_INGOT)
        offerSmelting(exporter, SMELTABLE_TO_TIN_INGOT, RecipeCategory.MISC, HomeObjectRegistry.TIN_INGOT, 0.7f, 200, "tin_ingot");
        offerBlasting(exporter, SMELTABLE_TO_TIN_INGOT, RecipeCategory.MISC, HomeObjectRegistry.TIN_INGOT, 0.7f, 100, "tin_ingot");

        //CRAFTING: Block of Raw Tin (COMPACTING, FROM Raw Tin) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjectRegistry.RAW_TIN, RecipeCategory.DECORATIONS, HomeObjectRegistry.RAW_TIN_BLOCK, null, null);

        //CRAFTING: Block of Tin (COMPACTING, FROM Tin Ingot) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjectRegistry.TIN_INGOT, RecipeCategory.DECORATIONS, HomeObjectRegistry.TIN_BLOCK, null, null);

        //CRAFTING: Tin Ingot (FROM Tin Nugget) (and vice-versa)
        offer3x3ReversibleCompactingRecipes(exporter, RecipeCategory.MISC, HomeObjectRegistry.TIN_NUGGET, RecipeCategory.DECORATIONS, HomeObjectRegistry.TIN_INGOT, null, null);

        //CRAFTING: Iron Prospecting Pick
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HomeObjectRegistry.IRON_PROSPECTING_PICK, 1)
                .pattern(" ##")
                .pattern("#$ ")
                .pattern(" $ ")
                .input('#', Items.IRON_INGOT)
                .input('$', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        //CRAFTING: Peat Block (COMPACTING)
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HomeObjectRegistry.PEAT_BLOCK, HomeObjectRegistry.PEAT_BRICK);
    }

    public static void offer3x3ReversibleCompactingRecipes(
            Consumer<RecipeJsonProvider> exporter,
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
            Consumer<RecipeJsonProvider> exporter,
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
