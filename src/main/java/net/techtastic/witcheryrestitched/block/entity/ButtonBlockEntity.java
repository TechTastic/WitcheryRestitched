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

public class ButtonBlockEntity extends BlockEntity {

    public ButtonBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    private UUID buttonUUID = UUID.randomUUID();

    public UUID getButtonUUID() {
        return this.buttonUUID;
    }

    public void setButtonUuid(UUID uuid) {
        this.buttonUUID = uuid;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        buttonUUID = nbt.getUuid("witcheryrestitched:doorUuid");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putUuid("witcheryrestitched:doorUuid", this.buttonUUID);
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
