package net.techtastic.witcheryrestitched.block.custom;


import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.TestAltarBlockEntity;
import org.jetbrains.annotations.Nullable;

public class TestAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    public TestAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        TestAltarBlockEntity be = (TestAltarBlockEntity) world.getBlockEntity(pos);

        TestAltarBlockEntity opposite = getOpposite(world, pos);
        if (opposite != null) {
            int quadrant = getQuadrant(pos, opposite.getPos());
            if (quadrant == 1) {
                    if (isVertical(pos, opposite.getPos())) {
                        int count = 0;
                        for (int x = 0; x < 2; x++) {
                            for (int z = 0; z < 3; z++) {
                                if (isTestAltarThere(world, pos, x, z)) {
                                    count++;
                                }
                            }
                        }
                        if (count == 6) {
                            BlockPos masterBlock = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                            for (int x = 0; x < 2; x++) {
                                for (int z = 0; z < 3; z++) {
                                    getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                    getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                    getOppositeBlock(world, pos, x, z).markDirty();
                                }
                            }
                            getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                        }
                    } else {
                        int count = 0;
                        for (int x = 0; x < 3; x++) {
                            for (int z = 0; z < 2; z++) {
                                if (isTestAltarThere(world, pos, x, z)) {
                                    count++;
                                }
                            }
                        }
                        if (count == 6) {
                            BlockPos masterBlock = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                            for (int x = 0; x < 3; x++) {
                                for (int z = 0; z < 2; z++) {
                                    getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                    getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                    getOppositeBlock(world, pos, x, z).markDirty();
                                }
                            }
                            getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                        }
                    }
            } else if (quadrant == 2) {
                if (isVertical(pos, opposite.getPos())) {
                    int count = 0;
                    for (int x = 0; x > -2; x--) {
                        for (int z = 0; z < 3; z++) {
                            if (isTestAltarThere(world, pos, x, z)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        BlockPos masterBlock = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                        for (int x = 0; x > -2; x--) {
                            for (int z = 0; z < 3; z++) {
                                getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                getOppositeBlock(world, pos, x, z).markDirty();
                            }
                        }
                        getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                    }
                } else {
                    int count = 0;
                    for (int x = 0; x > -3; x--) {
                        for (int z = 0; z < 2; z++) {
                            if (isTestAltarThere(world, pos, x, z)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        BlockPos masterBlock = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                        for (int x = 0; x > -3; x--) {
                            for (int z = 0; z < 2; z++) {
                                getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                getOppositeBlock(world, pos, x, z).markDirty();
                            }
                        }
                        getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                    }
                }
            } else if (quadrant == 3) {
                if (isVertical(pos, opposite.getPos())) {
                    int count = 0;
                    for (int x = 0; x > -2; x--) {
                        for (int z = 0; z > -3; z--) {
                            if (isTestAltarThere(world, pos, x, z)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        BlockPos masterBlock = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                        for (int x = 0; x > -2; x--) {
                            for (int z = 0; z > -3; z--) {
                                getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                getOppositeBlock(world, pos, x, z).markDirty();
                            }
                        }
                        getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                    }
                } else {
                    int count = 0;
                    for (int x = 0; x > -3; x--) {
                        for (int z = 0; z > -2; z--) {
                            if (isTestAltarThere(world, pos, x, z)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        BlockPos masterBlock = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                        for (int x = 0; x > -3; x--) {
                            for (int z = 0; z > -2; z--) {
                                getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                getOppositeBlock(world, pos, x, z).markDirty();
                            }
                        }
                        getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                    }
                }
            } else {
                if (isVertical(pos, opposite.getPos())) {
                    int count = 0;
                    for (int x = 0; x < 2; x++) {
                        for (int z = 0; z > -3; z--) {
                            if (isTestAltarThere(world, pos, x, z)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        BlockPos masterBlock = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                        for (int x = 0; x < 2; x++) {
                            for (int z = 0; z > -3; z--) {
                                getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                getOppositeBlock(world, pos, x, z).markDirty();
                            }
                        }
                        getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                    }
                } else {
                    int count = 0;
                    for (int x = 0; x < 3; x++) {
                        for (int z = 0; z > -2; z--) {
                            if (isTestAltarThere(world, pos, x, z)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        BlockPos masterBlock = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                        for (int x = 0; x < 3; x++) {
                            for (int z = 0; z > -2; z--) {
                                getOppositeBlock(world, pos, x, z).setMultiblock(true);
                                getOppositeBlock(world, pos, x, z).setMasterBlockPos(masterBlock);
                                getOppositeBlock(world, pos, x, z).markDirty();
                            }
                        }
                        getOppositeBlock(world, masterBlock, 0, 0).setMasterBlock(true);
                    }
                }
            }
        }

        be.markDirty();
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClient) {
            TestAltarBlockEntity be = (TestAltarBlockEntity) world.getBlockEntity(pos);

            if (be.isMultiblock()) {
                if (be.isMasterBlock()) {
                    //FOR MASTER BLOCK FUNCTIONS
                    entity.sendMessage(Text.of("The max power is " + be.getMaxAltarPower()));
                    entity.sendMessage(Text.of("The max range is " + be.getMaxRange()));
                    entity.sendMessage(Text.of("The max recharge rate is " + be.getRechargeRate()));
                } else {
                    //PASS TO MASTER BLOCK
                    onLandedUpon(world, state, be.getMasterBlockPos(), entity, fallDistance);
                }
            }
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TestAltarBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.TEST_ALTAR, (world1, pos, state1, be) -> TestAltarBlockEntity.tick(world1, pos, state1, be));
    }

    public static TestAltarBlockEntity getOppositeBlock(World world, BlockPos pos, int xOffset, int zOffset) {
        return (TestAltarBlockEntity) world.getBlockEntity(new BlockPos(pos.getX() + xOffset, pos.getY(), pos.getZ() + zOffset));
    }
    public static TestAltarBlockEntity getOpposite(World world, BlockPos pos) {
        TestAltarBlockEntity opposite = null;

            //Testing Quadrant I (+X,+Z)
        if (isTestAltarThere(world, pos, 1, 2)) {
            opposite = getOppositeBlock(world, pos, 1, 2);
        } else if (isTestAltarThere(world, pos, 2, 1)) {
            opposite = getOppositeBlock(world, pos, 2, 1);
        }
            //Testing Quadrant II (-X,+Z)
        if (isTestAltarThere(world, pos, -1, 2)) {
            opposite = getOppositeBlock(world, pos, -1, 2);
        } else if (isTestAltarThere(world, pos, -2, 1)) {
            opposite = getOppositeBlock(world, pos, -2, 1);
        }
            //Testing Quadrant III (-X,-Z)
        if (isTestAltarThere(world, pos, -1, -2)) {
            opposite = getOppositeBlock(world, pos, -1, -2);
        } else if (isTestAltarThere(world, pos, -2, -1)) {
            opposite = getOppositeBlock(world, pos, -2, -1);
        }
            //Testing Quadrant III (+X,-Z)
        if (isTestAltarThere(world, pos, 1, -2)) {
            opposite = getOppositeBlock(world, pos, 1, -2);
        } else if (isTestAltarThere(world, pos, 2, -1)) {
            opposite = getOppositeBlock(world, pos, 2, -1);
        }

        return opposite;
    }

    public static boolean isTestAltarThere(World world, BlockPos pos, int xOffset, int zOffset) {
        if (world.getBlockEntity(new BlockPos(pos.getX() + xOffset, pos.getY(), pos.getZ() + zOffset)) != null) {
            if (world.getBlockEntity(new BlockPos(pos.getX() + xOffset, pos.getY(), pos.getZ() + zOffset)).getType() == ModBlockEntities.TEST_ALTAR) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVertical(BlockPos pos1, BlockPos pos2) {
        return pos1.getZ() == pos2.getZ() - 2 || pos1.getZ() == pos2.getZ() + 2;
    }

    public static int getQuadrant(BlockPos origin, BlockPos opposite) {
        if (opposite.getX() > origin.getX()) {
            if (opposite.getZ() > origin.getZ()) {
                return 1;
            } else {
                return 4;
            }
        } else {
            if (opposite.getZ() > origin.getZ()) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}
