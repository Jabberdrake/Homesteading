package jabberdrake.homesteading;

import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class HomesteadingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Homesteading.LOGGER.info("Initializing client for " + Homesteading.MOD_ID);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                HomeObjectRegistry.HAZEL_DOOR,
                HomeObjectRegistry.HAZEL_TRAPDOOR,
                HomeObjectRegistry.HAZEL_LEAVES,
                HomeObjectRegistry.CRUCIBLE);
    }
}
