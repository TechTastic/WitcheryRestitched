package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.ArthanaBlockEntity;

import static net.techtastic.witcheryrestitched.block.custom.ArthanaBlock.FACING;

public class ArthanaSwordItem extends SwordItem {
    public ArthanaSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction dir = context.getSide();
        Hand hand = context.getHand();

        Block test = world.getBlockState(pos).getBlock();
        if (test.equals(ModBlocks.ALTAR)) {
            if (dir.equals(Direction.UP)) {
                if (player.isSneaking()) {
                    NbtList enchantments = new NbtList();
                    int damage = player.getStackInHand(hand).getDamage();
                    String name = player.getStackInHand(hand).getName().getString();
                    NbtCompound nbt = player.getStackInHand(hand).getNbt().copy();

                    if (player.getStackInHand(hand).hasEnchantments()) {
                        enchantments = player.getStackInHand(hand).getEnchantments();
                    }
                    if (name.equals("Arthana")) {
                        name = "";
                    }

                    player.setStackInHand(hand, ItemStack.EMPTY);

                    world.setBlockState(pos.up(), ModBlocks.ARTHANA.getDefaultState().with(FACING, player.getHorizontalFacing().getOpposite()));

                    ArthanaBlockEntity arthana = (ArthanaBlockEntity) world.getBlockEntity(pos.up());
                    arthana.setEnchantments(enchantments);
                    arthana.setDamage(damage);
                    arthana.setItemName(name);
                    arthana.setItemNbt(nbt);

                    arthana.markDirty();

                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.FAIL;
    }
}
