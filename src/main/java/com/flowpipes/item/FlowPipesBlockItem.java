package com.flowpipes.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

/**
 * Custom BlockItem that uses FlowPipesItemSettings for proper initialization
 */
public class FlowPipesBlockItem extends BlockItem {
	public FlowPipesBlockItem(Block block) {
		super(block, new FlowPipesItemSettings().setCustomTranslationKey(block.getTranslationKey()));
	}
}
