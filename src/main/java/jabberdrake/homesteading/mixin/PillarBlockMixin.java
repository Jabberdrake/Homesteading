package jabberdrake.homesteading.mixin;

import jabberdrake.homesteading.common.util.BlockUtils;
import jabberdrake.homesteading.common.util.HomesteadingProperties;
import jabberdrake.homesteading.common.util.PlayerUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PillarBlock.class)
public abstract class PillarBlockMixin extends Block {

    public PillarBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("TAIL"), method = "appendProperties(Lnet/minecraft/state/StateManager$Builder;)V")
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(HomesteadingProperties.NATURAL);
    }

    @Inject(method = "<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V", at = @At(value = "TAIL"))
    private void tailConstructor(AbstractBlock.Settings settings, CallbackInfo ci) {
        this.setDefaultState(this.getDefaultState().withIfExists(HomesteadingProperties.NATURAL, true));
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        float delta = super.calcBlockBreakingDelta(state, player, world, pos);
        World playerWorld = player.getWorld();
        if (PlayerUtils.canFellTree(playerWorld, pos, state, player)) {
            var numLogs = BlockUtils.findAllLogsAbove(playerWorld, pos, state).size();
            // Credit for this formula goes to magneticflux
            delta /= (float) Math.pow(numLogs + 1, 0.75);
        }
        return delta;
    }
}
