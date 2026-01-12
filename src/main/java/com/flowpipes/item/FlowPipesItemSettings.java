package com.flowpipes.item;

import net.minecraft.item.Item;

/**
 * Custom Item.Settings that provides a default translation key without calling super
 */
public class FlowPipesItemSettings extends Item.Settings {
	private String translationKey = "item.placeholder";

	public FlowPipesItemSettings() {
		super();
	}

	@Override
	public String getTranslationKey() {
		// Return the translation key we set, bypassing super's null check
		return translationKey;
	}
}
