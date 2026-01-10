package com.flowpipes.command;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import com.flowpipes.client.render.PipeRenderer;

public class FlowPipesCommands {

	public static void register() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("flowpipes")
				.then(CommandManager.literal("animations")
					.then(CommandManager.literal("on")
						.executes(context -> {
							PipeRenderer.setAnimationsEnabled(true);
							context.getSource().sendFeedback(
								() -> Text.literal("FlowPipes animations enabled"),
								false);
							return Command.SINGLE_SUCCESS;
						}))
					.then(CommandManager.literal("off")
						.executes(context -> {
							PipeRenderer.setAnimationsEnabled(false);
							context.getSource().sendFeedback(
								() -> Text.literal("FlowPipes animations disabled"),
								false);
							return Command.SINGLE_SUCCESS;
						}))));
		});
	}
}
