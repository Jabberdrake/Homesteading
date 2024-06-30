package jabberdrake.homesteading.common.util;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerUtils {

    public static boolean isFlyingInCreative(PlayerEntity playerEntity) {
        return playerEntity.getAbilities().creativeMode && playerEntity.getAbilities().flying;
    }
}
