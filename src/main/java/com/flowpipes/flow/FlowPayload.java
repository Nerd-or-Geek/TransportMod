package com.flowpipes.flow;

import net.minecraft.util.math.Direction;

/**
 * Represents a payload flowing through pipes.
 * Contains direction, head pressure, and payload data.
 */
public class FlowPayload {
	private Direction direction;
	private float headPressure;
	private Object payload; // Item or Fluid
	private int ticksInPipe;

	public FlowPayload(Direction direction, float headPressure, Object payload) {
		this.direction = direction;
		this.headPressure = headPressure;
		this.payload = payload;
		this.ticksInPipe = 0;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public float getHeadPressure() {
		return headPressure;
	}

	public void setHeadPressure(float headPressure) {
		this.headPressure = headPressure;
	}

	public void consumeHeadPressure(float amount) {
		this.headPressure = Math.max(0, this.headPressure - amount);
	}

	public void addHeadPressure(float amount) {
		this.headPressure += amount;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public int getTicksInPipe() {
		return ticksInPipe;
	}

	public void incrementTicksInPipe() {
		this.ticksInPipe++;
	}

	public void resetTicksInPipe() {
		this.ticksInPipe = 0;
	}
}
