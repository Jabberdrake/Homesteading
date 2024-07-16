package jabberdrake.homesteading.common.block;

import jabberdrake.homesteading.common.registry.HomeObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.List;
import java.util.Map;

public class FlaxCropBlock extends TallCropBlock {

    public static final int MAX_STAGE = 7;
    public static final int MAX_HEIGHT = 2;
    public static final int MAX_AGE = 11;

    public static final IntProperty AGE = IntProperty.of("age", 0, 11);

    // Edit these as per your specific crop's necessities
    private static final int[] BLOCKHEIGHT_PER_STAGE = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
    private static final double[] PIXELHEIGHT_PER_STAGE = new double[]{6.0, 10.0, 14.0, 16.0, 22.0, 26.0, 32.0, 32.0};

    private static VoxelShape[] SHAPE_PER_SEGMENT = null;
    private static Map<Integer, List<Integer>> STAGE_TO_AGES = null;

    public FlaxCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (SHAPE_PER_SEGMENT == null) {
            this.calculateInternalMaps();
        }
        return SHAPE_PER_SEGMENT[state.get(AGE)];
    }

    @Override
    public void setShapeMap(VoxelShape[] map) {
        SHAPE_PER_SEGMENT = map;
    }

    @Override
    public Map<Integer, List<Integer>> getAgeMap() {
        if (STAGE_TO_AGES == null) {
            this.calculateInternalMaps();
        }
        return STAGE_TO_AGES;
    }

    @Override
    public void setAgeMap(Map<Integer, List<Integer>> map) {
        STAGE_TO_AGES = map;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return HomeObjects.FLAX_SEEDS;
    }

    // Basic property and limit getters
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public int getMaxStage() {
        return MAX_STAGE;
    }

    @Override
    public int getMaxHeight() {
        return MAX_HEIGHT;
    }

    // Getters for the internal maps
    @Override
    public int[] getBlockHeightMap() {
        return BLOCKHEIGHT_PER_STAGE;
    }

    @Override
    public double[] getPixelHeightMap() {
        return PIXELHEIGHT_PER_STAGE;
    }
}
