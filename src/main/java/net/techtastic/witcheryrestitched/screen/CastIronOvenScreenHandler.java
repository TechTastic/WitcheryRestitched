package net.techtastic.witcheryrestitched.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.techtastic.witcheryrestitched.screen.slot.ModFuelSlot;
import net.techtastic.witcheryrestitched.screen.slot.ModInputSlot;
import net.techtastic.witcheryrestitched.screen.slot.ModJarSlot;
import net.techtastic.witcheryrestitched.screen.slot.ModResultSlot;

public class CastIronOvenScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public CastIronOvenScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(5), new ArrayPropertyDelegate(4));
    }

    public CastIronOvenScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.CAST_IRON_OVEN_SCREEN_HANDLER, syncId);
        checkSize(inventory, 5);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;

        //
        this.addSlot(new ModFuelSlot(inventory, 0, 44, 54));
        this.addSlot(new ModInputSlot(inventory, 1, 44, 19));
        this.addSlot(new ModJarSlot(inventory, 2, 80, 54));
        this.addSlot(new ModResultSlot(inventory, 3, 116, 19));
        this.addSlot(new ModResultSlot(inventory, 4, 116, 54));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);
    }

    public  boolean isCrafting() { return propertyDelegate.get(0) > 0; }

    public boolean hasFuel() { return propertyDelegate.get(2) > 0; }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 48;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int fuelProgress = this.propertyDelegate.get(2);
        int maxFuelProgress = this.propertyDelegate.get(3);
        int fuelProgressSize = 14;

        return maxFuelProgress != 0 ? (int)(((float)fuelProgress / (float)maxFuelProgress) * fuelProgressSize) : 0;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }



    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
