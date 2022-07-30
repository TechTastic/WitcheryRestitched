package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class AltarPowerSinkEntity extends BlockEntity {

    private BlockPos nearestAltar = this.pos;

    public AltarPowerSinkEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR_POWER_SINK_ENTITY, pos, state);
    }

    public void setNearestAltar(BlockPos pos) {
        this.nearestAltar = pos;
    }

    public BlockPos getNearestAltar() {
        return this.nearestAltar;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putDouble("witcheryrestitched:altarX", this.nearestAltar.getX());
        nbt.putDouble("witcheryrestitched:altarY", this.nearestAltar.getY());
        nbt.putDouble("witcheryrestitched:altarZ", this.nearestAltar.getZ());

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        double x = nbt.getDouble("witcheryrestitched:altarX");
        double y = nbt.getDouble("witcheryrestitched:altarY");
        double z = nbt.getDouble("witcheryrestitched:altarZ");
        this.nearestAltar = new BlockPos(x, y, z);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
