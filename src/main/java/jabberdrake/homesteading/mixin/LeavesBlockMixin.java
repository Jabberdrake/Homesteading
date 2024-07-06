package jabberdrake.homesteading.mixin;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.callback.LocalCapture.CAPTURE_FAILSOFT;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin {

    @Shadow public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0)
    private static AbstractBlock.Settings modifySettings(AbstractBlock.Settings settings) {
        return settings.noCollision();
    }

    @Inject(at = @At("TAIL"), method = "scheduledTick", locals = CAPTURE_FAILSOFT, remap = false)
    private void onScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

        ServerTickEvents.END_SERVER_TICK.register(tick -> {
            BlockState newState = world.getBlockState(pos);
            if (newState.getBlock() instanceof LeavesBlock && Math.random() > 0.99f) {
                this.randomTick(newState, world, pos, random);
            }
        });
    }
}
