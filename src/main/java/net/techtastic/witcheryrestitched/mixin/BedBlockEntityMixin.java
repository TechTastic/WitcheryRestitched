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

	private boolean hasBeenUsed = false;
	private UUID userUuid = null;
	private String userName = "";

	// FROM INTERFACE
	@Override
	public boolean wasUsed() {
		return this.hasBeenUsed;
	}

	@Override
	public void setUsed(boolean sleptIn) {
		this.hasBeenUsed = sleptIn;
	}

	@Override
	public UUID getUserUuid() {
		return this.userUuid;
	}

	@Override
	public void setUserUuid(UUID sleptUuid) {
		this.userUuid = sleptUuid;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setUserName(String sleptName) {
		this.userName = sleptName;
	}

	// INJECTIONS

	@Override
	public void WitcheryRestitched$readNbt(NbtCompound nbt, CallbackInfo info) {
		this.hasBeenUsed = nbt.getBoolean("witcheryrestitched:hasbeenused");
		this.userUuid = nbt.getUuid("witcheryrestitched:useruuid");
		this.userName = nbt.getString("witcheryrestitched:username");
	}

	@Override
	public void WitcheryRestitched$writeNbt(NbtCompound nbt, CallbackInfo info) {
		nbt.putBoolean("witcheryrestitched:hasbeenused", this.hasBeenUsed);
		nbt.putUuid("witcheryrestitched:useruuid", this.userUuid);
		nbt.putString("witcheryrestitched:username", this.userName);
	}

	public NbtCompound WitcheryRestitched$createNbt() {
		CallbackInfo info = new CallbackInfo("createNbt", false);
		NbtCompound nbtCompound = new NbtCompound();
		this.WitcheryRestitched$writeNbt(nbtCompound, info);
		return nbtCompound;
	}

	public void WitcheryRestitched$toInitialChunkDataNbt(CallbackInfoReturnable<NbtCompound> cir) {
		cir.setReturnValue(WitcheryRestitched$createNbt());
	}
}
