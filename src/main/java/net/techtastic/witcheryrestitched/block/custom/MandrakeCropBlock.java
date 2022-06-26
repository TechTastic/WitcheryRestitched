package net.techtastic.witcheryrestitched.block.custom;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.item.ModItems;

public class MandrakeCropBlock extends CropBlock {
    public MandrakeCropBlock(Settings settings)
    {
        super(settings);
    }

    boolean day = false;
    World blockWorld;
    ItemStack mainHand;

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.MANDRAKE_SEEDS;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        day = world.isDay();
        blockWorld = world;
        mainHand = player.getMainHandStack();

        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        day = world.isDay();
        blockWorld = world;

        if (entity.isPlayer()) {
            PlayerEntity player = (PlayerEntity) entity;
            mainHand = player.getMainHandStack();
        } else {
            mainHand = ItemStack.EMPTY;
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

     @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        double random = Math.random() * 1;

        if (day) {
            if (isHoldingSword(mainHand)) {
                if (random < 0.7) {
                    spawnRootItem(blockWorld, pos);
                    spawnSeedItem(blockWorld, pos);
                } else {
                    spawnMandrake(blockWorld, pos);
                    spawnSeedItem(blockWorld, pos);
                }
            } else {
                spawnMandrake(blockWorld, pos);
                spawnSeedItem(blockWorld, pos);
            }
        } else {
            if (random < 0.9) {
                spawnRootItem(blockWorld, pos);
                spawnSeedItem(blockWorld, pos);
            } else {
                spawnMandrake(blockWorld, pos);
                spawnSeedItem(blockWorld, pos);
            }
        }
    }

    private static void spawnRootItem (World world, BlockPos pos) {
        ItemStack rootItem = ModItems.MANDRAKE_ROOT.getDefaultStack();
        double chance = Math.random() * 1;
        if (chance > 0.5) {
            rootItem.increment(2);
        } else {
            rootItem.increment(1);
        }

        ItemEntity root = new ItemEntity(world, pos.getX(), pos.getY(), pos.getX(), rootItem);
        root.setPosition(Vec3d.ofCenter(pos));
        world.spawnEntity(root);
    }

    private static void spawnSeedItem (World world, BlockPos pos) {
        ItemStack seedItem = ModItems.MANDRAKE_SEEDS.getDefaultStack();
        double chance = Math.random() * 1;
        if (chance > 0.7) {
            seedItem.increment(2);
        } else {
            seedItem.increment(1);
        }

        ItemEntity seeds = new ItemEntity(world, pos.getX(), pos.getY(), pos.getX(), seedItem);
        seeds.setPosition(Vec3d.ofCenter(pos));
        world.spawnEntity(seeds);
    }

    private boolean isHoldingSword(ItemStack stack) {
        if (stack != ItemStack.EMPTY && stack.getItem().getDefaultStack().isIn(ConventionalItemTags.SWORDS)) {
            return true;
        }
        return false;
    }

    public static void spawnMandrake (World world, BlockPos pos) {
        LivingEntity mandrake = new SheepEntity(EntityType.SHEEP, world);
        mandrake.setPosition(Vec3d.ofCenter(pos));
        world.spawnEntity(mandrake);
    }
}
