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

public class TrapdoorBlockEntity extends BlockEntity {

    public TrapdoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    private UUID trapdoorUUID = UUID.randomUUID();

    public UUID getTrapdoorUUID() {
        return this.trapdoorUUID;
    }

    public void setTrapdoorUuid(UUID uuid) {
        this.trapdoorUUID = uuid;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        trapdoorUUID = nbt.getUuid("witcheryrestitched:trapdoorUuid");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putUuid("witcheryrestitched:trapdoorUuid", this.trapdoorUUID);
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
