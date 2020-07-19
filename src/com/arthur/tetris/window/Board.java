package com.arthur.tetris.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.arthur.tetris.framework.KeyInput;
import com.arthur.tetris.framework.State;
//import com.arthur.tetris.framework.MouseInput;
import com.arthur.tetris.window.Handler;
import com.arthur.tetris.objects.*;

public class Board extends JPanel{
	
	private boolean running = false;
	// private Thread thread;
	public static int WIDTH, HEIGHT;
	Handler handler;
	Display display;
	private static final boolean verbose = Handler.verbose;
	public static final int PIECEWIDTH = 10;
	public static final int PIECEHEIGHT = 20;
	public static final Color COLOR = Color.BLACK;
	
	private ActionListener timeListen;
	private Timer timer;
	private int initialDelay= 750;
	private int speed = 200; //speed at which things need to move
	
	
	public Board(Display display) {
		this.display = display;
		setBackground(COLOR);
		
		
		
		this.timeListen = new ActionListener() { // keyListener likely implemented here
			
			public void actionPerformed(ActionEvent e) {
				// handles the movement every time the timer pops
				if (verbose)
					System.out.println("an action was performed");
				if (running && handler.getState() == State.alive) {
					if (verbose)
						System.out.println("an action should happen");
					handler.move();
				}
				else {
					;//timer.stop();
				}
			}
		};
		handler = new Handler(this);
		
	}
	
	public void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		this.addKeyListener(new KeyInput(display, this, handler));  // keyListener added here
		this.setFocusable(true); // this needs to have and mostly keep focus to properly implement KeyInput
		setVisible(true);
		
		
		this.timer = new Timer(speed, timeListen);
		timer.setInitialDelay(initialDelay);
		handler.setState(State.paused); //State.paused
		this.requestFocus();
		
		//add creation of objects through create of handler here
		
		//add a menu here eventually menu = new Menu();
		
		//this.addKeyListener(new KeyInput(handler));
		//this.addMouseListener(new MouseInput(handler));
	}
	
	public synchronized void start() {
		init();
		startTimer();
		if (running) {
			return;
		}
		
		running = true;
		// thread = new Thread(this); // old implementation, did not have a single point actions were performed on (timer, movement, etc.)
		// thread.start(); //starts thread to implement run method of game
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(COLOR);
		g.fillRect(0,  0,  getWidth(), getHeight());
				
		//this is the code to create our objects
		handler.render(g);

		g.dispose();
	}

	/* this used to be a thread, so needed a run method. The changes to timer mean this probaly doesn't happen anymore
	public void run() {
		
	}*/
	public void changeMade() {
		repaint();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void stopTimer() {
		timer.stop();
		running = false;
	}
	
	public void startTimer() {
		timer.start();
		running = true;
	}
	
	public void restart() {
		timer.restart();
		running = true;
	}
	
	public void nextPiece() {
		display.cycleEnd();
	}
	
}