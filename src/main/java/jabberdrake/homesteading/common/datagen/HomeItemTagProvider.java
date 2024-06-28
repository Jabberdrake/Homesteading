package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import jabberdrake.homesteading.common.util.HomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class HomeItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public HomeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // [Vanilla] A set of all planks blocks.
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(HomeObjectRegistry.HAZEL_PLANKS.asItem());

        // [Vanilla] A set of all flammable log blocks.
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(HomeObjectRegistry.HAZEL_LOG.asItem())
                .add(HomeObjectRegistry.HAZEL_WOOD.asItem())
                .add(HomeObjectRegistry.STRIPPED_HAZEL_LOG.asItem())
                .add(HomeObjectRegistry.STRIPPED_HAZEL_WOOD.asItem());

        //[Homesteading] All items that can go in a Crucible
        getOrCreateTagBuilder(HomeTags.Items.CRUCIBLE_VALID)
                .add(Items.IRON_NUGGET)
                .add(Items.GOLD_NUGGET)
                .add(HomeObjectRegistry.TIN_NUGGET)
                .add(HomeObjectRegistry.COPPER_NUGGET);

    }
}
