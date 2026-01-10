package com.flowpipes.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.flowpipes.util.PipeTier;
import com.flowpipes.blockentity.PumpBlockEntity;

public class PumpBlock extends Block {
	public static final BooleanProperty POWERED = BooleanProperty.of("powered");
	public static final BooleanProperty NORTH = BooleanProperty.of("north");
	public static final BooleanProperty SOUTH = BooleanProperty.of("south");
	public static final BooleanProperty EAST = BooleanProperty.of("east");
	public static final BooleanProperty WEST = BooleanProperty.of("west");
	public static final BooleanProperty UP = BooleanProperty.of("up");
	public static final BooleanProperty DOWN = BooleanProperty.of("down");

	private final PipeTier tier;

	public PumpBlock(PipeTier tier) {
		super(Settings.create()
			.strength(2.0f, 8.0f)
			.nonOpaque());
		this.tier = tier;
		setDefaultState(getDefaultState()
			.with(POWERED, false)
			.with(NORTH, false)
			.with(SOUTH, false)
			.with(EAST, false)
			.with(WEST, false)
			.with(UP, false)
			.with(DOWN, false));
	}

	public PipeTier getTier() {
		return tier;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(POWERED, NORTH, SOUTH, EAST, WEST, UP, DOWN);
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, 
			Block sourceBlock, BlockPos sourcePos, boolean notify) {
		if (world.isClient) return;
		
		boolean powered = world.isReceivingRedstonePower(pos);
		if (powered != state.get(POWERED)) {
			world.setBlockState(pos, state.with(POWERED, powered), 3);
		}
	}
}
