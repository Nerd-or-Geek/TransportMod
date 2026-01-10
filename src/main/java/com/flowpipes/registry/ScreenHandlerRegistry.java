package com.flowpipes.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import com.flowpipes.FlowPipesMod;
import com.flowpipes.screen.SorterScreenHandler;

public class ScreenHandlerRegistry {

	public static ScreenHandlerType<SorterScreenHandler> SORTER_SCREEN_HANDLER;

	public static void registerScreenHandlers() {
		SORTER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
			new Identifier(FlowPipesMod.MOD_ID, "sorter_screen_handler"),
			new ScreenHandlerType<>(SorterScreenHandler::new, FeatureFlags.DEFAULT_SET));
	}
}
