package jabberdrake.homesteading.common.registry;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.block.CrucibleBlock;
import jabberdrake.homesteading.common.block.blockentities.CrucibleBlockEntity;
import jabberdrake.homesteading.common.item.ProspectingPickItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import javax.tools.Tool;
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
    public static final Item COPPER_NUGGET = register("copper_nugget", new Item(new FabricItemSettings()));


    //blocks
    public static final Block TIN_ORE = register("tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_ORE)), true);
    public static final Block DEEPSLATE_TIN_ORE = register("deepslate_tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_COPPER_ORE)), true);
    public static final Block RAW_TIN_BLOCK = register("raw_tin_block", new Block(FabricBlockSettings.copyOf(Blocks.RAW_COPPER_BLOCK)), true);
    public static final Block TIN_BLOCK = register("tin_block", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)), true);
    public static final Block PEAT_BLOCK = register("peat_block", new Block(FabricBlockSettings.copyOf(Blocks.MUD)), true);
    public static final Block CALCITE_STAIRS = register("calcite_stairs", new StairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CALCITE)), true);
    public static final Block CALCITE_SLAB = register("calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)), true);
    public static final Block CALCITE_BUTTON = register("calcite_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.CALCITE).collidable(false), BlockSetType.STONE, 10, false), true);
    public static final Block CALCITE_PRESSURE_PLATE = register("calcite_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.CALCITE), BlockSetType.STONE), true);
    public static final Block CALCITE_WALL = register("calcite_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)), true);
    public static final Block HAZEL_PLANKS = register("hazel_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), true);
    public static final Block HAZEL_STAIRS = register("hazel_stairs", new StairsBlock(HAZEL_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), true);
    public static final Block HAZEL_SLAB = register("hazel_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), true);
    public static final Block HAZEL_BUTTON = register("hazel_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).collidable(false), BlockSetType.OAK, 10, true), true);
    public static final Block HAZEL_PRESSURE_PLATE = register("hazel_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), BlockSetType.OAK), true);
    public static final Block HAZEL_FENCE = register("hazel_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), true);
    public static final Block HAZEL_FENCE_GATE = register("hazel_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), WoodType.OAK), true);
    public static final Block HAZEL_DOOR = register("hazel_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque(), BlockSetType.OAK), true);
    public static final Block HAZEL_TRAPDOOR = register("hazel_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque(), BlockSetType.OAK), true);

    //Functional Blocks
    public static final Block CRUCIBLE = register("crucible", new CrucibleBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT)), true);

    //tools & weapons
    public static final Item CROCODILE_SPEAR = register("crocodile_spear", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item IRON_PROSPECTING_PICK = register("iron_prospecting_pick", new ProspectingPickItem(ToolMaterials.IRON, 2, 2f, new FabricItemSettings(), 3));

    //foodstuffs
    public static final Item CHILI_PEPPER = register("chili_pepper", new Item(new FabricItemSettings().food(HomeFoodComponents.CHILI_PEPPER)));

    //Block Entities
    public static final BlockEntityType<CrucibleBlockEntity> CRUCIBLE_BLOCK_ENTITY = register("crucible", FabricBlockEntityTypeBuilder.create(CrucibleBlockEntity::new));

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

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Homesteading.createIdentifier(name), builder.build());
    }

    public static void init() {
        GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID))
                .icon(TIN_NUGGET::getDefaultStack).entries((displayContext, entries) -> {
                    ITEMS.keySet().forEach(entries::add);
                }).build();

        Homesteading.LOGGER.info("Registering tab group for " + Homesteading.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier(Homesteading.MOD_ID), GROUP);

        Homesteading.LOGGER.info("Registering blocks for " + Homesteading.MOD_ID);
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));

        Homesteading.LOGGER.info("Registering items for " + Homesteading.MOD_ID);
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
    }
}
