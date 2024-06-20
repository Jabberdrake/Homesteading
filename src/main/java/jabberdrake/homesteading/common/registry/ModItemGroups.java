package jabberdrake.homesteading.common.registry;

import jabberdrake.homesteading.Homesteading;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    private static final ItemGroup GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(Homesteading.MOD_ID, "homesteading"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + Homesteading.MOD_ID))
                    .icon(() -> new ItemStack(ModItems.TIN_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_TIN);
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModItems.TIN_NUGGET);
                    }).build()
    );

    public static void registerItemGroups() {
        Homesteading.LOGGER.info("Registering item groups for " + Homesteading.MOD_ID);
    }
}
