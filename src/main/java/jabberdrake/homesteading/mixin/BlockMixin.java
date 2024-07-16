package jabberdrake.homesteading.mixin;

import jabberdrake.homesteading.common.util.BlockUtils;
import jabberdrake.homesteading.common.util.HomesteadingProperties;
import jabberdrake.homesteading.common.util.PlayerUtils;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.slf4j.Logger;

import java.util.List;

@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock {

    private static final Logger log = LoggerFactory.getLogger(BlockMixin.class);
    @Shadow
    private BlockState defaultState;

    public BlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "onPlaced", at = @At("TAIL"))
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {

        // Sets the "natural" property of a log block to false if it is placed by a player.
        if (state.isIn(BlockTags.LOGS) && state.getBlock() instanceof PillarBlock && placer instanceof PlayerEntity) {
            this.defaultState = defaultState.withIfExists(HomesteadingProperties.NATURAL, false);
        }
    }

    @Inject(method = "onBreak", at = @At("TAIL"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {

        // Handles tree felling logic if:
        // - the block in question is a log block;
        // - the log block is "natural" (wasn't placed by a player);
        // - the player is not sneaking;
        // - the player is using an axe;
        if (PlayerUtils.canFellTree(world, pos, state, player)) {
            List<BlockPos> logs = BlockUtils.findAllLogsAbove(world, pos, state);
            for (BlockPos log : logs) {
                world.breakBlock(log, true, player, 512);
                // Axe item may break during this loop, hence the per-loop check
                if (!player.getMainHandStack().isEmpty())
                    player.getMainHandStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
            }
        }
    }
}
