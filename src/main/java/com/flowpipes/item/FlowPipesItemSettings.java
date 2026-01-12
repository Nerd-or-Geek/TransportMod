package com.flowpipes.item;

import net.minecraft.item.Item;

/**
 * Minimal Item.Settings override to provide a safe translation key before the item is registered.
 * Avoids the default null check that requires the registry id to be set during Item construction.
 */
public class FlowPipesItemSettings extends Item.Settings {
	private String translationKey = null;  // set in registry prior to Item construction
	private net.minecraft.util.Identifier modelId = null; // set in registry to the item model id

	public FlowPipesItemSettings() {
		super();
	}

	public FlowPipesItemSettings setCustomTranslationKey(String key) {
		this.translationKey = key;
		return this;
	}

	public FlowPipesItemSettings setCustomModelId(net.minecraft.util.Identifier id) {
		this.modelId = id;
		return this;
	}

	@Override
	public String getTranslationKey() {
		// Return the translation key we set; fall back to a stable placeholder
		return translationKey != null ? translationKey : "item.flowpipes.placeholder";
	}

	@Override
	public net.minecraft.util.Identifier getModelId() {
		// Provide a safe model id during Item construction; default to flowpipes:placeholder
		return modelId != null ? modelId : net.minecraft.util.Identifier.of("flowpipes", "placeholder");
	}
}
