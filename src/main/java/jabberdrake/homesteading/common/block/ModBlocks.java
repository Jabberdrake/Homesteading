package jabberdrake.homesteading.common.block;

import jabberdrake.homesteading.Homesteading;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block TIN_ORE = registerBlock("tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_ORE)));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_COPPER_ORE)));
    public static final Block RAW_TIN_BLOCK = registerBlock("raw_tin_block", new Block(FabricBlockSettings.copyOf(Blocks.RAW_COPPER_BLOCK)));
    public static final Block TIN_BLOCK = registerBlock("tin_block", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Homesteading.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Homesteading.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Homesteading.LOGGER.info("Registering mod blocks for " + Homesteading.MOD_ID);
    }
}
