package net.techtastic.witcheryrestitched.mixin;

import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.techtastic.witcheryrestitched.util.ModdedBedBlockInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(BedBlockEntity.class)
public class BedBlockEntityMixin extends BlockEntityMixin implements ModdedBedBlockInterface {

	// VARIABLES

	private boolean WitcheryRestitched$hasBeenUsed = false;
	private UUID WitcheryRestitched$userUuid = UUID.randomUUID();
	private String WitcheryRestitched$userName = "";

	// FROM INTERFACE
	@Override
	public boolean WitcheryRestitched$wasUsed() {
		return this.WitcheryRestitched$hasBeenUsed;
	}

	@Override
	public void WitcheryRestitched$setUsed(boolean sleptIn) {
		this.WitcheryRestitched$hasBeenUsed = sleptIn;
	}

	@Override
	public UUID WitcheryRestitched$getUserUuid() {
		return this.WitcheryRestitched$userUuid;
	}

	@Override
	public void WitcheryRestitched$setUserUuid(UUID sleptUuid) {
		this.WitcheryRestitched$userUuid = sleptUuid;
	}

	@Override
	public String WitcheryRestitched$getUserName() {
		return this.WitcheryRestitched$userName;
	}

	@Override
	public void WitcheryRestitched$setUserName(String sleptName) {
		this.WitcheryRestitched$userName = sleptName;
	}

	// INJECTIONS

	@Override
	protected void WitcheryRestitched$readCustomNbt(NbtCompound nbt, CallbackInfo info) {
		this.WitcheryRestitched$hasBeenUsed = nbt.getBoolean("witcheryrestitched:hasbeenused");
		this.WitcheryRestitched$userUuid = nbt.getUuid("witcheryrestitched:useruuid");
		this.WitcheryRestitched$userName = nbt.getString("witcheryrestitched:username");
	}

	@Override
	protected void WitcheryRestitched$writeCustomNbt(NbtCompound nbt, CallbackInfo info) {
		nbt.putBoolean("witcheryrestitched:hasbeenused", this.WitcheryRestitched$hasBeenUsed);
		nbt.putUuid("witcheryrestitched:useruuid", this.WitcheryRestitched$userUuid);
		nbt.putString("witcheryrestitched:username", this.WitcheryRestitched$userName);
	}

	protected void WitcheryRestitched$toInitialChunkDataCustomNbt(CallbackInfoReturnable<NbtCompound> cir) {
		this.WitcheryRestitched$writeCustomNbt(cir.getReturnValue(), null);
	}
}
