package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.block.ChiliCropBlock;
import jabberdrake.homesteading.common.block.CornCropBlock;
import jabberdrake.homesteading.common.block.FlaxCropBlock;
import jabberdrake.homesteading.common.registry.HomeObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class HomeLootTableProvider extends FabricBlockLootTableProvider {

    public HomeLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(HomeObjects.BASALT_GOLD_ORE, oreDrops(HomeObjects.BASALT_GOLD_ORE, Items.RAW_GOLD));
        addDrop(HomeObjects.TIN_ORE, copperLikeOreDrops(HomeObjects.TIN_ORE, HomeObjects.RAW_TIN, 1, 3));
        addDrop(HomeObjects.DEEPSLATE_TIN_ORE, copperLikeOreDrops(HomeObjects.DEEPSLATE_TIN_ORE, HomeObjects.RAW_TIN, 1, 3));
        addDrop(HomeObjects.RAW_TIN_BLOCK);
        addDrop(HomeObjects.TIN_BLOCK);
        addDrop(HomeObjects.ZINC_ORE, copperLikeOreDrops(HomeObjects.ZINC_ORE, HomeObjects.RAW_ZINC, 1, 3));
        addDrop(HomeObjects.DEEPSLATE_ZINC_ORE, copperLikeOreDrops(HomeObjects.DEEPSLATE_ZINC_ORE, HomeObjects.RAW_ZINC, 1, 3));
        addDrop(HomeObjects.ANDESITE_BISMUTH_ORE, oreDrops(HomeObjects.ANDESITE_BISMUTH_ORE, HomeObjects.RAW_BISMUTH));
        addDrop(HomeObjects.BISMUTH_ORE, oreDrops(HomeObjects.BISMUTH_ORE, HomeObjects.RAW_BISMUTH));
        addDrop(HomeObjects.DEEPSLATE_BISMUTH_ORE, oreDrops(HomeObjects.DEEPSLATE_BISMUTH_ORE, HomeObjects.RAW_BISMUTH));
        addDrop(HomeObjects.MARBLE_SILVER_ORE, oreDrops(HomeObjects.MARBLE_SILVER_ORE, HomeObjects.RAW_SILVER));
        addDrop(HomeObjects.SILVER_ORE, oreDrops(HomeObjects.SILVER_ORE, HomeObjects.RAW_SILVER));
        addDrop(HomeObjects.DEEPSLATE_SILVER_ORE, oreDrops(HomeObjects.DEEPSLATE_SILVER_ORE, HomeObjects.RAW_SILVER));
        addDrop(HomeObjects.PEAT_BLOCK, drops(HomeObjects.PEAT_BRICK, UniformLootNumberProvider.create(4, 4)));
        addDrop(HomeObjects.CALCITE_STAIRS);
        addDrop(HomeObjects.CALCITE_SLAB, slabDrops(HomeObjects.CALCITE_SLAB));
        addDrop(HomeObjects.CALCITE_BUTTON);
        addDrop(HomeObjects.CALCITE_PRESSURE_PLATE);
        addDrop(HomeObjects.HAZEL_PLANKS);
        addDrop(HomeObjects.HAZEL_STAIRS);
        addDrop(HomeObjects.HAZEL_SLAB, slabDrops(HomeObjects.HAZEL_SLAB));
        addDrop(HomeObjects.HAZEL_BUTTON);
        addDrop(HomeObjects.HAZEL_PRESSURE_PLATE);
        addDrop(HomeObjects.HAZEL_FENCE);
        addDrop(HomeObjects.HAZEL_FENCE_GATE);
        addDrop(HomeObjects.HAZEL_DOOR, doorDrops(HomeObjects.HAZEL_DOOR));
        addDrop(HomeObjects.HAZEL_TRAPDOOR);
        addDrop(HomeObjects.CRUCIBLE);

        BlockStatePropertyLootCondition.Builder chili_builder = BlockStatePropertyLootCondition.builder(HomeObjects.CHILI_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(ChiliCropBlock.AGE, ChiliCropBlock.MAX_AGE));
        addDrop(HomeObjects.CHILI_CROP, cropDrops(HomeObjects.CHILI_CROP, HomeObjects.CHILI_PEPPER, HomeObjects.CHILI_SEEDS, chili_builder));

        BlockStatePropertyLootCondition.Builder corn_builder = BlockStatePropertyLootCondition.builder(HomeObjects.CORN_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(CornCropBlock.AGE, CornCropBlock.MAX_AGE));
        addDrop(HomeObjects.CORN_CROP, cropDrops(HomeObjects.CORN_CROP, HomeObjects.CORN, HomeObjects.CORN_SEEDS, corn_builder));

        BlockStatePropertyLootCondition.Builder flax_builder = BlockStatePropertyLootCondition.builder(HomeObjects.FLAX_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(FlaxCropBlock.AGE, FlaxCropBlock.MAX_AGE));
        addDrop(HomeObjects.FLAX_CROP, cropDrops(HomeObjects.FLAX_CROP, HomeObjects.FLAX, HomeObjects.FLAX_SEEDS, flax_builder));

        addDrop(HomeObjects.MARBLE);
        addDrop(HomeObjects.MARBLE_STAIRS);
        addDrop(HomeObjects.MARBLE_SLAB);
        addDrop(HomeObjects.MARBLE_WALL);
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item, float minAmount, float maxAmount) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder) this.applyExplosionDecay(drop,
                ((LeafEntry.Builder) ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider
                        .create(minAmount, maxAmount)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
