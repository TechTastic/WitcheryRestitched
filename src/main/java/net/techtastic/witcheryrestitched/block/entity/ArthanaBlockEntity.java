package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ArthanaBlockEntity extends BlockEntity {

    private NbtList enchantments = new NbtList();
    private int damage = 251;
    private String name = "";
    private NbtCompound itemNbt = null;

    private List<Text> tooltip;

    public ArthanaBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ARTHANA, pos, state);
    }

    //SETTERS AND GETTERS

    public ItemStack getItemStack() {
        ItemStack stack = new ItemStack(ModItems.ARTHANA, 1);
        if (this.itemNbt != null) {
            stack.setNbt(this.itemNbt);
        }
        ItemStack.appendEnchantments(this.tooltip, this.enchantments);

        if (!this.name.equals(ModItems.ARTHANA.getName().getString())) {
            stack.setCustomName(Text.of(this.name));
        }
        stack.setDamage(this.damage);

        return stack;
    }

    public void setEnchantments(NbtList enchantments) {
        this.enchantments = enchantments;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public void setItemNbt(NbtCompound nbt) {
        this.itemNbt = nbt;
    }

    public void setTooltip(List<Text> tooltip) {
        this.tooltip = tooltip;
    }

    //NETWORKING NBT

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        boolean foundAllEnchants = false;
        int count = 0;
        while (!foundAllEnchants) {
            String check = "arthanaenchantment" + count;
            if (nbt.contains(check)) {
                this.enchantments.add(count, nbt.get(check));
                nbt.remove(check);
            } else {
                foundAllEnchants = true;
            }
        }

        this.damage = nbt.getInt("arthanadamage");
        nbt.remove("arthanadamage");

        this.name = nbt.getString("arthananame");
        nbt.remove("arthananame");

        this.itemNbt = nbt.copy();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.copyFrom(this.itemNbt);
        for (int i = 0; i < this.enchantments.size(); i++) {
            nbt.put("arthanaenchantment" + i ,this.enchantments.get(i));
        }
        nbt.putInt("arthanadamage", this.damage);
        nbt.putString("arthananame", this.name);

        super.writeNbt(nbt);
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
