package net.techtastic.witcheryrestitched.item.custom;

import net.fabricmc.fabric.impl.registry.sync.trackers.vanilla.BlockItemTracker;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class TaglockItem extends Item {
    public TaglockItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        if (context.getWorld().getBlockEntity(context.getBlockPos()).getClass() == BedBlockEntity.class) {
            UUID uuid = context.getPlayer().getUuid();
            String displayName = context.getPlayer().getName().getString();
            PlayerEntity user = context.getPlayer();
            ItemStack stack = context.getStack();

            taglockEntity(uuid, displayName, user, stack);
        }

        /*
            Get Taglock FROM Blocks to Add:
                Leech Chest
                Thorny Rose

            Apply Taglock TO Block to Add:
                Effigy
                Statue of the Hobgoblin
                Crystal Ball
         */

        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        Text name = Text.empty();
        if (user.getHeadYaw() < (entity.getBodyYaw() + 0.1) || user.getHeadYaw() > (entity.getBodyYaw() - 0.1)) {
            if (entity.isPlayer()) {
                name = entity.getDisplayName();
            } else {
                name = entity.getDisplayName();
            }
            UUID uuid = entity.getUuid();
            String displayName = name.getString();
            taglockEntity(uuid, displayName, user, stack);
        } else {
            entity.sendMessage(Text.of("Someone is attempting to taglock you!!!"));
            user.sendMessage(Text.of("You were noticed while trying to taglock them!!!"));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            String target = stack.getNbt().getString("witcheryrestitched:targetName");
            if (target == "") {
                target = "None";
            }
            tooltip.add(Text.of(target).copy().formatted(Formatting.DARK_PURPLE));
        }
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        if (!stack.hasNbt()) {
            NbtCompound nbtData = new NbtCompound();
            nbtData.putInt("witcheryresttiched:isfull", 0);
            stack.setNbt(nbtData);
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!stack.hasNbt()) {
            NbtCompound nbtData = new NbtCompound();
            nbtData.putInt("witcheryresttiched:isfull", 0);
            stack.setNbt(nbtData);
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (!stack.hasNbt()) {
            NbtCompound nbtData = new NbtCompound();
            nbtData.putInt("witcheryresttiched:isfull", 0);
            stack.setNbt(nbtData);
        }
        return super.onStackClicked(stack, slot, clickType, player);
    }

    private static void taglockEntity(UUID uuid, String displayName, PlayerEntity user, ItemStack stack) {
        stack.decrement(1);
        ItemStack taglock = new ItemStack(ModItems.TAGLOCK, 1);
        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("witcheryrestitched:targetName", displayName);
        nbtData.putUuid("witcheryrestitched:targetUuid", uuid);
        nbtData.putInt("witcheryresttiched:isfull", 1);
        taglock.setNbt(nbtData);
        user.giveItemStack(taglock);
    }
}