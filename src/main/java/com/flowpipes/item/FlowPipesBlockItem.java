package com.flowpipes.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

/**
 * Custom BlockItem that doesn't require translation key to be set during construction
 */
public class FlowPipesBlockItem extends BlockItem {
	public FlowPipesBlockItem(Block block) {
		super(block, new Item.Settings());
	}
}
