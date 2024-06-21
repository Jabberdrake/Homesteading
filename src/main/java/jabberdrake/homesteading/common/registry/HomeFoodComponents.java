package jabberdrake.homesteading.common.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class HomeFoodComponents {

    public static final FoodComponent CHILI_PEPPER = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 400), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1), 1f)
            .build();
}
