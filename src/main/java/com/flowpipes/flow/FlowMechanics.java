package com.flowpipes.flow;

import net.minecraft.util.math.Direction;
import com.flowpipes.util.PipeTier;

/**
 * Flow mechanics calculator using head pressure system.
 * Downward flow increases head, sideways consumes head, upward requires head.
 */
public class FlowMechanics {

	// Head pressure costs
	private static final float SIDEWAYS_COST = 0.1f;
	private static final float UPWARD_COST = 0.3f;
	private static final float DOWNWARD_GAIN = 0.2f;

	/**
	 * Calculate head pressure change when flowing in a direction.
	 */
	public static float calculateHeadChange(Direction direction) {
		if (direction.getAxis().isVertical()) {
			if (direction == Direction.DOWN) {
				return DOWNWARD_GAIN; // Gain head when flowing down
			} else {
				return -UPWARD_COST; // Lose head when flowing up
			}
		} else {
			return -SIDEWAYS_COST; // Lose head when flowing sideways
		}
	}

	/**
	 * Check if a payload can flow in the given direction.
	 */
	public static boolean canFlow(FlowPayload payload, Direction direction) {
		float headCost = -calculateHeadChange(direction);
		if (headCost > 0) {
			// Cost (upward or sideways)
			return payload.getHeadPressure() >= headCost;
		}
		// Downward flow always possible
		return true;
	}

	/**
	 * Apply flow mechanics when moving to next pipe.
	 */
	public static void applyFlow(FlowPayload payload, Direction direction) {
		float headChange = calculateHeadChange(direction);
		payload.setHeadPressure(Math.max(0, payload.getHeadPressure() + headChange));
		payload.setDirection(direction);
	}

	/**
	 * Get pump boost based on tier and direction.
	 */
	public static float getPumpBoost(PipeTier tier) {
		return switch (tier) {
			case WOOD -> 0.5f;
			case IRON -> 1.0f;
			case DIAMOND -> 2.0f;
		};
	}

	/**
	 * Calculate items per tick based on tier and pipe size multiplier.
	 */
	public static float getItemsPerTick(PipeTier pumpTier, PipeTier pipeTier, float sizeMultiplier) {
		// Base: ~1 stack per second = 64 items/sec = ~3.2 items/tick (1 tick = 0.05s)
		float baseRate = 3.2f;
		
		// Min of pump and pipe tier speed
		float tierSpeed = Math.min(pumpTier.getSpeedMultiplier(), pipeTier.getSpeedMultiplier());
		
		return baseRate * tierSpeed * sizeMultiplier;
	}
}
