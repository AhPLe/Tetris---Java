package com.arthur.tetris.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.arthur.tetris.framework.State;
import com.arthur.tetris.window.Board;
import com.arthur.tetris.window.Handler;
import com.arthur.tetris.window.Display;

public class KeyInput extends KeyAdapter {
	
	private Board board;
	private Handler handler;
	private Display display;
	private static final boolean verbose = com.arthur.tetris.window.Handler.verbose;
	
	public KeyInput(Display display, Board board, Handler handler) {
		this.board = board;
		this.handler = handler;
		this.display = display;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println("pressed"); //System.out.print("pressed" + String.valueOf(key));
		if (handler.getState() != State.paused && handler.getState() != State.dead) {
			if(key == KeyEvent.VK_LEFT) {
				handler.moveLeft();
			}
			else if(key == KeyEvent.VK_RIGHT) {
				handler.moveRight();
			}
			else if(key == KeyEvent.VK_UP) {
				handler.rotateClockwise();
			}
			else if(key == KeyEvent.VK_DOWN) {
				handler.rotateCounterClockwise();
			}
			else if(key == KeyEvent.VK_Z) {
				handler.move();
			}
			else if(key == KeyEvent.VK_SPACE) {
				handler.moveDown();
			}

		}
		

		if(key == KeyEvent.VK_P) {
			if (verbose)
				System.out.println("State should change, p was pressed");
			if (handler.getState() == State.alive) {
				handler.setState(State.paused);
			}
			else if (handler.getState() == State.paused || handler.getState() == State.dead) {
				handler.setState(State.alive);
			}
		}
		else if(key == KeyEvent.VK_Q) {
			display.closeProgram();
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//this likely stops the movement from happening and may or may not allow a key to continue being pressed
		//no bricks need to stop moving, the temporarily move anyway
		if(key == KeyEvent.VK_LEFT) {
			
		}
		if(key == KeyEvent.VK_RIGHT) {
			
		}
	}

}
