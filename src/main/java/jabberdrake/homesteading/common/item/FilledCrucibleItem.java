package jabberdrake.homesteading.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FilledCrucibleItem extends Item {
    private String contents;

    public FilledCrucibleItem(String contents, Settings settings) {
        super(settings.maxCount(1));
        this.contents = contents;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Contains ").append(Text.translatable("tooltip.homesteading." + contents)));
    }
}
