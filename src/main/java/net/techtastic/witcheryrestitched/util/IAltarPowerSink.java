package net.techtastic.witcheryrestitched.util;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.AltarBlockEntity;

import java.util.HashMap;
import java.util.Map;

public interface IAltarPowerSink {
    default BlockPos locateNearestAltar(World world, BlockPos sink) {
        Map<String, BlockPos> altarsFound = new HashMap<>();
        BlockPos start = sink.north(32).east(32).up(32);
        BlockPos end = sink.south(32).west(32).down(32);
        BlockBox range = BlockBox.create(start, end);

        for (int y = range.getMinY(); y < range.getMaxY() + 1; y++) {
            for (int x = range.getMinX(); x < range.getMaxX() + 1; x++) {
                for (int z = range.getMinZ(); z < range.getMaxZ() + 1; z++) {
                    BlockPos testPos = new BlockPos(x, y, z);

                    if (world.getBlockState(testPos).getBlock().equals(ModBlocks.ALTAR)) {
                        AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(testPos);

                        if (altar.isMultiblock()) {
                            altarsFound.put("" + altarsFound.size(), testPos);
                        }
                    }
                }
            }
        }

        if (!altarsFound.isEmpty()) {
            BlockPos closest = null;
            for (String key : altarsFound.keySet()) {
                if (closest == null) {
                    closest = altarsFound.get(key);
                } else if (altarsFound.get(key).getSquaredDistanceFromCenter(sink.getX(), sink.getY(), sink.getZ()) <
                        closest.getSquaredDistanceFromCenter(sink.getX(), sink.getY(), sink.getZ())) {
                    closest = altarsFound.get(key);
                }
            }

            return closest;
        }

        return null;
    }

    default boolean isWithinAltarRange(World world, BlockPos sink, AltarBlockEntity altar) {
        if (altar != null) {
            AltarBlockEntity master = (AltarBlockEntity) world.getBlockEntity(altar.getMasterBlockPos());

            if (master != null) {
                return sink.isWithinDistance(altar.getPos(), master.getAltarRange());
            }
        }
        return false;
    }

    default boolean attemptAltarPowerDraw(World world, AltarBlockEntity altar, int power) {
        if (altar != null) {
            AltarBlockEntity master = (AltarBlockEntity) world.getBlockEntity(altar.getMasterBlockPos());

            if (master != null) {
                if (master.getCurrentAltarPower() > power) {
                    master.drawPower(power);
                    master.markDirty();

                    return true;
                }
            }
        }
        return false;
    }

    default boolean doesAltarHavePower(World world, AltarBlockEntity altar) {
        if (altar != null) {
            AltarBlockEntity master = (AltarBlockEntity) world.getBlockEntity(altar.getMasterBlockPos());

            if (master != null) {
                return master.getCurrentAltarPower() > 3;
            }
        }
        return false;
    }
}