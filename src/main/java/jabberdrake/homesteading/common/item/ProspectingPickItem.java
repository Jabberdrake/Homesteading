package jabberdrake.homesteading.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class ProspectingPickItem extends Item {

    private int range = 3;

    public ProspectingPickItem(Settings settings, int range) {
        super(settings);
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
                            player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " nearby!"), false);
                            break;
                        }
                    }
                if (foundBlock) break;
                }
            if (foundBlock) break;
            }
            if (!foundBlock) {
                player.sendMessage(Text.literal("No ore found nearby!"), false);
            }
        }

        context.getStack().damage(1, player,
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    public int getRange() {
        return range;
    }

    private boolean isOreBlock(BlockState state) {
        return state.isOf(Blocks.IRON_ORE) ||
                state.isOf(Blocks.COAL_ORE) ||
                state.isOf(Blocks.COPPER_ORE) ||
                state.isOf(Blocks.DIAMOND_ORE) ||
                state.isOf(Blocks.REDSTONE_ORE) ||
                state.isOf(Blocks.LAPIS_ORE);
    }
}
