package jabberdrake.homesteading.common.registry;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.item.ProspectingPickItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeObjectRegistry {

    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
    private static ItemGroup GROUP;

    //items
    public static final Item RAW_TIN = register("raw_tin", new Item(new FabricItemSettings()));
    public static final Item TIN_INGOT = register("tin_ingot", new Item(new FabricItemSettings()));
    public static final Item TIN_NUGGET = register("tin_nugget", new Item(new FabricItemSettings()));
    public static final Item PEAT_BRICK = register("peat_brick", new Item(new FabricItemSettings()));


    //blocks
    public static final Block TIN_ORE = register("tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_ORE)), true);
    public static final Block DEEPSLATE_TIN_ORE = register("deepslate_tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_COPPER_ORE)), true);
    public static final Block RAW_TIN_BLOCK = register("raw_tin_block", new Block(FabricBlockSettings.copyOf(Blocks.RAW_COPPER_BLOCK)), true);
    public static final Block TIN_BLOCK = register("tin_block", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)), true);
    public static final Block PEAT_BLOCK = register("peat_block", new Block(FabricBlockSettings.copyOf(Blocks.MUD)), true);

    //tools
    public static final Item IRON_PROSPECTING_PICK = register("iron_prospecting_pick", new ProspectingPickItem(new FabricItemSettings(), 3));

    //foodstuffs
    public static final Item CHILI_PEPPER = register("chili_pepper", new Item(new FabricItemSettings().food(HomeFoodComponents.CHILI_PEPPER)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Homesteading.createIdentifier(name), item);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Homesteading.createIdentifier(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Homesteading.createIdentifier(name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static <T extends Block> T register(String name, Block block, boolean createBlockItem) {
        BLOCKS.put(block, Homesteading.createIdentifier(name));
        if (createBlockItem) {
            ITEMS.put(new BlockItem(block, new FabricItemSettings()), BLOCKS.get(block));
        }
        return (T) block;
    }

    private static <T extends Item> T register(String name, Item item) {
        ITEMS.put(item, Homesteading.createIdentifier(name));
        return (T) item;
    }

    public static void init() {
        GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID))
                .icon(TIN_NUGGET::getDefaultStack).entries((displayContext, entries) -> {
                    ITEMS.keySet().forEach(entries::add);
                    //BLOCKS.keySet().forEach(entries::add);
                }).build();

        Homesteading.LOGGER.info("Registering tab group for " + Homesteading.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier(Homesteading.MOD_ID), GROUP);

        Homesteading.LOGGER.info("Registering blocks for " + Homesteading.MOD_ID);
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));

        Homesteading.LOGGER.info("Registering items for " + Homesteading.MOD_ID);
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
    }
}
