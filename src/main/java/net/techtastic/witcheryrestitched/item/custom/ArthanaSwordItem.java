package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.ArthanaBlockEntity;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;

import java.util.List;

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
                    ItemStack stack = player.getStackInHand(hand);

                    player.setStackInHand(hand, ItemStack.EMPTY);

                    world.setBlockState(pos.up(), ModBlocks.ARTHANA.getDefaultState().with(FACING, player.getHorizontalFacing().getOpposite()));

                    ArthanaBlockEntity arthana = (ArthanaBlockEntity) world.getBlockEntity(pos.up());

                    arthana.setDamage(stack.getDamage());
                    arthana.setItemName(stack.getName().getString());
                    if (stack.hasNbt()) {
                        arthana.setItemNbt(stack.getNbt().copy());
                    }
                    arthana.setTooltip(stack.getTooltip(player, TooltipContext.Default.NORMAL));

                    NbtList enchantments = new NbtList();
                    if (stack.hasEnchantments()) {
                        enchantments = stack.getEnchantments();
                    }
                    arthana.setEnchantments(enchantments);

                    arthana.markDirty();

                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.FAIL;
    }
}
