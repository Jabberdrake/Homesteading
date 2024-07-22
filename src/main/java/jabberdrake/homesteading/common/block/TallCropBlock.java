package jabberdrake.homesteading.common.block;

import jabberdrake.homesteading.common.registry.HomeObjects;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TallCropBlock extends CropBlock {

    public static final int MAX_STAGE = 3;
    public static final int MAX_HEIGHT = 2;
    public static final int MAX_AGE = 5;

    public static final IntProperty AGE = IntProperty.of("age", 0, 5);

    // Edit these as per your specific crop's necessities
    private static final int[] BLOCKHEIGHT_PER_STAGE = new int[]{1, 1, 2, 2};
    private static final double[] PIXELHEIGHT_PER_STAGE = new double[]{8.0, 14.0, 22.0, 30.0};

    private static VoxelShape[] SHAPE_PER_SEGMENT = null;
    private static Map<Integer, List<Integer>> STAGE_TO_AGES = null;

    public TallCropBlock(Settings settings) {
        super(settings);
        calculateInternalMaps();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (SHAPE_PER_SEGMENT == null) {
            calculateInternalMaps();
        }
        return SHAPE_PER_SEGMENT[state.get(AGE)];
    }

    public void setShapeMap(VoxelShape[] map) {
        SHAPE_PER_SEGMENT = map;
    }

    public Map<Integer, List<Integer>> getAgeMap() {
        if (STAGE_TO_AGES == null) {
            calculateInternalMaps();
        }
        return STAGE_TO_AGES;
    }

    public void setAgeMap(Map<Integer, List<Integer>> map) {
        STAGE_TO_AGES = map;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return HomeObjects.CORN_SEEDS;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState stateBelow = world.getBlockState(pos.down(1));
        return super.canPlaceAt(state, world, pos) || (stateBelow.isOf(this) && !isTopSegment(getStage(stateBelow), getHeight(stateBelow)));
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

    public int getMaxStage() {
        return MAX_STAGE;
    }

    public int getMaxHeight() {
        return MAX_HEIGHT;
    }

    // Getters for the internal maps
    public int[] getBlockHeightMap() {
        return BLOCKHEIGHT_PER_STAGE;
    }

    public double[] getPixelHeightMap() {
        return PIXELHEIGHT_PER_STAGE;
    }

    // Initializes the shape map and the age map, for use in posterior functions
    public void calculateInternalMaps() {

        // Assumes that the parameter objects are set to null
        VoxelShape[] auxShapeMap = new VoxelShape[getMaxAge() + 1];
        Map<Integer, List<Integer>> auxAgeMap = new HashMap<>(getMaxStage() + 1);

        // Initializes auxAgeMap keys
        for (int stage = 0; stage <= getMaxStage(); stage++) {
            auxAgeMap.put(stage, new ArrayList<>());
        }

        int age = 0;
        for (int height = 1; height <= getMaxHeight(); height++) {
            for (int stage = 0; stage <= getMaxStage(); stage++) {
                if (isValidSegment(stage, height)) {
                    auxAgeMap.get(stage).add(age);
                    if (isTopSegment(stage, height)) {
                        auxShapeMap[age] = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, getPixelHeightMap()[stage] - (height - 1) * 16.0, 16.0);
                    } else {
                        auxShapeMap[age] = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
                    }
                    age++;
                }
            }
        }
        setShapeMap(auxShapeMap);
        setAgeMap(auxAgeMap);
    }

    // Auxiliary functions
    public boolean isValidSegment(int stage, int height) {
        return (stage >=0 && stage <= getMaxStage() &&
                height >= 1 && height <= getMaxHeight() &&
                getBlockHeightMap()[stage] >= height);
    }

    public boolean isTopSegment(int stage, int height) {
        return (getBlockHeightMap()[stage] == height);
    }

    public int getStage(BlockState state) {
        int age = state.get(this.getAgeProperty());
        for (int stage = 0; stage <= getMaxStage(); stage++) {
            if (getAgeMap().get(stage).contains(age)) {
                return stage;
            }
        }
        // If, for some reason, the age doesn't correspond to a known stage, default to stage=0.
        return 0;
    }

    public int getHeight(BlockState state) {
        int age = state.get(this.getAgeProperty());
        for (int stage = 0; stage <= getMaxStage(); stage++) {
            if (getAgeMap().get(stage).contains(age)) {
                return getAgeMap().get(stage).indexOf(age) + 1;
            }
        }
        // If, for some reason, the age doesn't correspond to a known height, default to height=1.
        return 1;
    }

    // Growth cycle logic
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int futureStage = getStage(state) + this.getGrowthAmount(world);
        int maxStage = getMaxStage();

        if (futureStage > maxStage) {
            futureStage = maxStage;
        }

        buildCrop(world, pos, futureStage);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int currentStage = getStage(state);
            if (currentStage < getMaxStage()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    buildCrop(world, pos, currentStage + 1);
                }
            }
        }
    }

    public void buildCrop(World world, BlockPos pos, int stage) {
        int fullheight = getBlockHeightMap()[stage];
        if (fullheight > 1) {
            pos = findLowestCropBlock(world, pos);
            for (int height = 0; height < fullheight; height++) {
                BlockPos futurePos = pos.up(height);
                if (world.getBlockState(futurePos).isOf(Blocks.AIR) || world.getBlockState(futurePos).isOf(this))
                    world.setBlockState(pos.up(height), this.withAge(getAgeMap().get(stage).get(height)), Block.NOTIFY_LISTENERS);
            }
        } else {
            // For stages where the crop is 1-block high, the age property value is the same as its corresponding stage.
            world.setBlockState(pos, this.withAge(stage), Block.NOTIFY_LISTENERS);
        }
    }

    public BlockPos findLowestCropBlock(World world, BlockPos pos) {
        for (int i = 0; i < getMaxHeight(); i++) {
            if (!world.getBlockState(pos.down(i+1)).isOf(this)) {
                return pos.down(i);
            }
        }
        return pos;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        BlockPos above = pos.up(1);
        BlockPos below = pos.down(1);
        if (world.getBlockState(above).isOf(this)) {
            world.breakBlock(above, getAge(world.getBlockState(above)) == getMaxAge(), player, 1);
        }
        if (world.getBlockState(below).isOf(this)) {
            world.breakBlock(below, getAge(world.getBlockState(below)) == getMaxAge(), player, 1);
        }
    }
}
