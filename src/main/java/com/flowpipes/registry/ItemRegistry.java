package com.flowpipes.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.flowpipes.FlowPipesMod;
import com.flowpipes.block.PipeBlocks;
import com.flowpipes.item.FlowPipesItemSettings;

public class ItemRegistry {

	public static void registerBlockItems() {
		// Item Pipes
		registerBlockItem("item_pipe_wood", PipeBlocks.ITEM_PIPE_WOOD);
		registerBlockItem("item_pipe_iron", PipeBlocks.ITEM_PIPE_IRON);
		registerBlockItem("item_pipe_diamond", PipeBlocks.ITEM_PIPE_DIAMOND);

		// Fluid Pipes
		registerBlockItem("fluid_pipe_wood", PipeBlocks.FLUID_PIPE_WOOD);
		registerBlockItem("fluid_pipe_iron", PipeBlocks.FLUID_PIPE_IRON);
		registerBlockItem("fluid_pipe_diamond", PipeBlocks.FLUID_PIPE_DIAMOND);

		// Pumps
		registerBlockItem("pump_wood", PipeBlocks.PUMP_WOOD);
		registerBlockItem("pump_iron", PipeBlocks.PUMP_IRON);
		registerBlockItem("pump_diamond", PipeBlocks.PUMP_DIAMOND);

		// Sorters
		registerBlockItem("sorter_wood", PipeBlocks.SORTER_WOOD);
		registerBlockItem("sorter_iron", PipeBlocks.SORTER_IRON);
		registerBlockItem("sorter_diamond", PipeBlocks.SORTER_DIAMOND);
	}

	private static BlockItem registerBlockItem(String name, net.minecraft.block.Block block) {
		Identifier id = Identifier.of(FlowPipesMod.MOD_ID, name);
		// Use custom settings to provide a translation key during Item construction
		FlowPipesItemSettings settings = new FlowPipesItemSettings()
			.setCustomTranslationKey(block.getTranslationKey())
			// Ensure model id exists during Item construction; points to models/item/<name>.json
			.setCustomModelId(Identifier.of(FlowPipesMod.MOD_ID, name));
		BlockItem item = new BlockItem(block, settings);
		return Registry.register(Registries.ITEM, id, item);
	}
}
