package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class KeyedPressurePlateBlock extends PressurePlateBlock {
    private final ActivationRule type;

    public KeyedPressurePlateBlock(ActivationRule type, Settings settings) {
        super(type, settings);
        this.type = type;
    }

    @Override
    protected int getRedstoneOutput(World world, BlockPos pos) {
        Box box = BOX.offset(pos);
        List list;
        switch (this.type) {
            case EVERYTHING:
                list = world.getOtherEntities((Entity)null, box);
                break;
            case MOBS:
                list = world.getNonSpectatingEntities(LivingEntity.class, box);
                break;
            default:
                return 0;
        }

        if (!list.isEmpty()) {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Entity entity = (Entity)var5.next();

                if (entity.isPlayer()) {
                    PlayerEntity player = (PlayerEntity) entity;
                    ItemStack key = null;
                    ItemStack mainHand = player.getMainHandStack().copy();
                    ItemStack offHand = player.getOffHandStack().copy();

                    if (mainHand.isOf(ModItems.KEY)) {
                        key = mainHand;
                    } else if (offHand.isOf(ModItems.KEY)) {
                        key = offHand;
                    }

                    if (key != null) {
                        NbtCompound nbt = key.getOrCreateNbt();

                        if (nbt != null) {
                            BlockPos testPos = new BlockPos(nbt.getDouble("witcheryrestitched:keyX"),
                                    nbt.getDouble("witcheryrestitched:keyY"), nbt.getDouble("witcheryrestitched:keyZ"));

                            if (pos.equals(testPos)) {
                                if (!entity.canAvoidTraps()) {
                                    return 15;
                                }
                            }
                        }
                    }
                } else if (entity.isLiving()) {
                    LivingEntity mob = (LivingEntity) entity;
                    ItemStack key = mob.getActiveItem();

                    if (key.isOf(ModItems.KEY)) {
                        NbtCompound nbt = key.getOrCreateNbt();

                        if (nbt != null) {
                            BlockPos testPos = new BlockPos(nbt.getDouble("witcheryrestitched:keyX"),
                                    nbt.getDouble("witcheryrestitched:keyY"), nbt.getDouble("witcheryrestitched:keyZ"));

                            if (pos.equals(testPos)) {
                                if (!entity.canAvoidTraps()) {
                                    return 15;
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        ItemStack key = new ItemStack(ModItems.KEY, 1);
        NbtCompound nbt = key.getOrCreateNbt();
        nbt.putDouble("witcheryrestitched:keyX", pos.getX());
        nbt.putDouble("witcheryrestitched:keyY", pos.getY());
        nbt.putDouble("witcheryrestitched:keyZ", pos.getZ());

        if (placer.isPlayer()) {
            PlayerEntity player = (PlayerEntity) placer;

            player.giveItemStack(key);
        } else {
            if (placer.getActiveItem().isEmpty()) {
                placer.setStackInHand(placer.getActiveHand(), key);
            } else {
                placer.dropStack(key);
            }

        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
