package jabberdrake.homesteading.common.block;

import jabberdrake.homesteading.common.block.blockentities.CrucibleBlockEntity;
import jabberdrake.homesteading.common.util.HomeTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrucibleBlock  extends BlockWithEntity {
    public static final IntProperty LEVEL;

    protected final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 10.0, 12.0);

    public CrucibleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(LEVEL, 0)));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity crucible = world.getBlockEntity(pos);
        int level = state.get(LEVEL);
        int lowerLevel = level - 1;
        int higherLevel = level + 1;

        if (crucible instanceof CrucibleBlockEntity) {
            if (!player.getAbilities().allowModifyWorld) {
                return ActionResult.PASS;
            } else if (player.getStackInHand(hand).getItem() == Items.STICK && level == 9) {
                ((CrucibleBlockEntity) crucible).tellRecipe(world, player);
                return ActionResult.SUCCESS;
            } else if (player.isSneaking() && level == 9) {
                ((CrucibleBlockEntity) crucible).processRecipes(world, player, pos);
                return ActionResult.SUCCESS;
            } else if (player.getStackInHand(hand).isEmpty() && level > 0) {

                for (int i = 8; i >= 0; i--) {

                    if (((CrucibleBlockEntity) crucible).canRemoveItem(i)) {
                        player.getInventory().offerOrDrop(((CrucibleBlockEntity) crucible).removeItem(i));
                        world.setBlockState(pos, (BlockState) state.with(LEVEL, lowerLevel), Block.NOTIFY_ALL);
                        return ActionResult.SUCCESS;
                    }
                }
            } else if (player.getStackInHand(hand).isIn(HomeTags.Items.CRUCIBLE_VALID) && level < 9) {
                for (int j = 0; j < 9; j++) {

                    if (((CrucibleBlockEntity) crucible).addItem(player.getStackInHand(hand), j)) {
                        world.setBlockState(pos, (BlockState) state.with(LEVEL, higherLevel), Block.NOTIFY_ALL);
                        return ActionResult.SUCCESS;
                    }
                }
            } else return ActionResult.FAIL;
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        breakCrucible(world, pos, state);
    }

    private void breakCrucible(World world, BlockPos pos, BlockState state) {
        BlockEntity crucible = world.getBlockEntity(pos);

        if (crucible instanceof CrucibleBlockEntity) {
            for (ItemStack stack : ((CrucibleBlockEntity) crucible).getInventory()) {
                dropStack(world, pos, stack);
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    static {
        LEVEL = IntProperty.of("crucible_level", 0, 9);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrucibleBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
