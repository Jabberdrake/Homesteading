package jabberdrake.homesteading.common.item;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.util.HomeTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.text.Normalizer;
import java.util.List;

public class ProspectingPickItem extends PickaxeItem {

    private int range = 3;

    public ProspectingPickItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, int range) {
        super(material, attackDamage, attackSpeed, settings);
        this.range = range;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        assert player != null;

        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            boolean foundBlock = false;

            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    for (int z = -range; z <= range; z++) {
                        BlockPos pos = positionClicked.add(x, y, z);
                        BlockState state = context.getWorld().getBlockState(pos);
                        Block block = state.getBlock();

                        if (isOreBlock(state)) {
                            foundBlock = true;
                            player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " nearby!").formatted(Formatting.LIGHT_PURPLE), true);
                            break;
                        }
                    }
                if (foundBlock) break;
                }
            if (foundBlock) break;
            }
            if (!foundBlock) {
                player.sendMessage(Text.literal("No ore found nearby...").formatted(Formatting.GRAY, Formatting.ITALIC), true);
            }
        }

        context.getStack().damage(1, player,
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip." + Homesteading.MOD_ID + ".prospecting_pick_range", "§d" + range).formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }

    private boolean isOreBlock(BlockState state) {
        return state.isIn(HomeTags.Blocks.ORE_BLOCKS);
    }

    public int getRange() {
        return range;
    }
}
