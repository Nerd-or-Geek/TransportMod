package com.flowpipes.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import com.flowpipes.FlowPipesMod;
import com.flowpipes.block.PipeBlocks;

public class ItemGroupRegistry {

	public static ItemGroup FLOWPIPES_TAB;

	public static void registerItemGroups() {
		FLOWPIPES_TAB = FabricItemGroup.builder()
			.displayName(Text.literal("FlowPipes"))
			.icon(() -> new ItemStack(PipeBlocks.ITEM_PIPE_WOOD))
			.entries((context, entries) -> {
				// Item Pipes
				entries.add(PipeBlocks.ITEM_PIPE_WOOD.asItem());
				entries.add(PipeBlocks.ITEM_PIPE_IRON.asItem());
				entries.add(PipeBlocks.ITEM_PIPE_DIAMOND.asItem());

				// Fluid Pipes
				entries.add(PipeBlocks.FLUID_PIPE_WOOD.asItem());
				entries.add(PipeBlocks.FLUID_PIPE_IRON.asItem());
				entries.add(PipeBlocks.FLUID_PIPE_DIAMOND.asItem());

				// Pumps
				entries.add(PipeBlocks.PUMP_WOOD.asItem());
				entries.add(PipeBlocks.PUMP_IRON.asItem());
				entries.add(PipeBlocks.PUMP_DIAMOND.asItem());

				// Sorters
				entries.add(PipeBlocks.SORTER_WOOD.asItem());
				entries.add(PipeBlocks.SORTER_IRON.asItem());
				entries.add(PipeBlocks.SORTER_DIAMOND.asItem());
			})
			.build();

		Registry.register(Registries.ITEM_GROUP, 
			Identifier.of(FlowPipesMod.MOD_ID, "flowpipes_tab"), FLOWPIPES_TAB);
	}
}
