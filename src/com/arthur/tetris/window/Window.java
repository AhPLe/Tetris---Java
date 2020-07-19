/*
 * This project was built to be Tetris using some of the framework from 
 * Java Breakout on Udemy
 * it was intended to show some ability with the Java language
 * other projects will likely show more skill and interest, 
 * but this is a functioning Tetris program 
 * 
 * the functions/algorithms for rotating, checking if a piece could be placed
 * and the some parts of the erase method were interesting to create 
 */

package com.arthur.tetris.window;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Window {
	private Dimension dimension;

	private static final int width = Display.WIDTH; //display contains and needs the dimensions for individual dimensions
	//so the main dimension was defined there
	private static final int height = Display.HEIGHT;
	public static final String title = "Tetris";
	private static JFrame frame;
	
	public Window(int w, int h, String title, Display display) {
		
		
		this.dimension = new Dimension(w, h);
		
		
		display.setPreferredSize(dimension);
		display.setMaximumSize(dimension);
		display.setMinimumSize(dimension);
		
		
		frame = new JFrame(title);
		frame.add(display);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		display.start();
	}
	
	public static void main(String[] args) {
		new Window(width, height, title, new Display(frame));
	}
	
	static void closeProgram() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		// from https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
	}
	
}
