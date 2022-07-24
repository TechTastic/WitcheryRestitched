package net.techtastic.witcheryrestitched.block.custom.rowan;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.techtastic.witcheryrestitched.block.custom.keyed.KeyedButtonBlock;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import net.techtastic.witcheryrestitched.block.entity.rowan.RowanButtonBlockEntity;
import org.jetbrains.annotations.Nullable;

public class RowanKeyedButtonBlock extends KeyedButtonBlock {
    public RowanKeyedButtonBlock(Settings settings) {
        super(ModBlockEntities.ROWAN_BUTTON_BLOCK_ENTITY, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RowanButtonBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
