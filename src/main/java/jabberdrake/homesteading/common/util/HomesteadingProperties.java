package jabberdrake.homesteading.common.util;

import net.minecraft.state.property.BooleanProperty;

public class HomesteadingProperties {

    // Indicates whether a block was naturally generated (true) or placed by a player
    public static final BooleanProperty NATURAL = BooleanProperty.of("natural");
}
