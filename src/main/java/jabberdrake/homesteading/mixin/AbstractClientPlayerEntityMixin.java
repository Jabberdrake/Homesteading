package jabberdrake.homesteading.mixin;

import com.mojang.authlib.GameProfile;
import jabberdrake.homesteading.access.EntityAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity implements EntityAccess {

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @ModifyVariable(method = "getFovMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getActiveItem()Lnet/minecraft/item/ItemStack;"))
    private float getFovMultiplier(float f) {
        if (this.homesteading$isInBlockOfTag(BlockTags.LEAVES)) {
            f *= 0.8f;
        }
        return f;
    }
}
