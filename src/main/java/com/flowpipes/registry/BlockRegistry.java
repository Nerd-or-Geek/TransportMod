package com.flowpipes.registry;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import com.flowpipes.FlowPipesMod;
import com.flowpipes.block.PipeBlocks;
import com.flowpipes.block.ItemPipeBlock;
import com.flowpipes.block.FluidPipeBlock;
import com.flowpipes.block.PumpBlock;
import com.flowpipes.block.SorterBlock;
import com.flowpipes.util.PipeTier;

public class BlockRegistry {

	public static void registerBlocks() {
		// Item Pipes
		PipeBlocks.ITEM_PIPE_WOOD = registerBlock("item_pipe_wood", 
			new ItemPipeBlock(createSettings("item_pipe_wood"), PipeTier.WOOD));
		PipeBlocks.ITEM_PIPE_IRON = registerBlock("item_pipe_iron", 
			new ItemPipeBlock(createSettings("item_pipe_iron"), PipeTier.IRON));
		PipeBlocks.ITEM_PIPE_DIAMOND = registerBlock("item_pipe_diamond", 
			new ItemPipeBlock(createSettings("item_pipe_diamond"), PipeTier.DIAMOND));

		// Fluid Pipes
		PipeBlocks.FLUID_PIPE_WOOD = registerBlock("fluid_pipe_wood", 
			new FluidPipeBlock(createSettings("fluid_pipe_wood"), PipeTier.WOOD));
		PipeBlocks.FLUID_PIPE_IRON = registerBlock("fluid_pipe_iron", 
			new FluidPipeBlock(createSettings("fluid_pipe_iron"), PipeTier.IRON));
		PipeBlocks.FLUID_PIPE_DIAMOND = registerBlock("fluid_pipe_diamond", 
			new FluidPipeBlock(createSettings("fluid_pipe_diamond"), PipeTier.DIAMOND));

		// Pumps
		PipeBlocks.PUMP_WOOD = registerBlock("pump_wood", 
			new PumpBlock(createSettings("pump_wood"), PipeTier.WOOD));
		PipeBlocks.PUMP_IRON = registerBlock("pump_iron", 
			new PumpBlock(createSettings("pump_iron"), PipeTier.IRON));
		PipeBlocks.PUMP_DIAMOND = registerBlock("pump_diamond", 
			new PumpBlock(createSettings("pump_diamond"), PipeTier.DIAMOND));

		// Sorters
		PipeBlocks.SORTER_WOOD = registerBlock("sorter_wood", 
			new SorterBlock(createSettings("sorter_wood"), PipeTier.WOOD));
		PipeBlocks.SORTER_IRON = registerBlock("sorter_iron", 
			new SorterBlock(createSettings("sorter_iron"), PipeTier.IRON));
		PipeBlocks.SORTER_DIAMOND = registerBlock("sorter_diamond", 
			new SorterBlock(createSettings("sorter_diamond"), PipeTier.DIAMOND));
	}

	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, 
			Identifier.of(FlowPipesMod.MOD_ID, name), block);
	}

	private static Block.Settings createSettings(String name) {
		Identifier id = Identifier.of(FlowPipesMod.MOD_ID, name);
		RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
		return Block.Settings.create().registryKey(key);
	}
}
