package net.techtastic.witcheryrestitched.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.techtastic.witcheryrestitched.WitcheryRestitched;
import net.techtastic.witcheryrestitched.block.ModBlocks;
import net.techtastic.witcheryrestitched.block.entity.AltarBlockEntity;
import net.techtastic.witcheryrestitched.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BlockWithEntity implements BlockEntityProvider {
    public AltarBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(MULTIBLOCK, false));
    }

    public static final BooleanProperty MULTIBLOCK = BooleanProperty.of("multiblock");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MULTIBLOCK);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        AltarBlockEntity be = (AltarBlockEntity) world.getBlockEntity(pos);

        BlockBox altarStructure = be.getPossibleMultiblock(world, pos);
        if (altarStructure != null) {
            be.validateMultiblock(world, pos, altarStructure);
        }

        be.markDirty();

        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (world.getBlockEntity(pos) instanceof AltarBlockEntity) {
            AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(pos);
            if (altar.isMultiblock()) {
                if (altar.isMasterBlock()) {
                    if (world.isClient()) {
                        entity.sendMessage(Text.of("Max altar power is at " + altar.getMaxAltarPower()));
                        entity.sendMessage(Text.of("Current altar power is at " + altar.getCurrentAltarPower()));
                        entity.sendMessage(Text.of("Altar rate is at " + altar.getAltarRate()));
                        entity.sendMessage(Text.of("Altar range is at " + altar.getAltarRange()));
                    }
                } else {
                    BlockPos master = altar.getMasterBlockPos();
                    onLandedUpon(world, state, master, entity, fallDistance);
                }
            }
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof AltarBlockEntity) {
            AltarBlockEntity altar = (AltarBlockEntity) world.getBlockEntity(pos);
            if (altar.isMultiblock()) {
                if (altar.isMasterBlock()) {
                    if (!world.isClient) {
                        //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
                        //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
                        NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

                        if (screenHandlerFactory != null) {
                            //With this call the server will request the client to open the appropriate Screenhandler
                            player.openHandledScreen(screenHandlerFactory);
                        }
                    }
                } else {
                    BlockPos master = altar.getMasterBlockPos();
                    onUse(world.getBlockState(master), world, master, player, hand, hit);
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AltarBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ALTAR, (world1, pos, state1, be) -> AltarBlockEntity.tick(world1, pos, state1, be));
    }

    // MULTIBLOCK CHECKERS

    public static void findNearestMasterBlock(World world, BlockPos pos, AltarBlockEntity be, Block[] neighbors) {
        //WILL FILL LATER
    }
}
