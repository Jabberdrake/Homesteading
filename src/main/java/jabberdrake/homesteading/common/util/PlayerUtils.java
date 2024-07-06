package jabberdrake.homesteading.common.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerUtils {

    // Returns TRUE if the player is flying and in creative mode; FALSE otherwise.
    public static boolean isFlyingInCreative(PlayerEntity playerEntity) {
        return playerEntity.getAbilities().creativeMode && playerEntity.getAbilities().flying;
    }

    // Returns TRUE if the player meets the conditions to execute tree felling; FALSE otherwise.
    // To execute tree felling, the following conditions must be met:
    // - the block in question is a log block;
    // - the log block is "natural" (as in, not placed by a player);
    // - the player is not crouching;
    // - the player is holding a valid axe item;
    public static boolean canFellTree(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        return (state.getBlock() instanceof PillarBlock && state.isIn(BlockTags.LOGS) &&
                state.get(HomesteadingProperties.NATURAL) &&
                !player.isSneaking() && player.getMainHandStack().getItem() instanceof AxeItem);
    }
}
