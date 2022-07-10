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
import net.techtastic.witcheryrestitched.block.entity.AltarBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BlockWithEntity implements BlockEntityProvider {
    public AltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        AltarBlockEntity be = (AltarBlockEntity) world.getBlockEntity(pos);
        Block[] neighbors = new Block[4];

        neighbors[0] = world.getBlockState(pos.north()).getBlock();
        neighbors[1] = world.getBlockState(pos.east()).getBlock();
        neighbors[2] = world.getBlockState(pos.south()).getBlock();
        neighbors[3] = world.getBlockState(pos.west()).getBlock();

        int neighborAltarBlocks = 0;
        for (int i = 0; i < 4; i++) {
            if (neighbors[i] == ModBlocks.ALTAR) {
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
        AltarBlockEntity be = (AltarBlockEntity) world.getBlockEntity(pos);
        if (be.isMultiblock()) {
            if (be.isMasterBlock()) {
                if (world.isClient()) {
                    entity.sendMessage(Text.of("Max altar power is at " + be.getMaxAltarPower()));
                    entity.sendMessage(Text.of("Current altar power is at " + be.getCurrentAltarPower()));
                    entity.sendMessage(Text.of("Altar rate is at " + be.getAltarRate()));
                    entity.sendMessage(Text.of("Altar range is at " + be.getAltarRange()));
                }
            } else {
                AltarBlock masterBlock = (AltarBlock) world.getBlockState(be.getMasterBlockPos()).getBlock();
                masterBlock.onLandedUpon(world, world.getBlockState(be.getMasterBlockPos()), be.getMasterBlockPos(), entity, fallDistance);
            }
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AltarBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ALTAR, (world1, pos, state1, be) -> AltarBlockEntity.tick(world1, pos, state1, be));
    }

    // MULTIBLOCK CHECKERS

    public static void checkMultiblockFromCorner(World world, BlockPos pos, AltarBlockEntity be, Block[] neighbors) {
        AltarBlockEntity[] altarBlocks = new AltarBlockEntity[6];

        altarBlocks[0] = be;

        if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[0] == ModBlocks.ALTAR &&
                world.getBlockState(pos.north().north()).getBlock() == ModBlocks.ALTAR && neighbors[1] == ModBlocks.ALTAR &&
                world.getBlockState(pos.east().north()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.east().north().north()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.north().north());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.east().north());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.east().north().north());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.east().north());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[0] == ModBlocks.ALTAR &&
                world.getBlockState(pos.north().north()).getBlock() == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().north()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().north().north()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.north().north());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.west().north());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.west().north().north());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west().north());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR &&
                world.getBlockState(pos.south().south()).getBlock() == ModBlocks.ALTAR && neighbors[1] == ModBlocks.ALTAR &&
                world.getBlockState(pos.east().south()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.east().south().south()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.south().south());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.east().south());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.east().south().south());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.east().south());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR &&
                world.getBlockState(pos.south().south()).getBlock() == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().south()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().south().south()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.south().south());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.west().south());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.west().south().south());

            altarBlocks[4].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[0] == ModBlocks.ALTAR &&
                neighbors[1] == ModBlocks.ALTAR && world.getBlockState(pos.east().north()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.east().east()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.east().east().north()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.east().north());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.east().east());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.east().east().north());

            altarBlocks[2].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.east());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR &&
                neighbors[3] == ModBlocks.ALTAR && world.getBlockState(pos.west().south()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().west()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().west().south()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.west().south());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.west().west());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.west().west().south());

            altarBlocks[2].setMasterBlock(true);

            for (int i = 0; i < 6; i++) {
                altarBlocks[i].setMasterBlockPos(pos.west().south());
            }
        } else if (world.getBlockState(pos).getBlock() == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR &&
                neighbors[3] == ModBlocks.ALTAR && world.getBlockState(pos.west().south()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().west()).getBlock() == ModBlocks.ALTAR &&
                world.getBlockState(pos.west().west().south()).getBlock() == ModBlocks.ALTAR) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.south());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.west());
            altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.west().south());
            altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(pos.west().west());
            altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(pos.west().west().south());

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

    public static void checkMultiblockFromCenter(World world, BlockPos pos, AltarBlockEntity be, Block[] neighbors) {
        AltarBlockEntity[] altarBlocks = new AltarBlockEntity[6];

        altarBlocks[0] = be;

        if (neighbors[0] == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR && be.getPos() == pos) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.south());

            if (neighbors[1] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.east());

                if (world.getBlockState(altarBlocks[3].getPos().north()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().south()).getBlock() == ModBlocks.ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().north());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().south());

                    altarBlocks[3].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(altarBlocks[3].getPos());
                    }
                }
            } else if (neighbors[3] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.west());

                if (world.getBlockState(altarBlocks[3].getPos().north()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().south()).getBlock() == ModBlocks.ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().north());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().south());

                    altarBlocks[0].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(pos);
                    }
                }
            }
        } else if (neighbors[1] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR && be.getPos() == pos) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.west());

            if (neighbors[0] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.north());

                if (world.getBlockState(altarBlocks[3].getPos().east()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().west()).getBlock() == ModBlocks.ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().east());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().west());

                    altarBlocks[0].setMasterBlock(true);

                    for (int i = 0; i < 6; i++) {
                        altarBlocks[i].setMasterBlockPos(pos);
                    }
                }
            } else if (neighbors[2] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.south());

                if (world.getBlockState(altarBlocks[3].getPos().east()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().west()).getBlock() == ModBlocks.ALTAR && be.getPos() == pos) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().east());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().west());

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

    public static void findNearestMasterBlock(World world, BlockPos pos, AltarBlockEntity be, Block[] neighbors) {
        //WILL FILL LATER
    }
}
