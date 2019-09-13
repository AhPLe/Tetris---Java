package com.arthur.tetris.framework;

import java.awt.Color;

public enum PieceType {
	LINE, RIGHTL, LEFTL, SQUARE, CROSS, RIGHTS, LEFTS;
	
	//first array is the y direction, second array is the x direction
	//this is done to make pieceBlock parsing easier
	public static final boolean[][] LINESHAPE = {{true, true, true, true}};
	public static final boolean[][] RIGHTLSHAPE = {{true, true, true}, {false, false, true}};
	public static final boolean[][] LEFTLSHAPE = {{false, false, true}, {true, true, true}};
	public static final boolean[][] SQUARESHAPE = {{true, true}, {true, true}};
	public static final boolean[][] CROSSSHAPE = {{true, false}, {true, true}, {true, false}};
	public static final boolean[][] RIGHTSSHAPE = {{false, true}, {true, true}, {true, false}};
	public static final boolean[][] LEFTSSHAPE = {{true, false}, {true, true}, {false, true}};
	
	//public static final boolean[][] LINEROTATIONPOINT = {{false, true, false, false}};
	//public static final boolean[][] RIGHTLROTATIONPOINT = {{false, true, false}, {false, false, false}};
	//public static final boolean[][] LEFTLROTATIONPOINT = {{false, false, false}, {false, true, false}};
	//public static final boolean[][] SQUAREROTATIONPOINT = {{true, false}, {false, false}};
	//public static final boolean[][] CROSSROTATIONPOINT = {{false, false}, {true, false}, {false, false}};
	//public static final boolean[][] RIGHTSROTATIONPOINT = {{false, false}, {true, false}, {false, false}};
	//public static final boolean[][] LEFTSROTATIONPOINT = {{false, false}, {true, false}, {false, false}};
	
	public static final int[] LINEROTATIONNODE = {0,1};
	public static final int[] RIGHTLROTATIONNODE = {0,1};
	public static final int[] LEFTLROTATIONNODE = {1,1};
	public static final int[] SQUAREROTATIONNODE = {0,0};
	public static final int[] CROSSROTATIONNODE = {1,0};
	public static final int[] RIGHTSROTATIONNODE = {1,0};
	public static final int[] LEFTSROTATIONNODE = {1,0};
	
	
	public static final Color BROWN = new Color(165, 42, 42);
	
	public static final Color LINECOLOR = Color.RED;
	public static final Color RIGHTLCOLOR = Color.GREEN;
	public static final Color LEFTLCOLOR = Color.BLUE;
	public static final Color SQUARECOLOR = Color.YELLOW;
	public static final Color CROSSCOLOR = Color.MAGENTA;
	public static final Color RIGHTSCOLOR = Color.ORANGE;
	public static final Color LEFTSCOLOR = BROWN;
	
	public static final Object[] LINEVALUES = {LINESHAPE, LINEROTATIONNODE, LINECOLOR};
	public static final Object[] RIGHTLVALUES = {RIGHTLSHAPE, RIGHTLROTATIONNODE, RIGHTLCOLOR};
	public static final Object[] LEFTLVALUES = {LEFTLSHAPE, LEFTLROTATIONNODE, LEFTLCOLOR};
	public static final Object[] SQUAREVALUES = {SQUARESHAPE, SQUAREROTATIONNODE, SQUARECOLOR};
	public static final Object[] CROSSVALUES = {CROSSSHAPE, CROSSROTATIONNODE, CROSSCOLOR};
	public static final Object[] RIGHTSVALUES = {RIGHTSSHAPE, RIGHTSROTATIONNODE, RIGHTSCOLOR};
	public static final Object[] LEFTSVALUES = {LEFTSSHAPE, LEFTSROTATIONNODE, LEFTSCOLOR};
	
