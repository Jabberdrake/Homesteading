package jabberdrake.homesteading.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VineBlock.class)
public abstract class VineBlockMixin {

    @Inject(at = @At("HEAD"), method = "shouldConnectTo", cancellable = true)
    private static void shouldConnectTo(BlockView world, BlockPos neighborPos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockstate = world.getBlockState(neighborPos);
        if (blockstate.isIn(BlockTags.LEAVES)) {
            cir.setReturnValue(true);
        }
    }

}
