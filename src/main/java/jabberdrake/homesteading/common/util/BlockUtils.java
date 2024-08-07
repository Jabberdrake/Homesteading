package jabberdrake.homesteading.common.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BlockUtils {

    // Returns a list of all same blocks within a cuboid centered on the origin block
    public static List<BlockPos> getAllSameNeighbours(World world, BlockPos originPos, BlockState originState, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        List<BlockPos> result = new ArrayList<>();

        for (int dx = minX; dx <= maxX; dx++) {
            for (int dy = minY; dy <= maxY; dy++) {
                for (int dz = minZ; dz <= maxZ; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;

                    BlockPos targetPos = originPos.add(dx, dy, dz);
                    if (world.getBlockState(targetPos).getBlock().equals(originState.getBlock())) {
                        result.add(targetPos);
                    }
                }
            }
        }

        return result;
    }

    // Returns a list of all same blocks above or similarly adjacent to the specified block (used for tree felling!)
    public static List<BlockPos> findAllLogsAbove(World world, BlockPos originPos, BlockState originState) {
        List<BlockPos> logs = new ArrayList<>();
        Queue<BlockPos> queue = new LinkedList<>();

        logs.add(originPos);
        queue.add(originPos);

        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();
            // Never search in positions with a y coordinate lower than that of currentPos
            for (BlockPos sameNeighbour : getAllSameNeighbours(world, currentPos, world.getBlockState(currentPos), -1, 1, 0, 1, -1, 1)) {
                if (!world.getBlockState(sameNeighbour).get(HomesteadingProperties.NATURAL)) continue;
                if (!logs.contains(sameNeighbour)) {
                    logs.add(sameNeighbour);
                    queue.add(sameNeighbour);
                }
            }
        }

        logs.remove(0);
        return logs;
    }

}
