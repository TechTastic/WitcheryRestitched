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

public class PlateBlockEntity extends BlockEntity {

    public PlateBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    private UUID plateUUID = UUID.randomUUID();

    public UUID getPlateUUID() {
        return this.plateUUID;
    }

    public void setPlateUuid(UUID uuid) {
        this.plateUUID = uuid;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        plateUUID = nbt.getUuid("witcheryrestitched:plateUuid");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putUuid("witcheryrestitched:plateUuid", this.plateUUID);
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
