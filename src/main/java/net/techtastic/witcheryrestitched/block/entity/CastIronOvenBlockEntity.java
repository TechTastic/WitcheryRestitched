package net.techtastic.witcheryrestitched.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.recipe.CastIronOvenRecipe;
import net.techtastic.witcheryrestitched.screen.CastIronOvenScreenHandler;
import net.techtastic.witcheryrestitched.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.util.math.Direction.*;
import static net.techtastic.witcheryrestitched.block.custom.CastIronOvenBlock.FACING;

public class CastIronOvenBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public CastIronOvenBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CAST_IRON_OVEN, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0: return CastIronOvenBlockEntity.this.progress;
                    case 1: return CastIronOvenBlockEntity.this.maxProgress;
                    case 2: return CastIronOvenBlockEntity.this.fuelTime;
                    case 3: return CastIronOvenBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: CastIronOvenBlockEntity.this.progress = value; break;
                    case 1: CastIronOvenBlockEntity.this.maxProgress = value; break;
                    case 2: CastIronOvenBlockEntity.this.fuelTime = value; break;
                    case 3: CastIronOvenBlockEntity.this.maxFuelTime = value; break;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Cast Iron Oven");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CastIronOvenScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("oven.progress", progress);
        nbt.putInt("oven.fuelTime", fuelTime);
        nbt.putInt("oven.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("oven.progress");
        fuelTime = nbt.getInt("oven.fuelTime");
        maxFuelTime = nbt.getInt("oven.maxFuelTime");
    }

    private void consumeFuel() {
        if(!getStack(0).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, CastIronOvenBlockEntity entity) {
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
        }

        if(hasRecipe(entity)) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(CastIronOvenBlockEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(CastIronOvenBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(CastIronOvenBlockEntity entity) {
        if (entity.inventory.get(1) != ItemStack.EMPTY) {
            World world = entity.world;
            SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
            for (int i = 0; i < entity.inventory.size(); i++) {
                inventory.setStack(i, entity.getStack(i));
            }

            Optional<CastIronOvenRecipe> match = world.getRecipeManager()
                    .getFirstMatch(CastIronOvenRecipe.Type.INSTANCE, inventory, world);

            return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
        }
        return false;
    }

    private static void craftItem(CastIronOvenBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CastIronOvenRecipe> match = world.getRecipeManager()
                .getFirstMatch(CastIronOvenRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {

            //Code for Jars
            if (canInsertAmountIntoSecondaryOutputSlot(inventory) &&
                    canInsertItemIntoSecondaryOutputSlot(inventory, new ItemStack(match.get().getSecondaryOutput().getItem()))) {

                Random ran = Random.create();
                if (ran.nextInt(100) < jarChance(entity, world)) {
                    entity.removeStack(2, 1);
                    entity.setStack(4, new ItemStack(match.get().getSecondaryOutput().getItem(),
                            entity.getStack(4).getCount() + 1));
                }
            }
            entity.removeStack(1,1);

            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(), entity.getStack(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }

    private static boolean canInsertItemIntoSecondaryOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(4).getItem() == output.getItem() || inventory.getStack(4).isEmpty();
    }

    private static boolean canInsertAmountIntoSecondaryOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(4).getMaxCount() > inventory.getStack(4).getCount();
    }

    private static int jarBonus(World world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() == Blocks.IRON_BLOCK) {
            return 15;
        }
        if (world.getBlockState(pos).getBlock() == Blocks.DIAMOND_BLOCK) {
            return 30;
        }
        return 0;
    }

    private static double jarChance(CastIronOvenBlockEntity entity, World world) {
        BlockPos entityPos = entity.getPos();
        int aboveNeighbor = jarBonus(world, new BlockPos(entityPos.getX(), entityPos.getY() + 1, entityPos.getZ()));

        if (entity.world.getBlockState(entity.getPos()).get(FACING) == NORTH || entity.world.getBlockState(entity.getPos()).get(FACING) == SOUTH) {
            int eastNeighbor = jarBonus(world, new BlockPos(entityPos.getX() + 1, entityPos.getY(), entityPos.getZ()));
            int westNeighbor = jarBonus(world, new BlockPos(entityPos.getX() - 1, entityPos.getY(), entityPos.getZ()));

            return 10 + eastNeighbor + westNeighbor + aboveNeighbor;
        } else {
            int northNeighbor = jarBonus(world, new BlockPos(entityPos.getX(), entityPos.getY(), entityPos.getZ() - 1));
            int southNeighbor = jarBonus(world, new BlockPos(entityPos.getX(), entityPos.getY(), entityPos.getZ() + 1));

            return 10 + northNeighbor + southNeighbor + aboveNeighbor;
        }
    }
}
