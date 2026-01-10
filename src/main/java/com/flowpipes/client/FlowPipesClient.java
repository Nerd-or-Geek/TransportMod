package com.flowpipes.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenEvents;
import com.flowpipes.client.screen.SorterScreen;
import com.flowpipes.client.render.PipeRenderer;

public class FlowPipesClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		PipeRenderer.registerRenderers();
	}
}
