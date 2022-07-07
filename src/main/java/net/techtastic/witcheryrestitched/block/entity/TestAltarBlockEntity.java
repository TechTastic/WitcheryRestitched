package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TestAltarBlockEntity extends BlockEntity {

    public int maxAltarPower = 0;
    public int maxRange = 16;
    public int rechargeRate = 1;
    public boolean isMultiblock = false;
    public boolean isMasterBlock = false;
    public BlockPos masterBlockPos = this.pos;

    public TestAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEST_ALTAR, pos, state);
    }

    public static void tick(World world1, BlockPos pos, BlockState state1, TestAltarBlockEntity be) {
        BlockPos onTop = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        if (world1.getBlockState(onTop).getBlock() == Blocks.DIAMOND_BLOCK) {
            be.setMaxAltarPower(1000);
            be.markDirty();
        }
    }

    //Multiblock Getters and Setters

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

    //Getters and Setters

    public int getMaxAltarPower() {
        return this.maxAltarPower;
    }
    public void setMaxAltarPower(int x) {
        this.maxAltarPower = x;
    }

    public int getMaxRange() {
        return this.maxRange;
    }

    public int getRechargeRate() {
        return this.rechargeRate;
    }

    // NBT Data

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("witcheryrestitched:maxaltarpower", this.maxAltarPower);
        nbt.putInt("witcheryrestitched:maxrange", this.maxRange);
        nbt.putInt("witcheryrestitched:rechargerate", this.rechargeRate);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        this.maxAltarPower = nbt.getInt("witcheryrestitched:maxaltarpower");
        this.maxRange = nbt.getInt("witcheryrestitched:maxrange");
        this.rechargeRate = nbt.getInt("witcheryrestitched:rechargerate");
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
