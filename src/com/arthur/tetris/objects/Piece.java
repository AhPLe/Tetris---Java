package com.arthur.tetris.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.arthur.tetris.framework.PieceType;
import com.arthur.tetris.window.Board;
import com.arthur.tetris.window.Handler;
import com.arthur.tetris.framework.PiecePart;

public class Piece extends PiecePart{
	//the parts of each manipulatable piece
	
	boolean[][] shape;
	int[] rotation_node;
	// for shape, first is y and second is x - done for easier parsing of pieceBlock
	private int arrX;
	private int arrY;
	private PieceBlock pieceBlock;
//	public final PieceType type;
	
	public Piece(int x, int y, PieceType type, Handler handler, PieceBlock pieceBlock) {
			super(x, y, handler, type);
			arrX = (int)(x/BRICKSIZE);
			arrY = (int)(y/BRICKSIZE);
			this.pieceBlock = pieceBlock;
			setPiece(type);
	}
	
	public void paintPiece(Graphics g, Color c) {
		g.setColor(c);
		
		for(int i = 0; i < shape.length; i++) {
			for(int j = 0; j < shape[i].length; j++) {
				if (shape[i][j]) {
					g.fillRect(x + (j*BRICKSIZE), y + (i*BRICKSIZE), BRICKSIZE, BRICKSIZE);
				}
			}
		}
	}

	public void render(Graphics g) {
		//sets the piece to the color of the piece
		paintPiece(g, color);
	}
	
	public void erase(Graphics g) {
		//sets the piece parts to the color of the board
		paintPiece(g, Board.COLOR);
	}
	
	private void setPiece(PieceType type) { //sets pieces from their bounds to their color
		
		Object[] pieceValues = PieceType.getShape(type);
		this.shape = (boolean[][]) pieceValues[0];
		this.rotation_node = (int[])pieceValues[1];
		this.color = (Color) pieceValues[2];
		
	}
	
	public boolean moveTime() {
		//pieces move down one block down due to timing or user key
		//this is the only method that required a boolean return value
		if (!(pieceBlock.isBlocked(x, y + BRICKSIZE, this.shape))){
			y += BRICKSIZE;
			arrY += 1;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void moveRight() {
		if (!(pieceBlock.isBlocked(x + BRICKSIZE, y, this.shape))){
			x += BRICKSIZE;
			arrX += 1;
		}
	}
	
	public void moveLeft() {
		if (!(pieceBlock.isBlocked(x - BRICKSIZE, y, this.shape))){
			x -= BRICKSIZE;
			arrX -= 1;
		}
	}
	
	public void moveDown() {
		arrY = pieceBlock.lowestPoint(this);
		y = arrY *BRICKSIZE;
		
	}
	
	public void rotateClockwise() {
		boolean[][] shapeNew = new boolean[shape[0].length][shape.length];
		//boolean[][] rotationPointNew = new boolean[rotation_point[0].length][rotation_point.length];
		// does inputting a new x and y work?
		int x_new, y_new;
		int y_node = rotation_node[0];
		int x_node = rotation_node[1];
		int x_node_new = shape.length - 1 - y_node;
		int y_node_new = x_node;
		//x_new = x - ((int)Math.ceil((double)shape[0].length/2) - 1)*BRICKSIZE;
		//y_new = y - ((int)Math.ceil((double)shape.length/2) - 1)*BRICKSIZE;

		for(int i = 0; i < shape.length; i++) {
			for(int j = 0; j < shape[i].length; j++) {
				shapeNew[j][i] = shape[shape.length - 1 - i][j];
				//rotationPointNew[j][i] = rotation_point[rotation_point.length - 1 - i][j];
			}
		}
		x_new = x - (x_node_new - x_node)*BRICKSIZE;
		y_new = y - (y_node_new - y_node)*BRICKSIZE;
		if (!(pieceBlock.isBlocked(x_new, y_new, shapeNew))){
			this.shape = shapeNew;
			//this.rotation_point = rotationPointNew;
			this.rotation_node[0] = y_node_new;
			this.rotation_node[1] = x_node_new;
			x=x_new;
			y=y_new;
		}
	}

	public void rotateCounterClockwise() {
		//I realize this only works for the upper left side of a piece for now, but if the rest of the game works
		//then this can be a slow project for later
		//ideas: label a midpoint for each PieceType, then use that for Piece.render
		// ALL THAT'S LEFT IS MODIFYING X_NEW AND Y_NEW TO INCLUDE THE ROTATIONPOINT, THEN IT SHOULD WORK
		boolean[][] shapeNew = new boolean[shape[0].length][shape.length];
		//boolean[][] rotationPointNew = new boolean[rotation_point[0].length][rotation_point.length];
		int x_new, y_new;
		int y_node = rotation_node[0];
		int x_node = rotation_node[1];
		int x_node_new = y_node;
		int y_node_new = shape[0].length - 1 - x_node;
		for(int i = 0; i < shape.length; i++) {
			for(int j = 0; j < shape[i].length; j++) {
				shapeNew[j][i] = shape[i][shape[i].length - 1 - j];
				//rotationPointNew[j][i] = rotation_point[i][rotation_point[i].length - 1 - j];
			}
		}
		x_new = x - (x_node_new - x_node)*BRICKSIZE;
		y_new = y - (y_node_new - y_node)*BRICKSIZE;
		if (!(pieceBlock.isBlocked(x_new, y_new, shapeNew))){
			this.shape = shapeNew;
			//this.rotation_point = rotationPointNew;
			this.rotation_node[0] = y_node_new;
			this.rotation_node[1] = x_node_new;
			x=x_new;
			y=y_new;
		}
	}
	
	public boolean[][] getShape(){
		return shape;
	}
	
}
