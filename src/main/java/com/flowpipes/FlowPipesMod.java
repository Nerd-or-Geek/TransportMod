package com.flowpipes;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.flowpipes.registry.BlockRegistry;
import com.flowpipes.registry.ItemRegistry;
import com.flowpipes.registry.BlockEntityRegistry;
import com.flowpipes.registry.ScreenHandlerRegistry;
import com.flowpipes.registry.ItemGroupRegistry;
import com.flowpipes.command.FlowPipesCommands;

public class FlowPipesMod implements ModInitializer {
	public static final String MOD_ID = "flowpipes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("FlowPipes initializing...");
		
		BlockRegistry.registerBlocks();
		ItemRegistry.registerBlockItems();
		ItemGroupRegistry.registerItemGroups();
		BlockEntityRegistry.registerBlockEntities();
		ScreenHandlerRegistry.registerScreenHandlers();
		FlowPipesCommands.register();
		
		LOGGER.info("FlowPipes loaded successfully!");
	}
}
