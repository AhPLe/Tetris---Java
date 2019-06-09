package com.arthur.tetris.framework;

public class Controls {
	public static final String[] controls = {"left", "right", "clockwise", "counterclockwise", "down", "drop", "pause"};
	
	public static final String AddArrow = " arrow";
	public static final String LeftTrigger = "Left";
	public static final String RightTrigger = "Right";
	public static final String ClockwiseTrigger = "Up";
	public static final String CounterclockwiseTrigger = "Down";
	public static final String DownTrigger = "Z";
	public static final String DropTrigger = "Space";
	public static final String PauseTrigger = "P";
	
	public static final String LeftName = "Move Left";
	public static final String RightName = "Move Right";
	public static final String ClockwiseName = "Rotate Clockwise";
	public static final String CounterclockwiseName = "Rotate Counterclockwise";
	public static final String DownName = "Down";
	public static final String DropName = "Drop";
	public static final String PauseName = "Pause";
	
	
	public static String getControlString(String test) {
		String toReturn = "";
		switch (test) {
		case "left":
			return LeftTrigger + AddArrow;
		case "right":
			return RightTrigger + AddArrow;
		case "clockwise":
			return ClockwiseTrigger + AddArrow;
		case "counterclockwise":
			return CounterclockwiseTrigger + AddArrow;
		case "down":
			return DownTrigger;
		case "drop":
			return DropTrigger;
		case "pause":
			return PauseTrigger;
		}
		return toReturn;
	}
	
	public static String getName(String test) {
		String toReturn = "";
		switch (test) {
		case "left":
			return LeftName;
		case "right":
			return RightName;
		case "clockwise":
			return ClockwiseName;
		case "counterclockwise":
			return CounterclockwiseName;
		case "down":
			return DownName;
		case "drop":
			return DropName;
		case "pause":
			return PauseName;
		}
		return toReturn;
	}
}
