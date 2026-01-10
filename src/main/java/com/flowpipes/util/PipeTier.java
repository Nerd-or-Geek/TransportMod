package com.flowpipes.util;

public enum PipeTier {
	WOOD(1.0f, "wood"),
	IRON(1.5f, "iron"),
	DIAMOND(2.5f, "diamond");

	private final float speedMultiplier;
	private final String name;

	PipeTier(float speedMultiplier, String name) {
		this.speedMultiplier = speedMultiplier;
		this.name = name;
	}

	public float getSpeedMultiplier() {
		return speedMultiplier;
	}

	public String getDisplayName() {
		return name;
	}
}
