package jabberdrake.homesteading.mixin;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.registry.HomeObjects;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useCrocodileSpearModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(HomeObjects.CROCODILE_SPEAR) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).homesteading$getModels().getModelManager().getModel(new ModelIdentifier(Homesteading.MOD_ID, "crocodile_spear_3d", "inventory"));
        }
        return value;
    }
}