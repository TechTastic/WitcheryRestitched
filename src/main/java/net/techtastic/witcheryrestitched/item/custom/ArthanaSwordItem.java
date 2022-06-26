package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FrogEntityRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.item.ModItems;

import java.rmi.registry.Registry;

public class ArthanaSwordItem extends SwordItem {
    public ArthanaSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.isAlive()) {
            attacker.sendMessage(Text.of("Its Dead!"));

            World world = target.getEntityWorld();
            BlockPos pos = target.getBlockPos();

            if (target.getType() == EntityType.BAT) {
                spawnDrop(world, pos, new ItemStack(ModItems.WOOL_OF_BAT), target, attacker);
            } else if (target.getType() == EntityType.CREEPER) {
                spawnDrop(world, pos, new ItemStack(ModItems.WOOL_OF_BAT), target, attacker);
            } else if (target.getType() == EntityType.FROG) {
                attacker.sendMessage(Text.of("It was a Frog!"));
                spawnDrop(world, pos, new ItemStack(ModItems.TOE_OF_FROG), target, attacker);
                attacker.sendMessage(Text.of("It was a Frog and it dropped an Item!"));
            }
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user.getStackInHand(hand).hasEnchantments()) {
                user.sendMessage(Text.of("Its Enchanted!"));

                String nbtData = user.getStackInHand(hand).getEnchantments().get(0).toString();
                String id = "";
                int lvlStart = 0;
                int lvl = 0;
                for (int i = 0; i < nbtData.length() - 1; i++) {
                    if (nbtData.substring(i, i + 4) == "id:") {
                        for (int k = i + 3; k < nbtData.length() - 1; k++) {
                            if (nbtData.substring(k, k + 1) == ",") {
                                id = nbtData.substring(i + 4, k - 1);
                                lvlStart = k;
                            }
                        }
                    }
                }
                if (id == "minecraft:looting") {
                    for (int v = lvlStart; v < nbtData.length() - 1; v++) {
                        if (nbtData.substring(v, v + 5) == "lvl:") {
                            lvl = Integer.parseInt(nbtData.substring(v + 4, v + 5));
                        }
                    }

                    user.sendMessage(Text.of("The Arthana has " + id + " at level " + lvl + "!!!"));
                }
            } else {
                user.sendMessage(Text.of("Its NOT Enchanted!"));
            }
        }

        return super.use(world, user, hand);
    }

    public void spawnDrop (World world, BlockPos pos, ItemStack dropStack, LivingEntity target, LivingEntity user) {
        ItemStack nbtStack = dropStack;

        if (target.getType() == EntityType.FROG) {
            FrogEntity frog = (FrogEntity) target;
            String frogTest = frog.getVariant().toString();
            int fv = 2;

            user.sendMessage(Text.of(frogTest));

            if (frogTest == "FrogVariant[texture=minecraft:textures/entity/frog/temperate_frog.png]") {
                fv = 0;
            } else if (frogTest == "FrogVariant[texture=minecraft:textures/entity/frog/warm_frog.png]") {
                fv = 1;
            }

            NbtCompound nbtData = new NbtCompound();
            nbtData.putInt("witcheryresttiched:frogvariant", fv);
            nbtStack.setNbt(nbtData);
        }

        ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getX(), nbtStack);
        item.setPosition(Vec3d.ofCenter(pos));
        world.spawnEntity(item);
    }
}
