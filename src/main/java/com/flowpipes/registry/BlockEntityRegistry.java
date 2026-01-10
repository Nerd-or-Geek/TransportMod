package com.flowpipes.registry;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.flowpipes.FlowPipesMod;
import com.flowpipes.blockentity.PipeBlockEntity;
import com.flowpipes.blockentity.SorterBlockEntity;
import com.flowpipes.blockentity.PumpBlockEntity;
import com.flowpipes.util.PipeTier;
import com.flowpipes.block.PipeBlocks;

public class BlockEntityRegistry {

	public static BlockEntityType<PipeBlockEntity> ITEM_PIPE_ENTITY;
	public static BlockEntityType<PipeBlockEntity> FLUID_PIPE_ENTITY;
	public static BlockEntityType<SorterBlockEntity> SORTER_ENTITY;
	public static BlockEntityType<PumpBlockEntity> PUMP_ENTITY;

	public static void registerBlockEntities() {
		// Register pipe entities
		ITEM_PIPE_ENTITY = registerBlockEntity("item_pipe_entity",
			BlockEntityType.Builder.create(
				(pos, state) -> new PipeBlockEntity(ITEM_PIPE_ENTITY, pos, state, PipeTier.WOOD),
				PipeBlocks.ITEM_PIPE_WOOD, PipeBlocks.ITEM_PIPE_IRON, PipeBlocks.ITEM_PIPE_DIAMOND)
				.build(null));

		FLUID_PIPE_ENTITY = registerBlockEntity("fluid_pipe_entity",
			BlockEntityType.Builder.create(
				(pos, state) -> new PipeBlockEntity(FLUID_PIPE_ENTITY, pos, state, PipeTier.WOOD),
				PipeBlocks.FLUID_PIPE_WOOD, PipeBlocks.FLUID_PIPE_IRON, PipeBlocks.FLUID_PIPE_DIAMOND)
				.build(null));

		// Register sorter entity
		SORTER_ENTITY = registerBlockEntity("sorter_entity",
			BlockEntityType.Builder.create((pos, state) -> new SorterBlockEntity(pos, state, PipeTier.WOOD),
				PipeBlocks.SORTER_WOOD, PipeBlocks.SORTER_IRON, PipeBlocks.SORTER_DIAMOND)
				.build(null));

		// Register pump entity
		PUMP_ENTITY = registerBlockEntity("pump_entity",
			BlockEntityType.Builder.create((pos, state) -> new PumpBlockEntity(pos, state, PipeTier.WOOD),
				PipeBlocks.PUMP_WOOD, PipeBlocks.PUMP_IRON, PipeBlocks.PUMP_DIAMOND)
				.build(null));
	}

	private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType<T> type) {
		return Registry.register(Registries.BLOCK_ENTITY_TYPE, 
			new Identifier(FlowPipesMod.MOD_ID, name), type);
	}
}
