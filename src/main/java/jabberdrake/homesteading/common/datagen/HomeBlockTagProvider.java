package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjects;
import jabberdrake.homesteading.common.util.HomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class HomeBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public HomeBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        // [Vanilla] All blocks that can be broken with a pickaxe.
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(HomeObjects.TIN_ORE)
                .add(HomeObjects.DEEPSLATE_TIN_ORE)
                .add(HomeObjects.RAW_TIN_BLOCK)
                .add(HomeObjects.TIN_BLOCK);

        // [Vanilla] All blocks that can be broken with a stone-tier tool.
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(HomeObjects.TIN_ORE)
                .add(HomeObjects.DEEPSLATE_TIN_ORE)
                .add(HomeObjects.RAW_TIN_BLOCK)
                .add(HomeObjects.TIN_BLOCK);

        // [Vanilla] A set of all fence blocks.
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(HomeObjects.HAZEL_FENCE);

        // [Vanilla] A set of all fence gate blocks.
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(HomeObjects.HAZEL_FENCE_GATE);

        // [Vanilla] A set of all wall blocks.
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(HomeObjects.CALCITE_WALL);

        // [Vanilla] A set of all flammable log blocks.
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(HomeObjects.HAZEL_LOG)
                .add(HomeObjects.HAZEL_WOOD)
                .add(HomeObjects.STRIPPED_HAZEL_LOG)
                .add(HomeObjects.STRIPPED_HAZEL_WOOD);


        // [Homesteading] A set of all vanilla or Homesteading ore blocks.
        getOrCreateTagBuilder(HomeTags.Blocks.ORE_BLOCKS)
                .add(HomeObjects.TIN_ORE)
                .forceAddTag(BlockTags.COAL_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES);
    }
}
