package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.custom.AltarBlock;
import org.jetbrains.annotations.Nullable;

import static net.techtastic.witcheryrestitched.block.custom.AltarBlock.MULTIBLOCK;

public class AltarBlockEntity extends BlockEntity {

    // MULTIBLOCK VARIABLES
    private boolean isMultiblock = false;
    private boolean isMasterBlock = false;
    private BlockPos masterBlockPos = this.pos;
    
    private BlockBox structure = null;

    //ALTAR POWER VARIABLES
    private int maxAltarPower = 0;
    private int basePowerIncrement = 10;
    private int currentAltarPower = 0;
    private int rate = 1;
    private int range = 16;

    //TICKER

    private int ticks = 1;

    public AltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR, pos, state);
    }

    // ALTAR POWER GETTERS AND SETTERS

    public int getMaxAltarPower() {
        return this.maxAltarPower;
    }

    private void setMaxAltarPower(int x) {
        this.maxAltarPower = x;
    }

    public int getBasePowerIncrement() {
        return this.basePowerIncrement;
    }

    public int getCurrentAltarPower() {
        return this.currentAltarPower;
    }

    private void setCurrentAltarPower(int x) {
        this.currentAltarPower = x;
    }

    public int getAltarRate() {
        return this.rate;
    }

    private void setAltarRate(int x) {
        this.rate = x;
    }

    public int getAltarRange() {
        return this.range;
    }

    private void setAltarRange(int x) {
        this.range = x;
    }

    // MULTIBLOCK GETTERS AND SETTERS

    public boolean isMultiblock() {
        /*if (!this.isMultiblock) {
            WitcheryRestitched.LOGGER.info("THIS WAS NOT A MULTIBLOCK!");
        }*/
        return this.isMultiblock;
    }
    public void setMultiblock(boolean bool) {
        this.isMultiblock = bool;
    }

    public boolean isMasterBlock() {
        return this.isMasterBlock;
    }
    public void setMasterBlock(boolean bool) {
        this.isMasterBlock = bool;
    }

    public BlockPos getMasterBlockPos() {
        return this.masterBlockPos;
    }
    public void setMasterBlockPos(BlockPos pos) {
        this.masterBlockPos = pos;
    }

    public BlockBox getStructure() {
        return this.structure;
    }

    public void setStructure(BlockBox structure) {
        this.structure = structure;
    }

    // TICKER

    private int getTicks() {
        return this.ticks;
    }

    private void resetTicks() {
        this.ticks = 1;
    }

    private void incrementTicks() {
        this.ticks++;
    }

    // TICK UPDATE METHODS

    public void validateMultiblock(World world, BlockPos pos, BlockBox multiblock) {
        int altarCount = 0;
        BlockPos.Mutable testPos = new BlockPos.Mutable();
        for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
            for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                if (world.getBlockState(testPos.set(x, pos.getY(), z)).getBlock() == ModBlocks.ALTAR) {
                    altarCount++;
                }
            }
        }

        if (altarCount == 6) {
            for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
                for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                    AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(testPos.set(x, pos.getY(), z));
                    altar.setMultiblock(true);
                    altar.setMasterBlockPos(multiblock.getCenter());
                    if (testPos == multiblock.getCenter()) {
                        altar.setMasterBlock(true);
                    }

                    altar.setStructure(multiblock);

                    altar.markDirty();

                    world.setBlockState(testPos, world.getBlockState(testPos).with(MULTIBLOCK, true));
                }
            }
        } else {
            for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
                for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                    if (world.getBlockState(testPos.set(x, pos.getY(), z)).getBlock() == ModBlocks.ALTAR) {
                        AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(testPos.set(x, pos.getY(), z));
                        altar.setMultiblock(false);
                        altar.setMasterBlockPos(testPos);
                        altar.setMasterBlock(false);

                        altar.setStructure(null);

                        altar.markDirty();

                        world.setBlockState(testPos, world.getBlockState(testPos).with(MULTIBLOCK, false));
                    }
                }
            }
        }
    }

    public BlockBox getPossibleMultiblock(World world, BlockPos pos) {
        BlockBox multiblock = null;
        
        Block[] neighbors = new Block[4];
        neighbors[0] = world.getBlockState(pos.north()).getBlock();
        neighbors[1] = world.getBlockState(pos.east()).getBlock();
        neighbors[2] = world.getBlockState(pos.south()).getBlock();
        neighbors[3] = world.getBlockState(pos.west()).getBlock();
        
        int altarCount = 0;
        for (int i = 0; i < 4; i++) {
            if (neighbors[i] == ModBlocks.ALTAR) {
                altarCount++;
            }
        }
        
        if (altarCount == 2) {
            if (neighbors[0] == ModBlocks.ALTAR && neighbors[1] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE SOUTHWEST
                
                if (world.getBlockState(pos.north().north()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL
                    
                    multiblock = BlockBox.create(pos, pos.north().north().east());
                } else if (world.getBlockState(pos.east().east()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.east().east().north());
                }
            } else if (neighbors[0] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE SOUTHEAST

                if (world.getBlockState(pos.north().north()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL

                    multiblock = BlockBox.create(pos, pos.north().north().west());
                } else if (world.getBlockState(pos.west().west()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.west().west().north());
                }
            } else if (neighbors[2] == ModBlocks.ALTAR && neighbors[1] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE NORTHWEST

                if (world.getBlockState(pos.south().south()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL

                    multiblock = BlockBox.create(pos, pos.south().south().east());
                } else if (world.getBlockState(pos.east().east()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.east().east().south());
                }
            } else if (neighbors[2] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE NORTHEAST

                if (world.getBlockState(pos.south().south()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL

                    multiblock = BlockBox.create(pos, pos.south().south().west());
                } else if (world.getBlockState(pos.west().west()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.west().west().south());
                }
            }
        } else if (altarCount == 3) {
            if (neighbors[0] == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR) {
                //THIS IS A CENTER BLOCK IN A VERTICAL MULTIBLOCK
                
                if (neighbors[1] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING EAST
                    
                    multiblock = BlockBox.create(pos.south(), pos.north().east());
                } else if (neighbors[3] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING WEST

                    multiblock = BlockBox.create(pos.south(), pos.north().west());
                }
            } else if (neighbors[1] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR) {
                //THIS IS A CENTER BLOCK IN A HORIZONAL MULTIBLOCK

                if (neighbors[0] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING NORTH

                    multiblock = BlockBox.create(pos.east(), pos.west().north());
                } else if (neighbors[2] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING SOUTH

                    multiblock = BlockBox.create(pos.east(), pos.west().south());
                }
            }
        }
        
        return multiblock;
    }

    public void updateCurrentAltarPower() {
        int currentAltarPower = this.getCurrentAltarPower();
        int maxAltarPower = this.getMaxAltarPower();
        int basePowerIncrement = this.getBasePowerIncrement();
        int altarRate = this.getAltarRate();

        if (currentAltarPower != maxAltarPower) {
            if (currentAltarPower >= maxAltarPower - currentAltarPower) {
                //WitcheryRestitched.LOGGER.info(maxAltarPower + ": New Current Altar Power");
                this.setCurrentAltarPower(maxAltarPower);
            } else {
                //WitcheryRestitched.LOGGER.info(String.valueOf(currentAltarPower + basePowerIncrement * altarRate) + ": New Current Altar Power");
                this.setCurrentAltarPower(currentAltarPower + (basePowerIncrement * altarRate));
            }
        }
    }

    /*public void updateMaxAltarPower(World world, BlockPos pos) {
        //WitcheryRestitched.LOGGER.info("Updating Max Altar Power...");

        //GET STUFF ON TOP OF ALTAR
        int maxAltarPower = 0;
        int rate = 1;
        int range = 16;

        for (int i = 0; i < 6; i++) {
            if (onTop[i] == Blocks.DIAMOND_BLOCK) {
                WitcheryRestitched.LOGGER.info("THERE'S A DIAMOND BLOCK!");
                maxAltarPower += 1000;
            } else if (onTop[i] == Blocks.TORCH) {
                WitcheryRestitched.LOGGER.info("THERE'S A TORCH!");
                rate += 1;
            } else if (onTop[i] == Blocks.CHAIN) {
                WitcheryRestitched.LOGGER.info("THERE'S A CHAIN!");
                range = 32;
            }
        }

        //GET NEARBY NATURE


        //REAPPLY VARIABLES
        this.setMaxAltarPower(maxAltarPower);
        this.setAltarRate(rate);
        this.setAltarRange(range);
    }*/

    public static void tick(World world1, BlockPos pos, BlockState state1, AltarBlockEntity be) {
        if (be.isMultiblock() && be.isMasterBlock() && true) {
            
            if (be.getTicks() % 10 == 0) {
                BlockBox potentialMultiblock;
                if (be.getStructure() != null) {
                    potentialMultiblock = be.getStructure();
                } else {
                    potentialMultiblock = be.getPossibleMultiblock(world1, pos);
                }
                if (potentialMultiblock != null) {
                    be.validateMultiblock(world1, pos, potentialMultiblock);
                }
            }

            //CHANGE CURRENT ALTAR POWER EVERY 20 TICKS
            if (be.getTicks() % 20 == 0) {

                //CHANGE CURRENT ALTAR POWER
                be.updateCurrentAltarPower();
            }

            //GET ORIENTATION AND CHECK FOR NATURE EVERY 100 TICKS
            if (be.getTicks() % 50 == 0) {

                // SET MAX ALTAR POWER BASED ON NATURE
                //be.updateMaxAltarPower(world1, pos);
                be.markDirty();

                //CHANGE CURRENT POWER IF OVER NEW MAX
                be.updateCurrentAltarPower();

                be.resetTicks();
            }
        }
        be.incrementTicks();
    }

    // NBT DATA

    @Override
    protected void writeNbt(NbtCompound nbt) {

        // MULTIBLOCK NBT DATA

        nbt.putDouble("witcheryrestitched:masterBlockX", this.masterBlockPos.getX());
        nbt.putDouble("witcheryrestitched:masterBlockY", this.masterBlockPos.getY());
        nbt.putDouble("witcheryrestitched:masterBlockZ", this.masterBlockPos.getZ());
        nbt.putBoolean("witcheryrestitched:ismultiblock", this.isMultiblock);
        nbt.putBoolean("witcheryrestitched:ismasterblock", this.isMasterBlock);

        int[] structureArray = new int[6];
        if (this.structure != null) {
            structureArray[0] = this.structure.getMinX();
            structureArray[1] = this.structure.getMinY();
            structureArray[2] = this.structure.getMinZ();
            structureArray[3] = this.structure.getMaxX();
            structureArray[4] = this.structure.getMaxY();
            structureArray[5] = this.structure.getMaxZ();
        } else {
            for (int i = 0; i < 6; i++) {
                structureArray[i] = 0;
            }
        }
        nbt.putIntArray("witcheryrestitched:structure", structureArray);

        //ALTAR POWER NBT DATA

        nbt.putInt("witcheryrestitched:maxaltarpower", this.maxAltarPower);
        nbt.putInt("witcheryrestitched:basepowerincrement", this.basePowerIncrement);
        nbt.putInt("witcheryrestitched:currentaltarpower", this.currentAltarPower);
        nbt.putInt("witcheryrestitched:rate", this.rate);
        nbt.putInt("witcheryrestitched:range", this.range);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        // MULTIBLOCK NBT DATA

        this.masterBlockPos = new BlockPos(nbt.getDouble("witcheryrestitched:masterBlockX"), nbt.getDouble("witcheryrestitched:masterBlock&"),
                nbt.getDouble("witcheryrestitched:masterBlockZ"));
        this.isMultiblock = nbt.getBoolean("witcheryrestitched:ismultiblock");
        this.isMasterBlock = nbt.getBoolean("witcheryrestitched:ismultiblock");

        int[] structureArray = nbt.getIntArray("witcheryrestitched:structure");
        int[] test = new int[6];
        for (int i = 0; i < 6; i++) {
            test[i] = 0;
        }
        if (structureArray != test) {
            this.structure = new BlockBox(structureArray[0], structureArray[1], structureArray[2], structureArray[3], structureArray[4], structureArray[5]);
        } else {
            this.structure = null;
        }



        //ALTAR POWER NBT DATA

        this.maxAltarPower = nbt.getInt("witcheryrestitched:maxaltarpower");
        this.basePowerIncrement = nbt.getInt("witcheryrestitched:basepowerincrement");
        this.currentAltarPower = nbt.getInt("witcheryrestitched:currentaltarpower");
        this.rate = nbt.getInt("witcheryrestitched:rate");
        this.range = nbt.getInt("witcheryrestitched:range");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
