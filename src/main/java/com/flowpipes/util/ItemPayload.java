package com.flowpipes.util;

import net.minecraft.item.ItemStack;

/**
 * Utility for handling item wrapper in flow payloads.
 */
public class ItemPayload {
	private ItemStack stack;
	private int count;

	public ItemPayload(ItemStack stack) {
		this.stack = stack.copy();
		this.count = stack.getCount();
	}

	public ItemStack getStack() {
		return stack;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		stack.setCount(count);
	}

	public ItemStack copy() {
		return stack.copy();
	}
}
