package com.arthur.tetris.window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Display extends JPanel{
	
	private Board board;
	private ScoreboardOld scoreboard;
	public Dimension dimension;
	private javax.swing.JFrame frame;
	private static final Dimension dimensionPart = 
			new Dimension(Board.PIECEWIDTH*com.arthur.tetris.framework.PiecePart.BRICKSIZE, 
					Board.PIECEHEIGHT*com.arthur.tetris.framework.PiecePart.BRICKSIZE);
	//dimensionPart is the main dimension, and so all other dimensions are defined here
	//also as fun as it would be, creating two non-equal parts to a layout would take some time, so that was not used
	private GridLayout layout;
	public static final int numDisplays = 2;
	public static int WIDTH = dimensionPart.width * 2;
	public static int HEIGHT = dimensionPart.height;
	public static final int SPACER = 6;
	public static int boardX = WIDTH - dimensionPart.width, boardY = SPACER;
	public static int scoreX = SPACER, scoreY = SPACER;

	public Display(javax.swing.JFrame frame) {
		
		this.frame = frame;
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		
		/* code to add the keylistener */
		
		//System.out.println("WIDTH and HEIGHT are " + String.valueOf(this.WIDTH) + "," +  this.HEIGHT);
		
		//CardLayout layout
		//contains next, score, and level
		//each should be a board type, to be updated as well
	}
	
	public void start() {
		WIDTH= getWidth();
		HEIGHT= getHeight();
		
		
		scoreboard = new ScoreboardOld();
		scoreboard.setBounds(scoreX, scoreY, dimensionPart.width, dimensionPart.height);
		board = new Board(this);
		board.setBounds(boardX, boardY, dimensionPart.width, dimensionPart.height);
		
		this.layout = new GridLayout(1, 2);
		this.setLayout(layout);
		this.add(scoreboard);
		this.add(board);
		scoreboard.setPreferredSize(dimensionPart);
		scoreboard.setMaximumSize(dimensionPart);
		scoreboard.setMinimumSize(dimensionPart);
		scoreboard.start();
		
		/* code to add the keyevent */
		//score2 = new ScoreboardGBL(new GridBagLayout,);
		//board.setLayout(null);
		//scoreboard.setLayout(null);
		
		
		board.setPreferredSize(dimensionPart);
		board.setMaximumSize(dimensionPart);
		board.setMinimumSize(dimensionPart);
		
		
		
		setVisible(true);
		setBackground(Color.CYAN);
		
		
		board.start();
	}
	
	public void cycleEnd() {
		scoreboard.repaint();
	}
	
	public void closeProgram() {
		Window.closeProgram();
	}
}
