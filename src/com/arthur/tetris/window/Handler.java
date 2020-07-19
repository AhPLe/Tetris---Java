package com.arthur.tetris.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

//import com.arthur.tetris.framework.MoveListener;
import com.arthur.tetris.framework.PieceType;
import com.arthur.tetris.framework.State;
import com.arthur.tetris.objects.PieceBoard;
import com.arthur.tetris.objects.Piece;
import java.util.Random;

public class Handler {
	
	Piece curPiece;

	private Board board;
	private PieceBoard pieceBoard;
	private Random random;
	private State state;
	private boolean deadStart = false;
	
	public static final boolean verbose = false;
	private static final Color COLORTEXT = Color.WHITE;
	private static final String PAUSED = "Paused";
	private static final int ROOMTEXTPAUSED = 80;
	private static final String GAMEEND = "End of Game";
	private static final int ROOMTEXTGAMEEND = 150;
	private static final String PRESSP = "Press P";
	private static final int ROOMTEXTPRESSP = 25;
	private static final int SIZETEXTHEADING = 50;
	private static final int SIZETEXTPRESSP = 20;
	
	public Handler(Board board) {
		this.board = board;
		this.pieceBoard = new PieceBoard();
		random = new Random();
		this.state = State.dead;
		newPiece();
		changeMade();
		

	}
	
	public void newPiece() {
		board.nextPiece();
		int nextPieceValue = random.nextInt(PieceType.values().length);
		if (verbose)
			System.out.println("A new piece is being made");
		curPiece = new Piece(((int)(Board.PIECEWIDTH/2)-1)*Piece.BRICKSIZE, 0, 
				PieceType.values()[nextPieceValue] , this, pieceBoard);
		if (pieceBoard.isBlocked(((int)(Board.PIECEWIDTH/2)-1)*Piece.BRICKSIZE, 0, curPiece.getShape())){
			setState(State.dead);
		}
		//the random works, pieceType needs to be fleshed out and implemented
		System.gc(); //I realize this basically does nothing, but for now I'll put it in
		//in case garbage collection ever becomes an issue and should be searched for
	}
	
	public void render(Graphics g) {
		//if doing this with backfill instead, this and board's update process will need to change
		pieceBoard.render(g);
		curPiece.render(g);
		//if (getState() != State.alive) {
			if (state == State.paused) {
				Font font = new Font("arial", Font.BOLD, SIZETEXTHEADING);
				g.setFont(font);
				g.setColor(COLORTEXT);
			
				g.drawString(PAUSED, (int)(Board.WIDTH/2 - ROOMTEXTPAUSED), (int)(Board.HEIGHT/3));
			
				Font font2 = new Font("arial", Font.BOLD, SIZETEXTPRESSP);
				g.setFont(font2);
				g.setColor(COLORTEXT);
			
				g.drawString(PRESSP, (int)(Board.WIDTH/2 - ROOMTEXTPRESSP), (int)(Board.HEIGHT*2/3));
			}
			else if (state == State.dead){
				Font font = new Font("arial", Font.BOLD, SIZETEXTHEADING);
				g.setFont(font);
				g.setColor(COLORTEXT);
			
				g.drawString(GAMEEND, (int)(Board.WIDTH/2 - ROOMTEXTGAMEEND), (int)(Board.HEIGHT/3));
			
				Font font2 = new Font("arial", Font.BOLD, SIZETEXTPRESSP);
				g.setFont(font2);
				g.setColor(COLORTEXT);
			
				g.drawString(PRESSP, (int)(Board.WIDTH/2 - ROOMTEXTPRESSP), (int)(Board.HEIGHT*2/3));
			
			}
		//}
	}
	
	public void restart() {
		deadStart = false;
		pieceBoard = new PieceBoard();
		System.gc();
		board.restart();
		newPiece();
	}
	
	public void move() {
		if (!curPiece.moveOne()){
			pieceBoard.addPiece(curPiece); 	// the piece was blocked
			newPiece();						// since it's only moving down one a new piece is made
		}
		changeMade();
	}
	
	public void moveRight() {
		curPiece.moveRight();
		changeMade();
	}
	
	public void moveLeft() {
		curPiece.moveLeft();
		changeMade();
	}
	
	public void moveDown() {
		curPiece.moveDown();
		pieceBoard.addPiece(curPiece);
		newPiece();
		changeMade();
		//need to implement, must get possible positions from the board
	}
	
	public void changeMade() {
		board.changeMade();
	}
	
	public void rotateClockwise() {
		curPiece.rotateClockwise();
		board.changeMade();
	}
	
	public void rotateCounterClockwise() {
		curPiece.rotateCounterClockwise();
		board.changeMade();
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
		if (state == State.paused) {
			board.stopTimer();
		}
		else if (state == State.alive) {
			if(deadStart) {
				restart();
			}
			else {
				board.startTimer();
			}
		}
		else if(state == State.dead) {
			board.stopTimer();
			deadStart = true;
		}
	}
}
