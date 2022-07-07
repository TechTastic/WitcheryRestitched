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
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.TestAltarBlockEntity;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.jetbrains.annotations.Nullable;

public class TestAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    public TestAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        TestAltarBlockEntity be = (TestAltarBlockEntity) world.getBlockEntity(pos);
        TestAltarBlockEntity[] altarBlocks = new TestAltarBlockEntity[6];
        Block[] neighbors = new Block[4];

        neighbors[0] = world.getBlockState(pos.north()).getBlock();
        neighbors[1] = world.getBlockState(pos.east()).getBlock();
        neighbors[2] = world.getBlockState(pos.south()).getBlock();
        neighbors[3] = world.getBlockState(pos.west()).getBlock();
        
        int neighborAltarBlocks = 0;
        for (int i = 0; i < 4; i++) {
            if (neighbors[i] == ModBlocks.TEST_ALTAR) {
                neighborAltarBlocks++;
            }
        }
        
        if (neighborAltarBlocks == 2) {
            checkMultiblockFromCorner(world, pos, be, neighbors);
        } else if (neighborAltarBlocks == 3) {
            checkMultiblockFromCenter(world, pos, be, neighbors);
        } else if (neighborAltarBlocks > 3) {
            findNearestMasterBlock(world, pos, be, neighbors); //DOES NOTHING RN
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (state.getBlock() == ModBlocks.TEST_ALTAR) {
            TestAltarBlockEntity be = (TestAltarBlockEntity) world.getBlockEntity(pos);

            if (be.isMultiblock()) {
                if (be.isMasterBlock()) {
                    //FOR MASTER BLOCK FUNCTIONS
                    if (!(world.isClient)) {
                        WitcheryRestitched.LOGGER.info("The max power is " + be.getMaxAltarPower());
                        WitcheryRestitched.LOGGER.info("The max range is " + be.getMaxRange());
                        WitcheryRestitched.LOGGER.info("The max recharge rate is " + be.getRechargeRate());
                    }
                } else {
                    //PASS TO MASTER BLOCK
                    onLandedUpon(world, state, be.getMasterBlockPos(), entity, fallDistance);
                }
            }
        }
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

    public static boolean isBlockAltar(BlockEntity be) {
        if (be != null) {
            if (be.getType() == ModBlockEntities.TEST_ALTAR) {
                return true;
            }
        }
        return false;
    }

    public static void checkMultiblockFromCorner(World world, BlockPos pos, TestAltarBlockEntity be, Block[] neighbors) {
        TestAltarBlockEntity[] altarBlocks = new TestAltarBlockEntity[6];

        altarBlocks[0] = be;

        if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[0] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.north().north()).getBlock() == ModBlocks.TEST_ALTAR && neighbors[1] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.east().north()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.east().north().north()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.north().north());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().north());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().north().north());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.east().north());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[0] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.north().north()).getBlock() == ModBlocks.TEST_ALTAR && neighbors[3] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().north()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().north().north()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.north().north());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().north());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().north().north());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west().north());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[2] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.south().south()).getBlock() == ModBlocks.TEST_ALTAR && neighbors[1] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.east().south()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.east().south().south()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.south().south());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().south());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().south().south());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.east().south());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[2] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.south().south()).getBlock() == ModBlocks.TEST_ALTAR && neighbors[3] == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().south()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().south().south()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.south().south());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().south());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().south().south());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[0] == ModBlocks.TEST_ALTAR && 
                neighbors[1] == ModBlocks.TEST_ALTAR && world.getBlockState(pos.east().north()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.east().east()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.east().east().north()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().north());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().east());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.east().east().north());
            
            altarBlocks[2].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.east());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[2] == ModBlocks.TEST_ALTAR &&
                neighbors[3] == ModBlocks.TEST_ALTAR && world.getBlockState(pos.west().south()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().west()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().west().south()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().south());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().west());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().west().south());

            altarBlocks[2].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west().south());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.TEST_ALTAR && neighbors[2] == ModBlocks.TEST_ALTAR &&
                neighbors[3] == ModBlocks.TEST_ALTAR && world.getBlockState(pos.west().south()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().west()).getBlock() == ModBlocks.TEST_ALTAR &&
                world.getBlockState(pos.west().west().south()).getBlock() == ModBlocks.TEST_ALTAR) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().south());
            altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().west());
            altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(pos.west().west().south());

            altarBlocks[2].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west().south());
            }
        }

        if (altarBlocks[5] != null) {
            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMultiblock(true);
                altarBlocks[i].markDirty();
            }
        }
    }

    public static void checkMultiblockFromCenter(World world, BlockPos pos, TestAltarBlockEntity be, Block[] neighbors) {
        TestAltarBlockEntity[] altarBlocks = new TestAltarBlockEntity[6];

        altarBlocks[0] = be;

        if (neighbors[0] == ModBlocks.TEST_ALTAR && neighbors[2] == ModBlocks.TEST_ALTAR && be.getPos() == pos) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.south());

            if (neighbors[1] == ModBlocks.TEST_ALTAR) {
                altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.east());

                if (world.getBlockState(altarBlocks[3].getPos().north()).getBlock() == ModBlocks.TEST_ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().south()).getBlock() == ModBlocks.TEST_ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().north());
                    altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().south());

                    altarBlocks[3].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(altarBlocks[3].getPos());
                    }
                }
            } else if (neighbors[3] == ModBlocks.TEST_ALTAR) {
                altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.west());

                if (world.getBlockState(altarBlocks[3].getPos().north()).getBlock() == ModBlocks.TEST_ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().south()).getBlock() == ModBlocks.TEST_ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().north());
                    altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().south());

                    altarBlocks[0].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(pos);
                    }
                }
            }
        } else if (neighbors[1] == ModBlocks.TEST_ALTAR && neighbors[3] == ModBlocks.TEST_ALTAR && be.getPos() == pos) {
            altarBlocks[1] = (TestAltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[2] = (TestAltarBlockEntity) world.getBlockEntity(pos.west());

            if (neighbors[0] == ModBlocks.TEST_ALTAR) {
                altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.north());

                if (world.getBlockState(altarBlocks[3].getPos().east()).getBlock() == ModBlocks.TEST_ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().west()).getBlock() == ModBlocks.TEST_ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().east());
                    altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().west());

                    altarBlocks[0].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(pos);
                    }
                }
            } else if (neighbors[2] == ModBlocks.TEST_ALTAR) {
                altarBlocks[3] = (TestAltarBlockEntity) world.getBlockEntity(pos.south());

                if (world.getBlockState(altarBlocks[3].getPos().east()).getBlock() == ModBlocks.TEST_ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().west()).getBlock() == ModBlocks.TEST_ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().east());
                    altarBlocks[5] = (TestAltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().west());

                    altarBlocks[3].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(altarBlocks[3].getPos());
                    }
                }
            }
        }

        if (altarBlocks[5] != null) {
            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMultiblock(true);
                altarBlocks[i].markDirty();
            }
        }
    }

    public static void findNearestMasterBlock(World world, BlockPos pos, TestAltarBlockEntity be, Block[] neighbors) {
        //WILL FILL LATER
    }
}