	public static Object[]  getShape(PieceType type) { 
		//returns array of type boolean[][], color to make fewer 'switch' calls
		Object[] values_returned = new Object[3];
		switch(type) {
		case LINE:
			values_returned[0] = new boolean[LINESHAPE.length][LINESHAPE[0].length];
			System.arraycopy(LINEVALUES[0], 0, values_returned[0], 0, ((boolean[][])LINEVALUES[0]).length);
			values_returned[1] = new int[LINEROTATIONNODE.length];
			System.arraycopy(LINEVALUES[1], 0, values_returned[1], 0, ((int[])LINEVALUES[1]).length);
			values_returned[2] = LINEVALUES[2];
			return  values_returned;
		case RIGHTL:
			values_returned[0] = new boolean[RIGHTLSHAPE.length][RIGHTLSHAPE[0].length];
			System.arraycopy(RIGHTLVALUES[0], 0, values_returned[0], 0, ((boolean[][])RIGHTLVALUES[0]).length);
			values_returned[1] = new int[RIGHTLROTATIONNODE.length];
			System.arraycopy(RIGHTLVALUES[1], 0, values_returned[1], 0, ((int[])RIGHTLVALUES[1]).length);
			values_returned[2] = RIGHTLVALUES[2];
			return  values_returned;
		case LEFTL:
			values_returned[0] = new boolean[LEFTLSHAPE.length][LEFTLSHAPE[0].length];
			System.arraycopy(LEFTLVALUES[0], 0, values_returned[0], 0, ((boolean[][])LEFTLVALUES[0]).length);
			values_returned[1] = new int[LEFTLROTATIONNODE.length];
			System.arraycopy(LEFTLVALUES[1], 0, values_returned[1], 0, ((int[])LEFTLVALUES[1]).length);
			values_returned[2] = LEFTLVALUES[2];
			return  values_returned;
		case SQUARE:
			values_returned[0] = new boolean[SQUARESHAPE.length][SQUARESHAPE[0].length];
			System.arraycopy(SQUAREVALUES[0], 0, values_returned[0], 0, ((boolean[][])SQUAREVALUES[0]).length);
			values_returned[1] = new int[SQUAREROTATIONNODE.length];
			System.arraycopy(SQUAREVALUES[1], 0, values_returned[1], 0, ((int[])SQUAREVALUES[1]).length);
			values_returned[2] = SQUAREVALUES[2];
			return  values_returned;
		case CROSS:
			values_returned[0] = new boolean[CROSSSHAPE.length][CROSSSHAPE[0].length];
			System.arraycopy(CROSSVALUES[0], 0, values_returned[0], 0, ((boolean[][])CROSSVALUES[0]).length);
			values_returned[1] = new int[CROSSROTATIONNODE.length];
			System.arraycopy(CROSSVALUES[1], 0, values_returned[1], 0, ((int[])CROSSVALUES[1]).length);
			values_returned[2] = CROSSVALUES[2];
			return  values_returned;
		case RIGHTS:
			values_returned[0] = new boolean[RIGHTSSHAPE.length][RIGHTSSHAPE[0].length];
			System.arraycopy(RIGHTSVALUES[0], 0, values_returned[0], 0, ((boolean[][])RIGHTSVALUES[0]).length);
			values_returned[1] = new int[RIGHTSROTATIONNODE.length];
			System.arraycopy(RIGHTSVALUES[1], 0, values_returned[1], 0, ((int[])RIGHTSVALUES[1]).length);
			values_returned[2] = RIGHTSVALUES[2];
			return  values_returned;
		case LEFTS:
			values_returned[0] = new boolean[LEFTSSHAPE.length][LEFTSSHAPE[0].length];
			System.arraycopy(LEFTSVALUES[0], 0, values_returned[0], 0, ((boolean[][])LEFTSVALUES[0]).length);
			values_returned[1] = new int[LEFTSROTATIONNODE.length];
			System.arraycopy(LEFTSVALUES[1], 0, values_returned[1], 0, ((int[])LEFTSVALUES[1]).length);
			values_returned[2] = LEFTSVALUES[2];
			return  values_returned;
			
		default:
			return null; //this shouldn't happen
		
		}
	}
}
