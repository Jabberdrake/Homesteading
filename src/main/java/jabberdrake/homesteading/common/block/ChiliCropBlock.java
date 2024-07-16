package jabberdrake.homesteading.common.block;

import jabberdrake.homesteading.common.registry.HomeObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class ChiliCropBlock extends CropBlock {

    public static final int MAX_AGE = 2;
    public static final IntProperty AGE = Properties.AGE_2;

    public ChiliCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return HomeObjects.CHILI_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
