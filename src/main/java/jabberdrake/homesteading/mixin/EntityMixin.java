package jabberdrake.homesteading.mixin;

import jabberdrake.homesteading.access.EntityAccess;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityAccess {

    @Shadow
    private World world;

    @Shadow public float fallDistance;

    @Shadow
    public abstract Box getBoundingBox();

    @Shadow
    public abstract int getSafeFallDistance();

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Override
    public BlockPos homesteading$getBlockPosByTag(TagKey<Block> tag) {
        Box contractedBoundingBox = this.getBoundingBox().contract(0.1f);
        return BlockPos.stream(contractedBoundingBox).filter((pos) -> {
            BlockState blockState = this.world.getBlockState(pos);
            return blockState.isIn(tag);
        }).findFirst().orElse(null);
    }

    @Override
    public boolean homesteading$isInBlockOfTag(TagKey<Block> tag) {
        return homesteading$getBlockPosByTag(tag) != null;
    }

    @Inject(method = "getVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    private void getVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        if (this.homesteading$isInBlockOfTag(BlockTags.LEAVES)) {
            cir.setReturnValue(0.8f);
        }
    }

    @Inject(method = "getJumpVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    private void getJumpVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        if (this.homesteading$isInBlockOfTag(BlockTags.LEAVES) && this.fallDistance < this.getSafeFallDistance()) {
            cir.setReturnValue(0.8f);
        }
    }

    @Inject(method = "playStepSound", at = @At("TAIL"))
    private void playLeafStepSound(CallbackInfo ci) {
        if (this.homesteading$isInBlockOfTag(BlockTags.LEAVES)) {
            BlockSoundGroup soundGroup = BlockSoundGroup.AZALEA_LEAVES;
            this.playSound(soundGroup.getBreakSound(), soundGroup.getVolume() * 0.6F, soundGroup.getPitch());
        }
    }

}
