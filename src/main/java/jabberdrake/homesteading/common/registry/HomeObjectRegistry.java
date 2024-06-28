package jabberdrake.homesteading.common.registry;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.block.CrucibleBlock;
import jabberdrake.homesteading.common.item.ProspectingPickItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
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

    private static ItemGroup BUILDING_GROUP;
    private static ItemGroup FUNCTIONAL_GROUP;
    private static ItemGroup NATURAL_GROUP;
    private static ItemGroup EQUIPMENT_GROUP;
    private static ItemGroup FOODSTUFFS_GROUP;
    private static ItemGroup INGREDIENTS_GROUP;
    private static ItemGroup MISC_GROUP;

    private static final Map<Item, Identifier> BUILDING = new LinkedHashMap<>();
    private static final Map<Item, Identifier> FUNCTIONAL = new LinkedHashMap<>();
    private static final Map<Item, Identifier> NATURAL = new LinkedHashMap<>();
    private static final Map<Item, Identifier> EQUIPMENT = new LinkedHashMap<>();
    private static final Map<Item, Identifier> FOODSTUFFS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> INGREDIENTS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> MISC = new LinkedHashMap<>();

    // ###################
    // # Group: BUILDING #
    // ###################

    // Blocks: Stone
    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs", new StairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CALCITE)), "building",true);
    public static final Block CALCITE_SLAB = registerBlock("calcite_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)), "building", true);
    public static final Block CALCITE_BUTTON = registerBlock("calcite_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.CALCITE).collidable(false), BlockSetType.STONE, 10, false), "building", true);
    public static final Block CALCITE_PRESSURE_PLATE = registerBlock("calcite_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.CALCITE), BlockSetType.STONE), "building", true);
    public static final Block CALCITE_WALL = registerBlock("calcite_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.CALCITE)), "building", true);

    // Blocks: Refined Wood
    public static final Block STRIPPED_HAZEL_LOG = registerBlock("stripped_hazel_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)), "building", true);
    public static final Block STRIPPED_HAZEL_WOOD = registerBlock("stripped_hazel_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)), "building", true);
    public static final Block HAZEL_PLANKS = registerBlock("hazel_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), "building", true);
    public static final Block HAZEL_STAIRS = registerBlock("hazel_stairs", new StairsBlock(HAZEL_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), "building", true);
    public static final Block HAZEL_SLAB = registerBlock("hazel_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), "building", true);
    public static final Block HAZEL_BUTTON = registerBlock("hazel_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).collidable(false), BlockSetType.OAK, 10, true), "building", true);
    public static final Block HAZEL_PRESSURE_PLATE = registerBlock("hazel_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), BlockSetType.OAK), "building", true);
    public static final Block HAZEL_FENCE = registerBlock("hazel_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), "building", true);
    public static final Block HAZEL_FENCE_GATE = registerBlock("hazel_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), WoodType.OAK), "building", true);
    public static final Block HAZEL_DOOR = registerBlock("hazel_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque(), BlockSetType.OAK), "building", true);
    public static final Block HAZEL_TRAPDOOR = registerBlock("hazel_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque(), BlockSetType.OAK), "building", true);

    // Blocks: Ores and Metals
    public static final Block TIN_ORE = registerBlock("tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_ORE)), "building",true);
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_COPPER_ORE)), "building",true);
    public static final Block RAW_TIN_BLOCK = registerBlock("raw_tin_block", new Block(FabricBlockSettings.copyOf(Blocks.RAW_COPPER_BLOCK)), "building", true);
    public static final Block TIN_BLOCK = registerBlock("tin_block", new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)), "building",true);

    // Blocks: Building, Other

    // #####################
    // # Group: FUNCTIONAL #
    // #####################

    // Blocks: Functional
    public static final Block CRUCIBLE = registerBlock("crucible", new CrucibleBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT)), "functional",true);

    // Items: Functional

    // ##################
    // # Group: NATURAL #
    // ##################

    // Blocks: Soil & Similar
    public static final Block PEAT_BLOCK = registerBlock("peat_block", new Block(FabricBlockSettings.copyOf(Blocks.MUD)), "natural",true);

    // Blocks: Logs & Leaves
    public static final Block HAZEL_LOG = registerBlock("hazel_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), "natural", true);
    public static final Block HAZEL_WOOD = registerBlock("hazel_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)), "natural", true);
    public static final Block HAZEL_LEAVES = registerBlock("hazel_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()), "natural", true);

    // Blocks: Natural, Other

    // Items: Seeds

    // Items: Saplings

    // Items: Plants & Flowers

    // Items: Natural, Other

    // ####################
    // # Group: EQUIPMENT #
    // ####################

    // Items: Tools
    public static final Item IRON_PROSPECTING_PICK = registerItem("iron_prospecting_pick", new ProspectingPickItem(ToolMaterials.IRON, 2, 2f, new FabricItemSettings(), 3), "equipment");

    // Items: Weapons

    // Items: Parts

    // Items: Armor

    // Items: Equipment, Other

    // #####################
    // # Group: FOODSTUFFS #
    // #####################

    // Items: Crops
    public static final Item CHILI_PEPPER = registerItem("chili_pepper", new Item(new FabricItemSettings().food(HomeFoodComponents.CHILI_PEPPER)), "foodstuffs");

    // Items: Food

    // Items: Dishes

    // Items: Drinks

    // Items: Foodstuffs, Other

    // ######################
    // # Group: INGREDIENTS #
    // ######################

    // Items: Metals
    public static final Item RAW_TIN = registerItem("raw_tin", new Item(new FabricItemSettings()), "ingredients");
    public static final Item TIN_INGOT = registerItem("tin_ingot", new Item(new FabricItemSettings()), "ingredients");
    public static final Item TIN_NUGGET = registerItem("tin_nugget", new Item(new FabricItemSettings()), "ingredients");
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new FabricItemSettings()), "ingredients");

    // Items: Ingredients, Other
    public static final Item PEAT_BRICK = registerItem("peat_brick", new Item(new FabricItemSettings()), "ingredients");

    // ###############
    // # Group: MISC #
    // ###############

    // Blocks: Misc

    // Items: Misc
    public static final Item CROCODILE_SPEAR = registerItem("crocodile_spear", new Item(new FabricItemSettings().maxCount(1)), "misc");


    private static void assignItemGroup(Item item, Identifier identifier, String group) {
        switch (group) {
            case "building":
                BUILDING.put(item, identifier);
                break;
            case "functional":
                FUNCTIONAL.put(item, identifier);
                break;
            case "natural":
                NATURAL.put(item, identifier);
                break;
            case "equipment":
                EQUIPMENT.put(item, identifier);
                break;
            case "foodstuffs":
                FOODSTUFFS.put(item, identifier);
                break;
            case "ingredients":
                INGREDIENTS.put(item, identifier);
                break;
            case "misc":
                MISC.put(item, identifier);
                break;
            default:
                Homesteading.LOGGER.error("Found unknown item group found while registering {}!", identifier);
        }
    }

    private static Item registerItem(String name, Item item, String group) {
        ITEMS.put(item, Homesteading.createIdentifier(name));
        assignItemGroup(item, Homesteading.createIdentifier(name), group);
        return item;
    }

    private static Block registerBlock(String name, Block block, String group, boolean createBlockItem) {
        BLOCKS.put(block, Homesteading.createIdentifier(name));
        if (createBlockItem) {
            BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
            Identifier identifier = BLOCKS.get(block);
            ITEMS.put(blockItem, identifier);
            assignItemGroup(blockItem, identifier, group);
        }
        return block;
    }

    public static void init() {
        BUILDING_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".building"))
            .icon(Items.OAK_LOG::getDefaultStack).entries((displayContext, entries) -> {
                BUILDING.keySet().forEach(entries::add);
            }).build();

        FUNCTIONAL_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".functional"))
                .icon(Items.FURNACE::getDefaultStack).entries((displayContext, entries) -> {
                    FUNCTIONAL.keySet().forEach(entries::add);
                }).build();

        NATURAL_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".natural"))
                .icon(Items.OAK_SAPLING::getDefaultStack).entries((displayContext, entries) -> {
                    NATURAL.keySet().forEach(entries::add);
                }).build();

        EQUIPMENT_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".equipment"))
                .icon(Items.DIAMOND_AXE::getDefaultStack).entries((displayContext, entries) -> {
                    EQUIPMENT.keySet().forEach(entries::add);
                }).build();

        FOODSTUFFS_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".foodstuffs"))
                .icon(Items.BREAD::getDefaultStack).entries((displayContext, entries) -> {
                    FOODSTUFFS.keySet().forEach(entries::add);
                }).build();

        INGREDIENTS_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".ingredients"))
                .icon(Items.GOLD_INGOT::getDefaultStack).entries((displayContext, entries) -> {
                    INGREDIENTS.keySet().forEach(entries::add);
                }).build();

        MISC_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID + ".misc"))
                .icon(CROCODILE_SPEAR::getDefaultStack).entries((displayContext, entries) -> {
                    MISC.keySet().forEach(entries::add);
                }).build();

        Homesteading.LOGGER.info("Registering tab groups for " + Homesteading.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.a.building"), BUILDING_GROUP);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.b.functional"), FUNCTIONAL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.c.natural"), NATURAL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.d.equipment"), EQUIPMENT_GROUP);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.e.foodstuffs"), FOODSTUFFS_GROUP);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.f.ingredients"), INGREDIENTS_GROUP);
        Registry.register(Registries.ITEM_GROUP, Homesteading.createIdentifier("itemgroups.g.misc"), MISC_GROUP);


        Homesteading.LOGGER.info("Registering blocks for " + Homesteading.MOD_ID);
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));

        Homesteading.LOGGER.info("Registering items for " + Homesteading.MOD_ID);
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
    }
}
