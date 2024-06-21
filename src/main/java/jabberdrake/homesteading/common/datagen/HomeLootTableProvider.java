package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataWriter;
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
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class HomeLootTableProvider extends FabricBlockLootTableProvider {

    public HomeLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(HomeObjectRegistry.TIN_ORE, copperLikeOreDrops(HomeObjectRegistry.TIN_ORE, HomeObjectRegistry.RAW_TIN, 1, 3));
        addDrop(HomeObjectRegistry.RAW_TIN_BLOCK);
        addDrop(HomeObjectRegistry.TIN_BLOCK);
        addDrop(HomeObjectRegistry.PEAT_BLOCK, drops(HomeObjectRegistry.PEAT_BRICK, UniformLootNumberProvider.create(4, 4)));
        addDrop(HomeObjectRegistry.CALCITE_STAIRS);
        addDrop(HomeObjectRegistry.CALCITE_SLAB, slabDrops(HomeObjectRegistry.CALCITE_SLAB));
        addDrop(HomeObjectRegistry.CALCITE_BUTTON);
        addDrop(HomeObjectRegistry.CALCITE_PRESSURE_PLATE);
        addDrop(HomeObjectRegistry.HAZEL_PLANKS);
        addDrop(HomeObjectRegistry.HAZEL_STAIRS);
        addDrop(HomeObjectRegistry.HAZEL_SLAB, slabDrops(HomeObjectRegistry.HAZEL_SLAB));
        addDrop(HomeObjectRegistry.HAZEL_BUTTON);
        addDrop(HomeObjectRegistry.HAZEL_PRESSURE_PLATE);
        addDrop(HomeObjectRegistry.HAZEL_FENCE);
        addDrop(HomeObjectRegistry.HAZEL_FENCE_GATE);
        addDrop(HomeObjectRegistry.HAZEL_DOOR, doorDrops(HomeObjectRegistry.HAZEL_DOOR));
        addDrop(HomeObjectRegistry.HAZEL_TRAPDOOR);
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item, float minAmount, float maxAmount) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder) this.applyExplosionDecay(drop,
                ((LeafEntry.Builder) ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider
                        .create(minAmount, maxAmount)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
