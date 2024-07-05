package jabberdrake.homesteading.mixin;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.util.BlockUtils;
import jabberdrake.homesteading.common.util.HomesteadingProperties;
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

    // Sets the "natural" property of a log block to false if it is placed by a player.
    @Inject(method = "onPlaced", at = @At("TAIL"))
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (state.isIn(BlockTags.LOGS) && state.getBlock() instanceof PillarBlock && placer instanceof PlayerEntity) {
            this.defaultState = defaultState.withIfExists(HomesteadingProperties.NATURAL, false);
        }
    }

    // Tree felling
    @Inject(method = "onBreak", at = @At("TAIL"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        if (!player.isSneaking() && state.getBlock() instanceof PillarBlock && state.isIn(BlockTags.LOGS) && state.get(HomesteadingProperties.NATURAL)) {
            List<BlockPos> logs = BlockUtils.findAllLogsAbove(world, pos, state);
            for (BlockPos log : logs) {
                world.breakBlock(log, true, player, 512);
            }
        }
    }
}
