package net.techtastic.witcheryrestitched.block.entity.rowan;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.entity.ButtonBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class RowanButtonBlockEntity extends ButtonBlockEntity {

    public RowanButtonBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ROWAN_BUTTON_BLOCK_ENTITY, pos, state);
    }
}
