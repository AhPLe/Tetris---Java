package com.arthur.tetris.framework;

import java.awt.Color;

public enum PieceType {
	LINE, RIGHTL, LEFTL, SQUARE, CROSS, RIGHTS, LEFTS;
	
	//first array is y, second array is x
	//this is done to make pieceBlock parsing easier
	public static final boolean[][] LINESHAPE = {{true, true, true, true}};
	public static final boolean[][] RIGHTLSHAPE = {{true, true, true}, {false, false, true}};
	public static final boolean[][] LEFTLSHAPE = {{false, false, true}, {true, true, true}};
	public static final boolean[][] SQUARESHAPE = {{true, true}, {true, true}};
	public static final boolean[][] CROSSSHAPE = {{false, true}, {true, true}, {false, true}};
	public static final boolean[][] RIGHTSSHAPE = {{false, true}, {true, true}, {true, false}};
	public static final boolean[][] LEFTSSHAPE = {{true, false}, {true, true}, {false, true}};
	
	public static final Color BROWN = new Color(165, 42, 42);
	
	public static final Color LINECOLOR = Color.RED;
	public static final Color RIGHTLCOLOR = Color.GREEN;
	public static final Color LEFTLCOLOR = Color.BLUE;
	public static final Color SQUARECOLOR = Color.YELLOW;
	public static final Color CROSSCOLOR = Color.MAGENTA;
	public static final Color RIGHTSCOLOR = Color.ORANGE;
	public static final Color LEFTSCOLOR = BROWN;
	
	public static final Object[] LINEVALUES = {LINESHAPE, LINECOLOR};
	public static final Object[] RIGHTLVALUES = {RIGHTLSHAPE, RIGHTLCOLOR};
	public static final Object[] LEFTLVALUES = {LEFTLSHAPE, LEFTLCOLOR};
	public static final Object[] SQUAREVALUES = {SQUARESHAPE, SQUARECOLOR};
	public static final Object[] CROSSVALUES = {CROSSSHAPE, CROSSCOLOR};
	public static final Object[] RIGHTSVALUES = {RIGHTSSHAPE, RIGHTSCOLOR};
	public static final Object[] LEFTSVALUES = {LEFTSSHAPE, LEFTSCOLOR};
	
	public static Object[]  getShape(PieceType type) { 
		//returns array of type boolean[][], color to make fewer 'switch' calls
		switch(type) {
		case LINE:
			return  LINEVALUES;
		case RIGHTL:
			return RIGHTLVALUES;
		case LEFTL:
			return LEFTLVALUES;
		case SQUARE:
			return SQUAREVALUES;
		case CROSS:
			return CROSSVALUES;
		case RIGHTS:
			return RIGHTSVALUES;
		case LEFTS:
			return LEFTSVALUES;
			
		default:
			return null; //this shouldn't happen
		
		}
	}
}
