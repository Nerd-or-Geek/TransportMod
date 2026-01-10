package com.flowpipes.util;

public enum PipeSize {
	SIZE_1X1(1, 1.0f, "1x1"),
	SIZE_2X2(2, 2.0f, "2x2"),
	SIZE_3X3(3, 3.0f, "3x3"),
	SIZE_4X4(4, 4.0f, "4x4");

	private final int sizeLevel;
	private final float throughputMultiplier;
	private final String displayName;

	PipeSize(int sizeLevel, float throughputMultiplier, String displayName) {
		this.sizeLevel = sizeLevel;
		this.throughputMultiplier = throughputMultiplier;
		this.displayName = displayName;
	}

	public int getSizeLevel() {
		return sizeLevel;
	}

	public float getThroughputMultiplier() {
		return throughputMultiplier;
	}

	public String getDisplayName() {
		return displayName;
	}
}
