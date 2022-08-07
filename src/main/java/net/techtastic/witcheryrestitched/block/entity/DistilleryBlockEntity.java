package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.custom.DistilleryBlock;
import net.techtastic.witcheryrestitched.item.ModItemGroup;
import net.techtastic.witcheryrestitched.item.ModItems;
import net.techtastic.witcheryrestitched.recipe.CastIronOvenRecipe;
import net.techtastic.witcheryrestitched.recipe.DistilleryRecipe;
import net.techtastic.witcheryrestitched.screen.CastIronOvenScreenHandler;
import net.techtastic.witcheryrestitched.screen.DistilleryScreenHandler;
import net.techtastic.witcheryrestitched.util.IAltarPowerSink;
import net.techtastic.witcheryrestitched.util.ImplementedInventory;
import org.apache.logging.log4j.core.selector.NamedContextSelector;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DistilleryBlockEntity extends BlockEntity implements IAltarPowerSink, ImplementedInventory, NamedScreenHandlerFactory {
    private BlockPos cachedAltar = this.pos;

    private int ticks = 0;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int craftProgress = 0;
    private int maxCraftProgress = 50;
    private int hasPower = 1;

    private int powerProgress = 0;

    private int maxPowerProgress = 8;

    public DistilleryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISTILLERY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0: return DistilleryBlockEntity.this.craftProgress;
                    case 1: return DistilleryBlockEntity.this.maxCraftProgress;
                    case 2: return DistilleryBlockEntity.this.hasPower;
                    case 3: return DistilleryBlockEntity.this.powerProgress;
                    case 4: return DistilleryBlockEntity.this.maxPowerProgress;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: DistilleryBlockEntity.this.craftProgress = value; break;
                    case 1: DistilleryBlockEntity.this.maxCraftProgress = value; break;
                    case 2: DistilleryBlockEntity.this.hasPower = value; break;
                    case 3: DistilleryBlockEntity.this.powerProgress = value; break;
                    case 4: DistilleryBlockEntity.this.maxPowerProgress = value; break;
                }
            }

            @Override
            public int size() {
                return 5;
            }
        };
    }

    // GETTERS AND SETTERS

    public BlockPos getCachedAltar() {
        return this.cachedAltar;
    }

    public void setCachedAltar(BlockPos pos) {
        this.cachedAltar = pos;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int x) {
        this.ticks = x;
    }

    // INVENTORIES

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Distillery");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DistilleryScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    // TICKER

    public static void tick(World world, BlockPos pos, BlockState state, DistilleryBlockEntity entity) {
        boolean isUsed = state.get(DistilleryBlock.USED);
        ItemStack jarSlot = entity.inventory.get(2).copy();
        int stateJars = state.get(DistilleryBlock.JARS);

        if (entity.getCachedAltar().equals(pos)) {
            BlockPos newAltar = entity.locateNearestAltar(world, pos);
            if (newAltar != null && entity.isWithinAltarRange(world, pos, (AltarBlockEntity) world.getBlockEntity(newAltar))) {
                entity.setCachedAltar(newAltar);
            }
        }

        if (!(world.getBlockEntity(entity.getCachedAltar()) instanceof AltarBlockEntity) && entity.getTicks() % 80 == 0) {
            BlockPos newAltar = entity.locateNearestAltar(world, pos);
            if (newAltar != null && entity.isWithinAltarRange(world, pos, (AltarBlockEntity) world.getBlockEntity(newAltar))) {
                entity.setCachedAltar(newAltar);
            }
        } else if (world.getBlockEntity(entity.getCachedAltar()) instanceof AltarBlockEntity && entity.getTicks() % 40 == 0) {
            AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(entity.getCachedAltar());
            if (!altar.isMultiblock()) {
                BlockPos newAltar = entity.locateNearestAltar(world, pos);
                if (newAltar != null && entity.isWithinAltarRange(world, pos, (AltarBlockEntity) world.getBlockEntity(newAltar))) {
                    entity.setCachedAltar(newAltar);
                }
            }
        }

        if (!entity.getCachedAltar().equals(pos)) {
            if (entity.doesAltarHavePower(world, (AltarBlockEntity) world.getBlockEntity(entity.getCachedAltar()))) {
                entity.hasPower = 1;
            } else {
                entity.hasPower = 0;
            }

            if (hasRecipe(entity)) {
                if (entity.attemptAltarPowerDraw(world, (AltarBlockEntity) world.getBlockEntity(entity.getCachedAltar()), 1)) {
                    entity.powerProgress++;

                    isUsed = true;
                }

                if (entity.powerProgress > entity.maxPowerProgress) {
                    entity.powerProgress = 0;

                    entity.craftProgress++;
                    if (entity.craftProgress > entity.maxCraftProgress) {
                        craftItem(entity);
                    }
                }
            } else {
                entity.resetProgress();

                isUsed = false;
            }
        } else {
            entity.hasPower = 0;
        }

        if (entity.getTicks() % 80 == 0) {
            entity.setTicks(1);
        } else {
            entity.setTicks(entity.getTicks() + 1);
        }

        WitcheryRestitched.LOGGER.info(jarSlot + "");
        WitcheryRestitched.LOGGER.info(ItemStack.EMPTY + "");
        WitcheryRestitched.LOGGER.info(new ItemStack(Blocks.AIR.asItem()) + "");

        //BlockState defaultState = state.getBlock().getDefaultState();

        if (jarSlot.equals(ItemStack.EMPTY)) {
            WitcheryRestitched.LOGGER.info("THIS WAS REACHED");
            //world.setBlockState(pos, defaultState.with(DistilleryBlock.USED, isUsed).with(DistilleryBlock.FACING, state.get(DistilleryBlock.FACING)));
            world.setBlockState(pos, state.with(DistilleryBlock.JARS, 0).with(DistilleryBlock.USED, isUsed));
        } else {
            if (jarSlot.getCount() >= 4) {
                world.setBlockState(pos, state.with(DistilleryBlock.JARS, 4).with(DistilleryBlock.USED, isUsed));
            } else {
                world.setBlockState(pos, state.with(DistilleryBlock.JARS, jarSlot.getCount()).with(DistilleryBlock.USED, isUsed));
            }
        }

        entity.markDirty();
    }

    // NBT DATA

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("distillery.craftProgress", craftProgress);
        nbt.putInt("distillery.hasPower", hasPower);
        nbt.putInt("distillery.powerProgress", powerProgress);

        nbt.putDouble("witcheryrestitched:altarX", this.cachedAltar.getX());
        nbt.putDouble("witcheryrestitched:altarY", this.cachedAltar.getY());
        nbt.putDouble("witcheryrestitched:altarZ", this.cachedAltar.getZ());

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        craftProgress = nbt.getInt("distillery.craftProgress");
        hasPower = nbt.getInt("distillery.hasPower");
        powerProgress = nbt.getInt("distillery.powerProgress");

        double x = nbt.getDouble("witcheryrestitched:altarX");
        double y = nbt.getDouble("witcheryrestitched:altarY");
        double z = nbt.getDouble("witcheryrestitched:altarZ");
        this.cachedAltar = new BlockPos(x, y, z);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    // RECIPE METHODS

    private static boolean hasRecipe(DistilleryBlockEntity entity) {
        if (entity.inventory.get(0) != ItemStack.EMPTY) {
            World world = entity.world;
            SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
            for (int i = 0; i < entity.inventory.size(); i++) {
                inventory.setStack(i, entity.getStack(i));
            }

            Optional<DistilleryRecipe> match = world.getRecipeManager()
                    .getFirstMatch(DistilleryRecipe.Type.INSTANCE, inventory, world);

            return match.isPresent() && canInsertAmountIntoOutputSlots(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match.get().getFirstOutput(), 3)
                    && canInsertItemIntoOutputSlot(inventory, match.get().getSecondOutput(), 4)
                    && canInsertItemIntoOutputSlot(inventory, match.get().getThirdOutput(), 5)
                    && canInsertItemIntoOutputSlot(inventory, match.get().getFourthOutput(), 6)
                    && entity.inventory.get(2).getCount() >= match.get().getJarCount();
        }
        return false;
    }

    private static void craftItem(DistilleryBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<DistilleryRecipe> match = world.getRecipeManager()
                .getFirstMatch(DistilleryRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {

            entity.removeStack(0,1);
            entity.removeStack(1,1);
            entity.removeStack(2,match.get().getJarCount());

            ItemStack firstOutput = match.get().getFirstOutput();
            ItemStack secondOutput = match.get().getSecondOutput();
            ItemStack thirdOutput = match.get().getThirdOutput();
            ItemStack fourthOutput = match.get().getFourthOutput();
            entity.setStack(3, new ItemStack(firstOutput.getItem(), entity.getStack(3).getCount() + firstOutput.getCount()));
            entity.setStack(4, new ItemStack(secondOutput.getItem(), entity.getStack(4).getCount() + secondOutput.getCount()));
            entity.setStack(5, new ItemStack(thirdOutput.getItem(), entity.getStack(5).getCount() + thirdOutput.getCount()));
            entity.setStack(6, new ItemStack(fourthOutput.getItem(), entity.getStack(6).getCount() + fourthOutput.getCount()));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.craftProgress = 0;
        this.powerProgress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output, int slot) {
        return inventory.getStack(slot).getItem() == output.getItem() || inventory.getStack(slot).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlots(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount() &&
                inventory.getStack(4).getMaxCount() > inventory.getStack(4).getCount() &&
                inventory.getStack(5).getMaxCount() > inventory.getStack(5).getCount() &&
                inventory.getStack(6).getMaxCount() > inventory.getStack(6).getCount();
    }
}
