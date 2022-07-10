package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class AltarBlockEntity extends BlockEntity {

    // MULTIBLOCK VARIABLES
    private boolean isMultiblock = false;
    private boolean isMasterBlock = false;
    private BlockPos masterBlockPos = this.pos;

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
    public AltarBlockEntity[] checkMultiblock(World world, BlockPos pos) {
        //WitcheryRestitched.LOGGER.info("Checking Multiblock...");

        Block[] neighbors = new Block[4];

        neighbors[0] = world.getBlockState(pos.north()).getBlock();
        neighbors[1] = world.getBlockState(pos.east()).getBlock();
        neighbors[2] = world.getBlockState(pos.south()).getBlock();
        neighbors[3] = world.getBlockState(pos.west()).getBlock();

        AltarBlockEntity[] altarBlocks = new AltarBlockEntity[6];

        altarBlocks[0] = (AltarBlockEntity) world.getBlockEntity(pos);

        if (neighbors[0] == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR && true) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.north());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.south());

            if (neighbors[1] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.east());

                if (world.getBlockState(altarBlocks[3].getPos().north()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().south()).getBlock() == ModBlocks.ALTAR && true) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().north());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().south());
                }
            } else if (neighbors[3] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.west());

                if (world.getBlockState(altarBlocks[3].getPos().north()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().south()).getBlock() == ModBlocks.ALTAR && true) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().north());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().south());
                }
            }
        } else if (neighbors[1] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR && true) {
            altarBlocks[1] = (AltarBlockEntity) world.getBlockEntity(pos.east());
            altarBlocks[2] = (AltarBlockEntity) world.getBlockEntity(pos.west());

            if (neighbors[0] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.north());

                if (world.getBlockState(altarBlocks[3].getPos().east()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().west()).getBlock() == ModBlocks.ALTAR && true) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().east());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().west());
                }
            } else if (neighbors[2] == ModBlocks.ALTAR) {
                altarBlocks[3] = (AltarBlockEntity) world.getBlockEntity(pos.south());

                if (world.getBlockState(altarBlocks[3].getPos().east()).getBlock() == ModBlocks.ALTAR &&
                        world.getBlockState(altarBlocks[3].getPos().west()).getBlock() == ModBlocks.ALTAR && this.getPos() == pos) {
                    altarBlocks[4] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().east());
                    altarBlocks[5] = (AltarBlockEntity) world.getBlockEntity(altarBlocks[3].getPos().west());
                }
            }
        }

        if (altarBlocks[5] == null) {
            for (int i = 0; i < 6; i++) {
                if (altarBlocks[i] != null) {
                    altarBlocks[i].setMultiblock(false);
                    altarBlocks[i].setMasterBlock(false);
                    altarBlocks[i].setMasterBlockPos(altarBlocks[i].getPos());

                    altarBlocks[i].markDirty();
                }
                altarBlocks[i] = null;
            }
        }

        return altarBlocks;
    }

    public void updateCurrentAltarPower() {
        int currentAltarPower = this.getCurrentAltarPower();
        int maxAltarPower = this.getMaxAltarPower();
        int basePowerIncrement = this.getBasePowerIncrement();
        int altarRate = this.getAltarRate();

        if (currentAltarPower != maxAltarPower) {
            if (currentAltarPower >= maxAltarPower - currentAltarPower) {
                WitcheryRestitched.LOGGER.info(maxAltarPower + ": New Current Altar Power");
                this.setCurrentAltarPower(maxAltarPower);
            } else {
                WitcheryRestitched.LOGGER.info(String.valueOf(currentAltarPower + basePowerIncrement * altarRate) + ": New Current Altar Power");
                this.setCurrentAltarPower(currentAltarPower + (basePowerIncrement * altarRate));
            }
        }
    }

    public void updateMaxAltarPower(World world, BlockPos pos, AltarBlockEntity[] altarBlocks) {
        WitcheryRestitched.LOGGER.info("Updating Max Altar Power...");

        //GET STUFF ON TOP OF ALTAR
        int maxAltarPower = 0;
        int rate = 1;
        int range = 16;

        Block[] onTop = new Block[6];
        onTop[0] = world.getBlockState(altarBlocks[0].getPos().up()).getBlock();
        onTop[1] = world.getBlockState(altarBlocks[1].getPos().up()).getBlock();
        onTop[2] = world.getBlockState(altarBlocks[2].getPos().up()).getBlock();
        onTop[3] = world.getBlockState(altarBlocks[3].getPos().up()).getBlock();
        onTop[4] = world.getBlockState(altarBlocks[4].getPos().up()).getBlock();
        onTop[5] = world.getBlockState(altarBlocks[5].getPos().up()).getBlock();

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
    }

    public static void tick(World world1, BlockPos pos, BlockState state1, AltarBlockEntity be) {
        if (be.isMultiblock() && be.isMasterBlock() && true) {

            //CHECK STRUCTURE EVERY 5 TICKS
            if (be.getTicks() % 5 == 0) {

                //CHECK STRUCTURE
                AltarBlockEntity[] altarBlocks = be.checkMultiblock(world1, pos);

                //CHANGE CURRENT ALTAR POWER EVERY 20 TICKS
                if (be.getTicks() % 20 == 0) {

                    //CHANGE CURRENT ALTAR POWER
                    be.updateCurrentAltarPower();
                }

                //GET ORIENTATION AND CHECK FOR NATURE EVERY 100 TICKS
                if (be.getTicks() % 50 == 0) {
                    // SET MAX ALTAR POWER BASED ON NATURE
                    be.updateMaxAltarPower(world1, pos, altarBlocks);
                    be.markDirty();

                    //CHANGE CURRENT POWER IF OVER NEW MAX
                    be.updateCurrentAltarPower();

                    be.resetTicks();
                }
            }

            be.incrementTicks();
            be.markDirty();
        }
        be.markDirty();
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
