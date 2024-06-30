package jabberdrake.homesteading.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {

    @Shadow
    public abstract boolean isIn(TagKey<Block> tag);

    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("HEAD"), cancellable = true)
    private void adaptOcclusionLightLevel(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (this.isIn(BlockTags.LEAVES)) {
            cir.setReturnValue(0.2F);
        }
    }

    @Inject(method = "canPathfindThrough", at = @At("HEAD"), cancellable = true)
    private void canPathfindThrough(BlockView world, BlockPos pos, NavigationType type, CallbackInfoReturnable<Boolean> cir) {
        if (this.isIn(BlockTags.LEAVES)) {
            cir.setReturnValue(true);
        }
    }

}
