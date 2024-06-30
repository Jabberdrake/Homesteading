package jabberdrake.homesteading.access;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;

public interface EntityAccess {
    default BlockPos homesteading$getBlockPosByTag(TagKey<Block> tag) {
        return null;
    }

    default boolean homesteading$isInBlockOfTag(TagKey<Block> tag) {
        return false;
    }
}
