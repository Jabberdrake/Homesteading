package jabberdrake.homesteading.common.datagen;

import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import jabberdrake.homesteading.common.util.HomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class HomeItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public HomeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        //[Homesteading] All items that can go in a Crucible
        getOrCreateTagBuilder(HomeTags.Items.CRUCIBLE_VALID)
                .add(Items.IRON_NUGGET)
                .add(Items.GOLD_NUGGET)
                .add(HomeObjectRegistry.TIN_NUGGET)
                .add(HomeObjectRegistry.COPPER_NUGGET);
    }
}
