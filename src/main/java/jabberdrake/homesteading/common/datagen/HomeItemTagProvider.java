package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjects;
import jabberdrake.homesteading.common.util.HomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class HomeItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public HomeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // [Vanilla] A set of all planks blocks.
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(HomeObjects.HAZEL_PLANKS.asItem());

        // [Vanilla] A set of all flammable log blocks.
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(HomeObjects.HAZEL_LOG.asItem())
                .add(HomeObjects.HAZEL_WOOD.asItem())
                .add(HomeObjects.STRIPPED_HAZEL_LOG.asItem())
                .add(HomeObjects.STRIPPED_HAZEL_WOOD.asItem());

        //[Homesteading] All items that can go in a Crucible
        getOrCreateTagBuilder(HomeTags.Items.CRUCIBLE_VALID)
                .add(HomeObjects.TIN_NUGGET)
                .add(HomeObjects.RAW_TIN_CHUNK)
                .add(HomeObjects.COPPER_NUGGET)
                .add(HomeObjects.RAW_COPPER_CHUNK)
                .add(HomeObjects.ZINC_NUGGET)
                .add(HomeObjects.RAW_ZINC_CHUNK)
                .add(HomeObjects.BISMUTH_NUGGET)
                .add(HomeObjects.RAW_BISMUTH_CHUNK)
                .add(HomeObjects.SILVER_NUGGET)
                .add(HomeObjects.RAW_SILVER_CHUNK)
                .add(HomeObjects.IRON_BLOOM_CHUNK)
                .add(Items.IRON_NUGGET)
                .add(HomeObjects.RAW_IRON_CHUNK)
                .add(HomeObjects.CAST_IRON_NUGGET)
                .add(Items.GOLD_NUGGET)
                .add(HomeObjects.RAW_GOLD_CHUNK)
                .add(HomeObjects.BRONZE_NUGGET)
                .add(HomeObjects.BRASS_NUGGET)
                .add(HomeObjects.ELECTRUM_NUGGET);

        //[Homesteading] Bits of a certain metal type, used in Crucible Recipe Logic; No need to comment every individual one, as they will be MANY.
        getOrCreateTagBuilder(HomeTags.Items.COPPER_CHUNKS)
                .add(HomeObjects.COPPER_NUGGET)
                .add(HomeObjects.RAW_COPPER_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.WROUGHT_IRON_CHUNKS)
                .add(Items.IRON_NUGGET)
                .add(HomeObjects.IRON_BLOOM_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.CAST_IRON_CHUNKS)
                .add(HomeObjects.CAST_IRON_NUGGET)
                .add(HomeObjects.RAW_IRON_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.GOLD_CHUNKS)
                .add(Items.GOLD_NUGGET)
                .add(HomeObjects.RAW_GOLD_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.TIN_CHUNKS)
                .add(HomeObjects.TIN_NUGGET)
                .add(HomeObjects.RAW_TIN_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.ZINC_CHUNKS)
                .add(HomeObjects.ZINC_NUGGET)
                .add(HomeObjects.RAW_ZINC_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.BISMUTH_CHUNKS)
                .add(HomeObjects.BISMUTH_NUGGET)
                .add(HomeObjects.RAW_BISMUTH_CHUNK);
        getOrCreateTagBuilder(HomeTags.Items.SILVER_CHUNKS)
                .add(HomeObjects.SILVER_NUGGET)
                .add(HomeObjects.RAW_SILVER_CHUNK);
    }
}
