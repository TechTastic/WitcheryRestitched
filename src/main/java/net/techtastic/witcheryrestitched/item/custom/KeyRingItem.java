package net.techtastic.witcheryrestitched.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class KeyRingItem extends Item {
    public KeyRingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        WitcheryRestitched.LOGGER.info("THIS IS BEING USED?");
        if (!world.isClient) {
            WitcheryRestitched.LOGGER.info("IS NOT CLIENT");
            if (user.isSneaking()) {
                WitcheryRestitched.LOGGER.info("IS PLAYER SNEAKING");
                NamedScreenHandlerFactory screenHandlerFactory = world.getBlockState(user.getBlockPos()).createScreenHandlerFactory(world, user.getBlockPos());

                if (screenHandlerFactory != null) {
                    WitcheryRestitched.LOGGER.info("OPEN THE SCREEN");
                    user.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return TypedActionResult.success(stack);
    }
}
