package net.techtastic.witcheryrestitched.block.entity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.block.custom.CauldronBlock;
import net.techtastic.witcheryrestitched.recipe.CauldronRecipe;
import net.techtastic.witcheryrestitched.recipe.DistilleryRecipe;
import net.techtastic.witcheryrestitched.screen.DistilleryScreenHandler;
import net.techtastic.witcheryrestitched.util.IAltarPowerSink;
import net.techtastic.witcheryrestitched.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CauldronBlockEntity extends BlockEntity implements IAltarPowerSink, ImplementedInventory {
    private BlockPos cachedAltar = this.pos;
    private int ticks = 0;
    private int powerDrawAmount = 0;
    private int craftProgress = 0;
    private int maxCraftProgress = 50;
    private boolean isBoiling = false;

    private int boilingTicks = 1;

    public DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);

    // This field is going to contain the amount, and the fluid variant (more on that in a bit).
    public SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            // Here, you can pick your capacity depending on the fluid variant.
            // For example, if we want to store 8 buckets of any fluid:
            return 3 * FluidConstants.BUCKET;
        }

        @Override
        protected void onFinalCommit() {
            // Called after a successful insertion or extraction, markDirty to ensure the new amount and variant will be saved properly.
            markDirty();
        }

        @Override
        protected boolean canInsert(FluidVariant variant) {
            if (variant.isOf(Fluids.WATER)) {
                return super.canInsert(variant);
            }
            return false;
        }
    };

    public CauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CAULDRON, pos, state);
    }

    // GETTERS AND SETTERS

    public SingleVariantStorage<FluidVariant> getFluidStorage() {return this.fluidStorage;}

    public BlockPos getCachedAltar() {
        return this.cachedAltar;
    }

    public void setCachedAltar(BlockPos pos) {
        this.cachedAltar = pos;
    }

    public int getPowerDrawAmount() {return this.powerDrawAmount;}
    public void setPowerDrawAmount(int x) {this.powerDrawAmount = x;}

    public boolean isBoiling() {return this.isBoiling;}
    public void setBoiling(boolean bool) {this.isBoiling = bool;}

    public int getBoilingTicks() {return this.boilingTicks;}
    public void setBoilingTicks(int x) {this.boilingTicks = x;}

    public void emptyLiquidStorage() {this.fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            // Here, you can pick your capacity depending on the fluid variant.
            // For example, if we want to store 8 buckets of any fluid:
            return 3 * FluidConstants.BUCKET;
        }

        @Override
        protected void onFinalCommit() {
            // Called after a successful insertion or extraction, markDirty to ensure the new amount and variant will be saved properly.
            markDirty();
        }

        @Override
        protected boolean canInsert(FluidVariant variant) {
            if (variant.isOf(Fluids.WATER)) {
                return super.canInsert(variant);
            }
            return false;
        }
    };}

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

    // TICKER

    public static void tick(World world, BlockPos pos, BlockState state, CauldronBlockEntity entity) {

        // FIND AND TEST NEAREST ALTAR
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

        // CHECKING FLUID LEVEL AND SETTING BOILING STATE
        if (entity.fluidStorage.getAmount() == entity.fluidStorage.getCapacity()) {
            int maxBoilingTicks = 0;
            if (world.getFluidState(pos.down()).isEqualAndStill(Fluids.LAVA)) {
                maxBoilingTicks = 25;
            } else {
                if (world.getBlockState(pos.down()).isIn(BlockTags.FIRE) || world.getBlockState(pos.down()).isIn(BlockTags.CAMPFIRES)) {
                    maxBoilingTicks = 50;
                } else if (world.getBlockState(pos.down()).isIn(BlockTags.CANDLES) || world.getBlockState(pos.down()).isOf(Blocks.TORCH)
                        || world.getBlockState(pos.down()).isOf(Blocks.SOUL_TORCH) || world.getBlockState(pos.down()).isOf(Blocks.MAGMA_BLOCK)) {
                    maxBoilingTicks = 100;
                } else {
                    entity.setBoilingTicks(1);
                }
            }

            if (maxBoilingTicks != 0) {
                if (entity.getBoilingTicks() % maxBoilingTicks == 0 || entity.getBoilingTicks() > maxBoilingTicks) {
                    entity.setBoiling(true);
                    entity.setBoilingTicks(1);
                } else {
                    entity.setBoilingTicks(entity.getBoilingTicks() + 1);
                }
            } else {
                entity.setBoiling(false);
            }
        } else if (entity.getBoilingTicks() > 1 || entity.isBoiling()) {
            entity.setBoilingTicks(1);
            entity.setBoiling(false);
        }

        // RECIPE TESTING
        if (hasRecipe(entity) && entity.isBoiling()) {
            entity.craftProgress++;
            if (entity.craftProgress > entity.maxCraftProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
        }

        // TICKING VARIABLES
        if (entity.getTicks() % 80 == 0) {
            entity.setTicks(1);
        } else {
            entity.setTicks(entity.getTicks() + 1);
        }

        entity.markDirty();
    }

    // NBT DATA

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("distillery.craftProgress", craftProgress);

        nbt.put("fluidVariant", fluidStorage.variant.toNbt());
        nbt.putLong("amount", fluidStorage.amount);

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

        fluidStorage.variant = FluidVariant.fromNbt(nbt.getCompound("fluidVariant"));
        fluidStorage.amount = nbt.getLong("amount");

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

    private static boolean hasRecipe(CauldronBlockEntity entity) {
        if (entity.inventory.get(0) != ItemStack.EMPTY) {
            World world = entity.world;
            SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
            for (int i = 0; i < entity.inventory.size(); i++) {
                inventory.setStack(i, entity.getStack(i));
            }

            Optional<CauldronRecipe> match = world.getRecipeManager()
                    .getFirstMatch(CauldronRecipe.Type.INSTANCE, inventory, world);

            return match.isPresent();
        }
        return false;
    }

    private static void craftItem(CauldronBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CauldronRecipe> match = world.getRecipeManager()
                .getFirstMatch(CauldronRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            if (match.get().getPower() != 0) {
                if (entity.attemptAltarPowerDraw(world, (AltarBlockEntity) world.getBlockEntity(entity.cachedAltar), match.get().getPower())) {
                    entity.inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
                    entity.fluidStorage.amount = 0;

                    ItemEntity output = new ItemEntity(world, entity.pos.getX(), entity.pos.getY(), entity.pos.getZ(),
                            match.get().getOutput(), 0.0, 0.1, 0.0);
                    world.spawnEntity(output);
                } else {
                    entity.inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
                }
            } else {
                entity.inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
                entity.fluidStorage.amount = 0;

                ItemEntity output = new ItemEntity(world, entity.pos.getX(), entity.pos.getY(), entity.pos.getZ(),
                        match.get().getOutput(), 0.0, 0.1, 0.0);
                world.spawnEntity(output);
            }

            entity.resetProgress();
        }

        entity.markDirty();
    }

    private void resetProgress() {
        this.craftProgress = 0;
    }
}
