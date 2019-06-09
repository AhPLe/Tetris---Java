package com.arthur.tetris.framework;

import java.awt.Color;
import java.awt.Graphics;

import com.arthur.tetris.window.Handler;

public abstract class PiecePart {
	
	public static final int BRICKSIZE = 40; //width and height of a part of each piece
	protected Color color;
	protected PieceType type;
	protected Handler handler;
	protected int x;
	protected int y;

	public PiecePart(int x, int y, Handler handler, PieceType type) {
			this.handler = handler;
			this.type = type;
			this.x = x;
			this.y = y;
	}
	
	public abstract void render(Graphics g);
	
	protected void setx(int x) {
		this.x = x;
	}
	protected void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	public Color getColor() {
		
		return color;
	}
	
	public PieceType getPieceType() {
		return this.type;
	}

}
