package com.arthur.tetris.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.arthur.tetris.framework.KeyInput;
import com.arthur.tetris.framework.State;
//import com.arthur.tetris.framework.MouseInput;
import com.arthur.tetris.window.Handler;
import com.arthur.tetris.objects.*;

public class Board extends JPanel implements Runnable{
	
	private boolean running = false;
	private Thread thread;
	public static int WIDTH, HEIGHT;
	Handler handler;
	public static final int PIECEWIDTH = 10;
	public static final int PIECEHEIGHT = 20;
	public static final Color COLOR = Color.BLACK;
	
	private ActionListener timeListen;
	public Timer timer;
	private int initialPause = 2000;
	private int speed = 500; //speed at which things need to move
	
	
	public Board() {
		setVisible(true);
		setBackground(COLOR);
		
		this.timeListen = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (running && handler.getState() == State.alive) {
					handler.move();
					timer.restart();
				}
				else {
					timer.stop();
				}
			}
		};
		handler = new Handler(this);
	}
	
	public void init() {
		setFocusable(true);
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		this.addKeyListener(new KeyInput(this, handler));
		
		this.timer = new Timer(speed, timeListen);
		timer.setInitialDelay(initialPause);
		handler.setState(State.paused);
		//add creation of objects through create of handler here
		
		//add a menu here eventually menu = new Menu();
		
		//this.addKeyListener(new KeyInput(handler));
		//this.addMouseListener(new MouseInput(handler));
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start(); //starts thread to implement run method of game
		
	}
	
	public void running() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(COLOR);
		g.fillRect(0,  0,  getWidth(), getHeight());
				
		//this is the code to create our objects
		handler.render(g);

		g.dispose();
	}

	
	public void run() {
		init();
		
		while(running) {
			//implement game in this loop
			//game loop
			
			//handler.move(); implemented through timer
			
			repaint();
			
			try {
				thread.sleep(23);//was 23, I'm not quite sure why if changed, 23 was the original number
			}
			catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void startTimer() {
		timer.restart();
	}
}