package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class HomeLootTableProvider extends FabricBlockLootTableProvider {

    public HomeLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(HomeObjects.TIN_ORE, copperLikeOreDrops(HomeObjects.TIN_ORE, HomeObjects.RAW_TIN, 1, 3));
        addDrop(HomeObjects.RAW_TIN_BLOCK);
        addDrop(HomeObjects.TIN_BLOCK);
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
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item, float minAmount, float maxAmount) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder) this.applyExplosionDecay(drop,
                ((LeafEntry.Builder) ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider
                        .create(minAmount, maxAmount)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
