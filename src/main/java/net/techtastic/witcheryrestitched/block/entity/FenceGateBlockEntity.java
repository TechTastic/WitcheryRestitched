package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FenceGateBlockEntity extends BlockEntity {

    public FenceGateBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    private UUID gateUUID = UUID.randomUUID();

    public UUID getDoorUUID() {
        return this.gateUUID;
    }

    public void setDoorUuid(UUID uuid) {
        this.gateUUID = uuid;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        gateUUID = nbt.getUuid("witcheryrestitched:doorUuid");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putUuid("witcheryrestitched:doorUuid", this.gateUUID);
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
