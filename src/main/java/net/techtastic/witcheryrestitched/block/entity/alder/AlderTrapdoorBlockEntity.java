package net.techtastic.witcheryrestitched.block.entity.alder;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.TrapdoorBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AlderTrapdoorBlockEntity extends TrapdoorBlockEntity {

    public AlderTrapdoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALDER_TRAPDOOR_BLOCK_ENTITY, pos, state);
    }
}
