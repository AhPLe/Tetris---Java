package com.arthur.tetris.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import com.arthur.tetris.framework.Controls;

public class ScoreboardOld extends JPanel{

	private Dimension dimensionBoard;
	public static int WIDTH;
	public static int HEIGHT;
	public static int gridX = 0;
	public static int gridY = 0;
	public static Color COLOR = Color.LIGHT_GRAY;
	public static Color COLORTEXT = Color.DARK_GRAY;
	
	private int spacing = 10;
	private int columns = 3;
	private int rows = Controls.controls.length + 2;

	private static int borderWidth = 100;
	private static int borderHeight = 50;
	
	public ScoreboardOld() {
		setVisible(true);
		setBackground(COLOR);
		
	}
	
	public void start() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(COLOR);
		g.fillRect(0,  0,  getWidth(), getHeight());
		
		paintDirections(g);
		
	}
	
	public void paintDirections(Graphics g) {
		
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(COLORTEXT);
		
		g.drawString("Tetris", (int)(3*(WIDTH - spacing*2)/(3*columns)), (int)((HEIGHT - 2*spacing)/rows));
		
		Graphics2D g2d = (Graphics2D) g;
		Font font2 = new Font("arial", Font.BOLD, 15);
		g.setFont(font2);
		
		for (int i = 0; i < Controls.controls.length; i++) {
			g.drawString(Controls.getName(Controls.controls[i]), (int)(1*(WIDTH - spacing*2)/(3*columns)), (int)((2 + i)*(HEIGHT - 2*spacing)/rows));
			g.drawString(Controls.getControlString(Controls.controls[i]), (int)(7*(WIDTH - spacing*2)/(3*columns)), (int)((2 + i)*(HEIGHT - 2*spacing)/rows));
		}
		//g.drawString("Tetris", dimensionBoard.width/3,  SPACING);
		
	}
	
	
}