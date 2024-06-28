package jabberdrake.homesteading.common.util;

import jabberdrake.homesteading.Homesteading;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class HomeTags {

    public static class Blocks {
        public static final TagKey<Block> ORE_BLOCKS = createTag("ore_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Homesteading.createIdentifier(name));
        }
    }

    public static class Items {
        public static TagKey<Item> CRUCIBLE_VALID = createTag("crucible_valid");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Homesteading.createIdentifier(name));
        }
    }
}
