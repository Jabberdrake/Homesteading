package jabberdrake.homesteading.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.LeavesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin {

    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0)
    private static AbstractBlock.Settings modifySettings(AbstractBlock.Settings settings) {
        return settings.noCollision();
    }
}
