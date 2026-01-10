package com.flowpipes.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import com.flowpipes.util.PipeTier;
import com.flowpipes.screen.SorterScreenHandler;

public class SorterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
	private PipeTier tier;
	private boolean isWhitelist = true;

	public SorterBlockEntity(BlockPos pos, BlockState state, PipeTier tier) {
		super(null, pos, state);
		this.tier = tier;
	}

	public PipeTier getTier() {
		return tier;
	}

	public boolean isWhitelist() {
		return isWhitelist;
	}

	public void setWhitelist(boolean whitelist) {
		isWhitelist = whitelist;
	}

	@Override
	public int size() {
		return inventory.size();
	}

	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}

	@Override
	public ItemStack getStack(int slot) {
		return inventory.get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		return Inventories.removeStack(inventory, slot, amount);
	}

	@Override
	public ItemStack removeStack(int slot) {
		return Inventories.removeStack(inventory, slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
		inventory.set(slot, stack);
		if (stack.getCount() > getMaxCountPerStack()) {
			stack.setCount(getMaxCountPerStack());
		}
	}

	@Override
	public boolean canPlayerUse(net.minecraft.entity.player.PlayerEntity player) {
		return true;
	}

	@Override
	public void clear() {
		inventory.clear();
	}

	public static void tick(World world, BlockPos pos, BlockState state, SorterBlockEntity entity) {
		if (world.isClient) return;
	}

	@Override
	public Text getDisplayName() {
		return Text.literal("Sorter");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, net.minecraft.entity.player.PlayerInventory inventory, net.minecraft.entity.player.PlayerEntity player) {
		return new SorterScreenHandler(syncId, inventory, this);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putString("tier", tier.getDisplayName());
		nbt.putBoolean("isWhitelist", isWhitelist);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		isWhitelist = nbt.getBoolean("isWhitelist");
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
