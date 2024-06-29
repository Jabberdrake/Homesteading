package jabberdrake.homesteading;

import jabberdrake.homesteading.common.registry.HomeObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class HomesteadingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Homesteading.LOGGER.info("Initializing client for " + Homesteading.MOD_ID);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                HomeObjects.HAZEL_DOOR,
                HomeObjects.HAZEL_TRAPDOOR,
                HomeObjects.HAZEL_LEAVES,
                HomeObjects.CRUCIBLE);
    }
}
