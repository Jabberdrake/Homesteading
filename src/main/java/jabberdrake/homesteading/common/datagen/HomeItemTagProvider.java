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
                .add(HomeObjects.RAW_TIN_BIT)
                .add(HomeObjects.COPPER_NUGGET)
                .add(HomeObjects.RAW_COPPER_BIT)
                .add(HomeObjects.ZINC_NUGGET)
                .add(HomeObjects.RAW_ZINC_BIT)
                .add(HomeObjects.BISMUTH_NUGGET)
                .add(HomeObjects.RAW_BISMUTH_BIT)
                .add(HomeObjects.SILVER_NUGGET)
                .add(HomeObjects.RAW_SILVER_BIT)
                .add(Items.IRON_NUGGET)
                .add(HomeObjects.RAW_IRON_BIT)
                .add(HomeObjects.CAST_IRON_NUGGET)
                .add(Items.GOLD_NUGGET)
                .add(HomeObjects.RAW_GOLD_BIT)
                .add(HomeObjects.BRONZE_NUGGET)
                .add(HomeObjects.BRASS_NUGGET)
                .add(HomeObjects.ELECTRUM_NUGGET);

        //[Homesteading] Bits of a certain metal type, used in Crucible Recipe Logic; No need to comment every individual one, as they will be MANY.
        getOrCreateTagBuilder(HomeTags.Items.COPPER_BITS)
                .add(HomeObjects.COPPER_NUGGET)
                .add(HomeObjects.RAW_COPPER_BIT);
        getOrCreateTagBuilder(HomeTags.Items.WROUGHT_IRON_BITS)
                .add(Items.IRON_NUGGET);
        getOrCreateTagBuilder(HomeTags.Items.CAST_IRON_BITS)
                .add(HomeObjects.CAST_IRON_NUGGET)
                .add(HomeObjects.RAW_IRON_BIT);
        getOrCreateTagBuilder(HomeTags.Items.GOLD_BITS)
                .add(Items.GOLD_NUGGET)
                .add(HomeObjects.RAW_GOLD_BIT);
        getOrCreateTagBuilder(HomeTags.Items.TIN_BITS)
                .add(HomeObjects.TIN_NUGGET)
                .add(HomeObjects.RAW_TIN_BIT);
        getOrCreateTagBuilder(HomeTags.Items.ZINC_BITS)
                .add(HomeObjects.ZINC_NUGGET)
                .add(HomeObjects.RAW_ZINC_BIT);
        getOrCreateTagBuilder(HomeTags.Items.BISMUTH_BITS)
                .add(HomeObjects.BISMUTH_NUGGET)
                .add(HomeObjects.RAW_BISMUTH_BIT);
        getOrCreateTagBuilder(HomeTags.Items.SILVER_BITS)
                .add(HomeObjects.SILVER_NUGGET)
                .add(HomeObjects.RAW_SILVER_BIT);
    }
}
