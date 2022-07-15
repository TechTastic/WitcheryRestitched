package net.techtastic.witcheryrestitched.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.screen.AltarScreenHandler;
import net.techtastic.witcheryrestitched.screen.CastIronOvenScreenHandler;
import net.techtastic.witcheryrestitched.util.ImplementedInventory;
import net.techtastic.witcheryrestitched.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static net.techtastic.witcheryrestitched.block.custom.AltarBlock.MULTIBLOCK;

public class AltarBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    // MULTIBLOCK VARIABLES
    private boolean isMultiblock = false;
    private boolean isMasterBlock = false;
    private BlockPos masterBlockPos = this.pos;
    
    private BlockBox structure = null;

    //ALTAR POWER VARIABLES
    private int maxAltarPower = 0;
    private int basePowerIncrement = 10;
    private int currentAltarPower = 0;
    private int rate = 1;
    private int range = 16;

    //TICKER

    private int ticks = 1;

    // GUI PROPERTYDELEGATE

    protected final PropertyDelegate propertyDelegate;

    public AltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return AltarBlockEntity.this.maxAltarPower;
                    case 1:
                        return AltarBlockEntity.this.currentAltarPower;
                    case 2:
                        return AltarBlockEntity.this.rate;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        AltarBlockEntity.this.maxAltarPower = value;
                        break;
                    case 1:
                        AltarBlockEntity.this.currentAltarPower = value;
                        break;
                    case 2:
                        AltarBlockEntity.this.rate = value;
                        break;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    // ALTAR POWER GETTERS AND SETTERS

    public int getMaxAltarPower() {
        return this.maxAltarPower;
    }

    private void setMaxAltarPower(int x) {
        this.maxAltarPower = x;
    }

    public int getBasePowerIncrement() {
        return this.basePowerIncrement;
    }

    public int getCurrentAltarPower() {
        return this.currentAltarPower;
    }

    private void setCurrentAltarPower(int x) {
        this.currentAltarPower = x;
    }

    public int getAltarRate() {
        return this.rate;
    }

    private void setAltarRate(int x) {
        this.rate = x;
    }

    public int getAltarRange() {
        return this.range;
    }

    private void setAltarRange(int x) {
        this.range = x;
    }

    // MULTIBLOCK GETTERS AND SETTERS

    public boolean isMultiblock() {
        /*if (!this.isMultiblock) {
            WitcheryRestitched.LOGGER.info("THIS WAS NOT A MULTIBLOCK!");
        }*/
        return this.isMultiblock;
    }
    public void setMultiblock(boolean bool) {
        this.isMultiblock = bool;
    }

    public boolean isMasterBlock() {
        return this.isMasterBlock;
    }
    public void setMasterBlock(boolean bool) {
        this.isMasterBlock = bool;
    }

    public BlockPos getMasterBlockPos() {
        return this.masterBlockPos;
    }
    public void setMasterBlockPos(BlockPos pos) {
        this.masterBlockPos = pos;
    }

    public BlockBox getStructure() {
        return this.structure;
    }

    public void setStructure(BlockBox structure) {
        this.structure = structure;
    }

    // SCREEN HANDLER

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(0, ItemStack.EMPTY);

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new AltarScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Altar");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // TICKER

    private int getTicks() {
        return this.ticks;
    }

    private void resetTicks() {
        this.ticks = 1;
    }

    private void incrementTicks() {
        this.ticks++;
    }

    // TICK UPDATE METHODS

    public void validateMultiblock(World world, BlockPos pos, BlockBox multiblock) {
        int altarCount = 0;
        BlockPos.Mutable testPos = new BlockPos.Mutable();
        for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
            for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                if (world.getBlockState(testPos.set(x, pos.getY(), z)).getBlock() == ModBlocks.ALTAR) {
                    altarCount++;
                }
            }
        }

        if (altarCount == 6) {
            for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
                for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                    AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(testPos.set(x, pos.getY(), z));
                    altar.setMultiblock(true);
                    altar.setMasterBlockPos(multiblock.getCenter());
                    if (testPos.equals(multiblock.getCenter())) {
                        altar.setMasterBlock(true);
                    }

                    altar.setStructure(multiblock);

                    altar.markDirty();

                    world.setBlockState(testPos, world.getBlockState(testPos).with(MULTIBLOCK, true));
                }
            }
        } else {
            for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
                for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                    if (world.getBlockState(testPos.set(x, pos.getY(), z)).getBlock() == ModBlocks.ALTAR) {
                        AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(testPos.set(x, pos.getY(), z));
                        altar.setMultiblock(false);
                        altar.setMasterBlockPos(testPos);
                        altar.setMasterBlock(false);

                        altar.setStructure(null);

                        altar.markDirty();

                        world.setBlockState(testPos, world.getBlockState(testPos).with(MULTIBLOCK, false));
                    }
                }
            }
        }
    }

    public BlockBox getPossibleMultiblock(World world, BlockPos pos) {
        BlockBox multiblock = null;
        
        Block[] neighbors = new Block[4];
        neighbors[0] = world.getBlockState(pos.north()).getBlock();
        neighbors[1] = world.getBlockState(pos.east()).getBlock();
        neighbors[2] = world.getBlockState(pos.south()).getBlock();
        neighbors[3] = world.getBlockState(pos.west()).getBlock();
        
        int altarCount = 0;
        for (int i = 0; i < 4; i++) {
            if (neighbors[i] == ModBlocks.ALTAR) {
                altarCount++;
            }
        }
        
        if (altarCount == 2) {
            if (neighbors[0] == ModBlocks.ALTAR && neighbors[1] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE SOUTHWEST

                if (world.getBlockState(pos.north().north()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL
                    
                    multiblock = BlockBox.create(pos, pos.north().north().east());
                } else if (world.getBlockState(pos.east().east()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.east().east().north());
                }
            } else if (neighbors[0] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE SOUTHEAST

                if (world.getBlockState(pos.north().north()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL

                    multiblock = BlockBox.create(pos, pos.north().north().west());
                } else if (world.getBlockState(pos.west().west()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.west().west().north());
                }
            } else if (neighbors[2] == ModBlocks.ALTAR && neighbors[1] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE NORTHWEST

                if (world.getBlockState(pos.south().south()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL

                    multiblock = BlockBox.create(pos, pos.south().south().east());
                } else if (world.getBlockState(pos.east().east()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.east().east().south());
                }
            } else if (neighbors[2] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR && true) {
                //THIS BLOCK IS A CORNER BLOCK IN THE NORTHEAST

                if (world.getBlockState(pos.south().south()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS VERTICAL

                    multiblock = BlockBox.create(pos, pos.south().south().west());
                } else if (world.getBlockState(pos.west().west()).getBlock() == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS HORIZONTAL

                    multiblock = BlockBox.create(pos, pos.west().west().south());
                }
            }
        } else if (altarCount == 3) {
            if (neighbors[0] == ModBlocks.ALTAR && neighbors[2] == ModBlocks.ALTAR) {
                //THIS IS A CENTER BLOCK IN A VERTICAL MULTIBLOCK
                
                if (neighbors[1] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING EAST
                    
                    multiblock = BlockBox.create(pos.south(), pos.north().east());
                } else if (neighbors[3] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING WEST

                    multiblock = BlockBox.create(pos.south(), pos.north().west());
                }
            } else if (neighbors[1] == ModBlocks.ALTAR && neighbors[3] == ModBlocks.ALTAR) {
                //THIS IS A CENTER BLOCK IN A HORIZONAL MULTIBLOCK

                if (neighbors[0] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING NORTH

                    multiblock = BlockBox.create(pos.east(), pos.west().north());
                } else if (neighbors[2] == ModBlocks.ALTAR) {
                    //THE MULTIBLOCK IS FACING SOUTH

                    multiblock = BlockBox.create(pos.east(), pos.west().south());
                }
            }
        }

        if (multiblock != null) {
            for (int x = multiblock.getMinX(); x < multiblock.getMaxX() + 1; x++) {
                for (int z = multiblock.getMinZ(); z < multiblock.getMaxZ() + 1; z++) {
                    AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(new BlockPos(x, pos.getY(), z));
                    if (altar.isMultiblock()) {
                        multiblock = null;
                        break;
                    }
                }
            }
        }
        
        return multiblock;
    }

    public void updateCurrentAltarPower() {
        int currentAltarPower = this.getCurrentAltarPower();
        int maxAltarPower = this.getMaxAltarPower();
        int basePowerIncrement = this.getBasePowerIncrement();
        int altarRate = this.getAltarRate();

        int newCurrentPower = currentAltarPower + basePowerIncrement * altarRate;

        if (newCurrentPower >= maxAltarPower) {
            this.setCurrentAltarPower(maxAltarPower);
        } else {
            this.setCurrentAltarPower(newCurrentPower);
        }
    }

    // For each block state, call this method in order to retrieve the key:
    public static String getKeyString(BlockState state) {
        if (state.isIn(BlockTags.LEAVES)) { // Here, check for all block groups that shall be counted together/have a common limit
            if (!state.isIn(ModTags.Blocks.MOD_LEAVES)) {
                return "leaves";
            } else {
                return "mod_leaves";
            }
        } else if (state.isIn(BlockTags.DIRT)) {
            if (state.getBlock() != Blocks.GRASS_BLOCK) {
                if (state.getBlock() != Blocks.MYCELIUM) {
                    if (state.getBlock() != Blocks.PODZOL) {
                        if (state.getBlock() != Blocks.MOSS_BLOCK) {
                            return "dirt";
                        }
                    }
                }
            }
        } else if (state.isIn(BlockTags.LOGS)) {
            if (!state.isIn(ModTags.Blocks.MOD_LOGS)) {
                return "logs";
            } else {
                return "mod_logs";
            }
        } else if (state.isIn(BlockTags.CROPS)) {
            if (state.getBlock() == Blocks.PUMPKIN_STEM || state.getBlock() == Blocks.MELON_STEM) {
                return "stem_crops";
            }
        } else if (state.isIn(BlockTags.SAPLINGS)) {
            return "saplings";
        }
        String name = Registry.BLOCK.getKey(state.getBlock()).get().getValue().toString();
        String trueName = name.replace(Registry.BLOCK.getKey(state.getBlock()).get().getValue().getNamespace() + ":", "");
        return trueName; // forgot the name of the method that returns just the string name of a block, like "oak_planks" for example
    }

    private static Map<String, Integer> getBlockLimits() {
        Map<String, Integer> blockCountLimits = new HashMap<>();

        // 10, 20, 30, 50, 80, 100

        /*String[] tenCount = new String[0];

        for (int i = 0; i < tenCount.length; i++) {
            blockCountLimits.put(tenCount[i], 10);
        }*/

        String[] twentyCount = new String[7];
        twentyCount[0] = "moss_block";
        twentyCount[1] = "wheat";
        twentyCount[2] = "carrots";
        twentyCount[3] = "potatoes";
        twentyCount[4] = "beetroots";
        twentyCount[5] = "stem_crops";
        twentyCount[6] = "saplings";

        for (int i = 0; i < twentyCount.length; i++) {
            blockCountLimits.put(twentyCount[i], 20);
        }

        /*String[] thirtyCount = new String[0];

        for (int i = 0; i < thirtyCount.length; i++) {
            blockCountLimits.put(thirtyCount[i], 30);
        }*/

        String[] fiftyCount = new String[2];
        fiftyCount[0] = "mod_leaves";
        fiftyCount[1] = "logs";

        for (int i = 0; i < fiftyCount.length; i++) {
            blockCountLimits.put(fiftyCount[i], 50);
        }

        String[] eightyCount = new String[3];
        eightyCount[0] = "dirt";
        eightyCount[1] = "grass_block";
        eightyCount[2] = "mycelium";

        for (int i = 0; i < eightyCount.length; i++) {
            blockCountLimits.put(eightyCount[i], 80);
        }

        String[] hundredCount = new String[2];
        hundredCount[0] = "leaves";
        hundredCount[1] = "mod_logs";

        for (int i = 0; i < hundredCount.length; i++) {
            blockCountLimits.put(hundredCount[i], 100);
        }

        return blockCountLimits;
    }

    private static Map<String, Integer> getBlockPowers() {
        Map<String, Integer> blockPowers = new HashMap<>();

        // 1, 2, 3, 4

        String[] one = new String[3];
        one[0] = "dirt";
        one[1] = "podzol";
        one[2] = "mycelium";

        for (int i = 0; i < one.length; i++) {
            blockPowers.put(one[i], 1);
        }

        String[] two = new String[3];
        two[0] = "grass_block";
        two[1] = "moss_block";
        two[2] = "logs";

        for (int i = 0; i < two.length; i++) {
            blockPowers.put(two[i], 2);
        }

        String[] three = new String[3];
        three[0] = "stem_crops";
        three[1] = "leaves";
        three[2] = "mod_logs";

        for (int i = 0; i < three.length; i++) {
            blockPowers.put(three[i], 3);
        }

        String[] four = new String[6];
        four[0] = "saplings";
        four[1] = "mod_leaves";
        four[2] = "wheat";
        four[3] = "carrots";
        four[4] = "potatoes";
        four[5] = "beetroots";

        for (int i = 0; i < four.length; i++) {
            blockPowers.put(four[i], 4);
        }

        return blockPowers;
    }

    public void updateMaxAltarPower(World world, BlockPos pos, BlockBox altar) {
        if (altar != null) {
            int maxAltarPower = 0;
            int rate = 1;
            int range = 16;

            //GET STUFF ON TOP OF ALTAR
            BlockBox onTop = new BlockBox(altar.getMinX(), altar.getMinY() + 1, altar.getMinZ(), altar.getMaxX(), altar.getMaxY() + 1, altar.getMaxZ());
            double powerMultiplier = 1;
            boolean alreadySkull = false;
            boolean alreadyChalice = false;
            boolean alreadyLight = false;

            for (int x = onTop.getMinX(); x < altar.getMaxX() + 1; x++) {
                for (int z = onTop.getMinZ(); z < altar.getMaxZ() + 1; z++) {
                    BlockState testBlock = world.getBlockState(new BlockPos.Mutable(x, pos.getY() + 1, z));

                    if (!alreadySkull) {
                        if (testBlock.getBlock() == Blocks.PLAYER_HEAD) {
                            powerMultiplier += 2.5;
                            rate += 3;
                        } else if (testBlock.getBlock() == Blocks.WITHER_SKELETON_SKULL) {
                            powerMultiplier += 2;
                            rate += 2;
                        } else if (testBlock.getBlock() == Blocks.SKELETON_SKULL) {
                            powerMultiplier += 1;
                            rate += 1;
                        }
                    }

                    if (!alreadyChalice) {
                        if (testBlock.getBlock() == ModBlocks.CHALICE) {
                            if (testBlock != testBlock.getBlock().getDefaultState()) {
                                powerMultiplier += 2;
                            } else {
                                powerMultiplier += 1;
                            }
                        }
                    }

                    if (!alreadyLight) {
                        /*if (testBlock.getBlock() == ModBlocks.CANDELABRA) {
                            rate += 2;
                        } else */if (testBlock.getBlock() == Blocks.TORCH) {
                            rate += 1;
                        }
                    }

                    /*if (testBlock.getBlock() == ModBlocks.PENTACLE) {
                        rate = rate * 2;
                    }

                    if (testBlock.getBlock() == ModBlocks.ARTHANA) {
                        range = 32;
                    }

                    if (testBlock.getBlock() == ModBlocks.INFINITY_EGG) {
                        maxAltarPower = 1000;
                        powerMultiplier += 10;
                        rate = rate * 10;
                    }*/
                }
            }

            //GET NEARBY NATURE

            BlockPos.Mutable center = altar.getCenter().mutableCopy();
            BlockBox surroundings = new BlockBox(center.getX() - range, center.getY() - range, center.getZ() - range,
                    center.getX() + range, center.getY() + range, center.getZ() + range);

            Map<String, Integer> blockCounts = new HashMap<>();  // This Map stores the counts for each block/block group
            Map<String, Integer> blockCountLimits = getBlockLimits();  // Stores the count limits for each block/block group
            Map<String, Integer> blockPowers = getBlockPowers();  // Stores the power each block/block group provides (for example: 3 for leaves, 250 for dragon egg)

            for (int x = surroundings.getMinX(); x < surroundings.getMaxX() + 1; x++) {
                for (int y = surroundings.getMinY(); y < surroundings.getMaxY() + 1; y++) {
                    for (int z = surroundings.getMinZ(); z < surroundings.getMaxZ() + 1; z++) {

                        // In the for loop that analyzes the terrain, do this:
                        BlockState state = world.getBlockState(new BlockPos(x, y, z));
                        if (state.isIn(ModTags.Blocks.NATURE_BLOCKS)) { // This tag contains all blocks that you want to count ('nature blocks')
                            String key = getKeyString(state); // Call the method from above
                            int value = 1;
                            if (blockCounts.containsKey(key)) {
                                value = blockCounts.get(key) + 1;
                            }
                            blockCounts.put(key, value);
                        }
                    }
                }
            }

            // And when you determine the 'max power' of your block entity, iterate though each entry of all maps (they should use the same keys)
            for (String key : blockCounts.keySet()) {
                int count = blockCounts.get(key);
                if (!blockCounts.containsKey(key) || !blockPowers.containsKey(key) || !blockCountLimits.containsKey(key)) {
                    WitcheryRestitched.LOGGER.info(key);
                }
                if (count > blockCountLimits.get(key)) count = blockCountLimits.get(key); // Handle case if the count is bigger than maximum
                maxAltarPower += count * blockPowers.get(key);
            }

            //REAPPLY VARIABLES
            this.setMaxAltarPower( (int) Math.ceil(maxAltarPower * powerMultiplier));
            if (this.getMaxAltarPower() < this.getCurrentAltarPower()) {
                this.setCurrentAltarPower(this.getMaxAltarPower());
            }
            this.setAltarRate(rate);
            this.setAltarRange(range);
            this.markDirty();
        }
    }

    public static void tick(World world1, BlockPos pos, BlockState state1, AltarBlockEntity be) {
        if (be.isMultiblock() && be.isMasterBlock() && true) {
            BlockBox potentialMultiblock = null;

            if (be.getTicks() % 10 == 0) {
                if (be.getStructure() != null) {
                    potentialMultiblock = be.getStructure();
                } else {
                    potentialMultiblock = be.getPossibleMultiblock(world1, pos);
                }

                if (potentialMultiblock != null) {
                    be.validateMultiblock(world1, pos, potentialMultiblock);
                }
            }

            //CHANGE CURRENT ALTAR POWER EVERY 20 TICKS
            if (be.getTicks() % 20 == 0) {

                //CHANGE CURRENT ALTAR POWER
                be.updateCurrentAltarPower();
            }

            //GET ORIENTATION AND CHECK FOR NATURE EVERY 100 TICKS
            if (be.getTicks() % 40 == 0) {

                // SET MAX ALTAR POWER BASED ON NATURE
                be.updateMaxAltarPower(world1, pos, potentialMultiblock);

                be.resetTicks();
            }
        }
        be.incrementTicks();
        be.markDirty();
    }

    // NBT DATA

    @Override
    protected void writeNbt(NbtCompound nbt) {

        // MULTIBLOCK NBT DATA

        nbt.putDouble("witcheryrestitched:masterBlockX", this.masterBlockPos.getX());
        nbt.putDouble("witcheryrestitched:masterBlockY", this.masterBlockPos.getY());
        nbt.putDouble("witcheryrestitched:masterBlockZ", this.masterBlockPos.getZ());
        nbt.putBoolean("witcheryrestitched:ismultiblock", this.isMultiblock);
        nbt.putBoolean("witcheryrestitched:ismasterblock", this.isMasterBlock);

        int[] structureArray = new int[6];
        if (this.structure != null) {
            structureArray[0] = this.structure.getMinX();
            structureArray[1] = this.structure.getMinY();
            structureArray[2] = this.structure.getMinZ();
            structureArray[3] = this.structure.getMaxX();
            structureArray[4] = this.structure.getMaxY();
            structureArray[5] = this.structure.getMaxZ();
        } else {
            for (int i = 0; i < 6; i++) {
                structureArray[i] = 0;
            }
        }
        nbt.putIntArray("witcheryrestitched:structure", structureArray);

        // ALTAR POWER NBT DATA

        nbt.putInt("witcheryrestitched:maxaltarpower", this.maxAltarPower);
        nbt.putInt("witcheryrestitched:basepowerincrement", this.basePowerIncrement);
        nbt.putInt("witcheryrestitched:currentaltarpower", this.currentAltarPower);
        nbt.putInt("witcheryrestitched:rate", this.rate);
        nbt.putInt("witcheryrestitched:range", this.range);

        // PSEUDO INVENTORY DATA

        Inventories.writeNbt(nbt, inventory);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        // MULTIBLOCK NBT DATA

        this.masterBlockPos = new BlockPos(nbt.getDouble("witcheryrestitched:masterBlockX"), nbt.getDouble("witcheryrestitched:masterBlock&"),
                nbt.getDouble("witcheryrestitched:masterBlockZ"));
        this.isMultiblock = nbt.getBoolean("witcheryrestitched:ismultiblock");
        this.isMasterBlock = nbt.getBoolean("witcheryrestitched:ismultiblock");

        int[] structureArray = nbt.getIntArray("witcheryrestitched:structure");
        int[] test = new int[6];
        for (int i = 0; i < 6; i++) {
            test[i] = 0;
        }
        if (structureArray != test) {
            this.structure = new BlockBox(structureArray[0], structureArray[1], structureArray[2], structureArray[3], structureArray[4], structureArray[5]);
        } else {
            this.structure = null;
        }

        // ALTAR POWER NBT DATA

        this.maxAltarPower = nbt.getInt("witcheryrestitched:maxaltarpower");
        this.basePowerIncrement = nbt.getInt("witcheryrestitched:basepowerincrement");
        this.currentAltarPower = nbt.getInt("witcheryrestitched:currentaltarpower");
        this.rate = nbt.getInt("witcheryrestitched:rate");
        this.range = nbt.getInt("witcheryrestitched:range");

        // PSEUDO INVENTORY DATA

        Inventories.readNbt(nbt, inventory);
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
