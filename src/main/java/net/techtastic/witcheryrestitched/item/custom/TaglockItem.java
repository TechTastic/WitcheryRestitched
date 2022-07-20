package net.techtastic.witcheryrestitched.item.custom;

import com.eliotlash.mclib.math.functions.classic.Mod;
import net.fabricmc.fabric.impl.registry.sync.trackers.vanilla.BlockItemTracker;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.util.ModdedBedBlockInterface;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TaglockItem extends Item {
	public TaglockItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		BlockEntity bed = context.getWorld().getBlockEntity(context.getBlockPos());
		if (!(bed == null) && bed.getType().equals(BlockEntityType.BED) && true) {
			ModdedBedBlockInterface Ibed = (ModdedBedBlockInterface) bed;

			if (Ibed.WitcheryRestitched$wasUsed()) {
				taglockEntity(Ibed.WitcheryRestitched$getUserUuid(), Ibed.WitcheryRestitched$getUserName(), context.getPlayer(), context.getStack().copy());
			}

			return ActionResult.PASS;
		} else {
			return super.useOnBlock(context);
		}
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		if ((user.getHeadYaw() < (entity.getBodyYaw() + 0.01) || user.getHeadYaw() > (entity.getBodyYaw() - 0.01)) && user.getHorizontalFacing().equals(entity.getHorizontalFacing())) {
			UUID uuid = entity.getUuid();
			String displayName = entity.getDisplayName().getString();

			taglockEntity(uuid, displayName, user, stack);
		} else {
			entity.sendMessage(Text.of("Someone is attempting to taglock you!!!"));
			user.sendMessage(Text.of("You were noticed while trying to taglock them!!!"));
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		String target = null;
		NbtCompound nbt = stack.getOrCreateNbt();
		
		if (nbt.contains("witcheryrestitched:targetName")) {
			target = nbt.getString("witcheryrestitched:targetName");
		}
		if (target == null) {
			target = "None";
		}
		tooltip.add(Text.of(target).copy().formatted(Formatting.DARK_PURPLE));
	}

	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) {
		NbtCompound nbt = stack.getOrCreateNbt();
		if (!nbt.contains("witcheryrestitched:isfull")) {
			nbt.putInt("witcheryrestitched:isfull", 0);
		}
	}

	@Override
	public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
		NbtCompound nbt = stack.getOrCreateNbt();
		if (!nbt.contains("witcheryrestitched:isfull")) {
			nbt.putInt("witcheryrestitched:isfull", 0);
		}
		
		return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
	}

	@Override
	public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
		NbtCompound nbt = stack.getOrCreateNbt();
		if (!nbt.contains("witcheryrestitched:isfull")) {
			nbt.putInt("witcheryrestitched:isfull", 0);
		}
		
		return super.onStackClicked(stack, slot, clickType, player);
	}

	private static void taglockEntity(UUID uuid, String displayName, PlayerEntity user, ItemStack stack) {
		NbtCompound nbtData = stack.getOrCreateNbt();

		user.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0f, 5.0f);

		if (stack.getCount() > 1) {
			stack.decrement(1);
			ItemStack taglock = new ItemStack(ModItems.TAGLOCK, 1);

			nbtData.putString("witcheryrestitched:targetName", displayName);
			nbtData.putUuid("witcheryrestitched:targetUuid", uuid);
			nbtData.putInt("witcheryrestitched:isfull", 1);

			taglock.setNbt(nbtData);
			
			user.giveItemStack(taglock);
		} else {
			user.setStackInHand(user.getActiveHand(), ItemStack.EMPTY);

			ItemStack taglock = new ItemStack(ModItems.TAGLOCK, 1);

			nbtData.putString("witcheryrestitched:targetName", displayName);
			nbtData.putUuid("witcheryrestitched:targetUuid", uuid);
			nbtData.putInt("witcheryrestitched:isfull", 1);

			taglock.setNbt(nbtData);
			user.giveItemStack(taglock);
		}
	}
}
