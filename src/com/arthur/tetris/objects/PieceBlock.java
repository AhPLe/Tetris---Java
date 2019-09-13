package com.arthur.tetris.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.xml.transform.Templates;

import com.arthur.tetris.objects.Piece;
import com.arthur.tetris.window.Board;
import com.arthur.tetris.framework.PiecePart;
import com.arthur.tetris.framework.PieceType;

public class PieceBlock{
	//this includes piece parts but does not extend PiecePart
	
	public Color[][] blocks;
	
	public PieceBlock() {
		
		blocks = new Color[Board.PIECEHEIGHT][Board.PIECEWIDTH];
	}
	
	public void paintBlock(Graphics g) {
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[i].length; j++) {
				if (blocks[i][j] !=null) {
					g.setColor(blocks[i][j]);
					g.fillRect((j*PiecePart.BRICKSIZE), (i*PiecePart.BRICKSIZE), PiecePart.BRICKSIZE, PiecePart.BRICKSIZE);
				}
			}
		}
	}

	public void render(Graphics g) {
		//sets the piece to the color of the piece
		paintBlock(g);
	}
	
	
	public void addPiece(Piece piece) {
		int[] arrPos = toArray(piece.getX(), piece.getY());
		int arrX = arrPos[0];
		int arrY = arrPos[1];
		for (int i = 0; i < piece.shape.length; i++) {
			for (int j = 0; j < piece.shape[i].length; j++) {
				if (piece.shape[i][j]) {
					blocks[arrY + i][arrX + j] = piece.getColor();
				}
			}
		}
		//the below is not ready to be used
		checkAndEraseLines(arrY, piece.shape.length);
	}
	
	public void checkAndEraseLines(int arrY, int vertLines) {
		//numLines is the number of vertical lines
		//since only the lines that had a piece added could possibly contain a full row, this checks those rows
		//this function decides if line Erasure is necessary
		boolean debug = false;
		if (debug) {
			System.out.println("new check");
		}
		int totalErased = 0;
		int lastLine = 0;
		boolean needErase = false;
		int[] valuesToErase = new int[vertLines];
		//valuesToErase is useful for the next part: eraseBlocks
		//so there doesn't have to be a second check of all lines involved
		for (int i = 0; i < vertLines; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				if (blocks[i + arrY][j] == null) {
					if (debug) {
						System.out.println("blocks (" + (i + arrY) + "," + j + ") was null");
					}
					lastLine = i + 1;
					break;
				}
				if (j == blocks[0].length - 1) {
					if (lastLine == i) {
						valuesToErase[i] = 1;
					}
					else {
						valuesToErase[lastLine] += 1;
					}
					if (debug) {
						System.out.println("row " + i + " will be erased");
					}
					totalErased += 1;
					needErase = true;
				}
			}
		}
		if (needErase) {
			eraseBlocks(valuesToErase, totalErased, arrY);
			//this function erases the required lines
		}
	}
	
	public void rowNull(int row) {
		Color[] rowNew = new Color[blocks[0].length];
		blocks[row] = rowNew;
	}
	
	public void eraseBlocks(int[] toErase, int totalErased, int arrY) {	 
		boolean debug = false;
		int numErase = 0;
		LinkedList<Color[]> temp = new LinkedList<Color[]>();
		boolean flag = true;
		//if thread-safe is needed: ConcurrentLinkedDeque if not important: ArrayDeque
		for (int i =  0; i < arrY; i++) {
			if (i < totalErased) {
				temp.addLast(blocks[i]);
				rowNull(i);
			}
			else {
				temp.addLast(blocks[i]);
				blocks[i] = temp.removeFirst();
			}
		}
		
		//once it gets to the part where the piece is, the loop makes it easier with this implementation
		//the use of LinkedList was a good but late addition, the rest of the variables may not be strictly required
		for (int i = 0; i < toErase.length; i++) {
			if (toErase[i] > 0) {
				if (debug) {
					System.out.println("removing row " + (i + arrY));
				}
				numErase = toErase[i];
				blocks[i + arrY] = temp.removeFirst();
				numErase--;
			}
			else if(numErase > 0) {
				if(debug) {
					System.out.println("removing row " + (i + arrY));
				}
				blocks[i + arrY] = temp.removeFirst();
				numErase--;
			}
			else {
				if(debug) {
					System.out.println("removing and adding row " + (i + arrY));
				}
				temp.addLast(blocks[i + arrY]);
				blocks[i + arrY] = temp.removeFirst();
			}
		}
	}
	
	public void testPiece() {
		int width = 9;
		int height = 7;
		Color c = Color.WHITE;
		for (int i = blocks.length - 1; i >blocks.length - 1 - height; i--) {
			for (int j = 0; j < width; j++) {
				blocks[i][j] = c;
			}
		}
		
	}
	
	private int[] toArray(int x, int y) {
		return new int[] {(int)(x/Piece.BRICKSIZE), (int)(y/Piece.BRICKSIZE)};
	}
	
	public boolean isBlocked(int toX, int toY, boolean[][] shape){
		//variables input are all variables that could be changed, this function tests if they would overlap something
		boolean isBlocked = false;
		int[] arrPos = toArray(toX, toY);

		int arrX = arrPos[0];
		int arrY = arrPos[1];
		//seems wordy, also the below has not been implemented

		for (int i = 0; i < shape.length; i++) {
				if (arrY + i > -1 && arrY + i < blocks.length) {	
					for (int j = 0; j < shape[i].length; j++) {
						if(arrX + j > -1 && arrX + j < blocks[0].length) {
							if (shape[i][j]) {
								if (arrY + i > blocks.length - 1 || blocks[arrY + i][arrX + j] != null) {
									isBlocked = true;
									return isBlocked;
								}
							}
						}
						else {
							isBlocked = true;
							return isBlocked;
						}
						
					}
				}
				else {
					isBlocked = true;
					return isBlocked;
				}
			}
		return isBlocked;
	}
	
	public int lowestPoint(Piece piece) {
		boolean debug = false;
		if(debug) {
			System.out.println("new movement check here");
		}
		//tests the lowest point a piece can g
		//this still does not test all possibilities for non-normal tetris shapes
		//like an array with all false or null on one side, or a complex 4x4 with an internal void of a square
		int[] arrPos = toArray(piece.getX(), piece.getY());
		int arrX = arrPos[0];
		int arrY = arrPos[1];
		int[] lowPointPiece = new int[piece.shape[0].length];
		int[] lowPointBlock = new int[piece.shape[0].length];
		int lowestPoint  = blocks.length - 1;
		
		//making it easy to find the dimensions of the piece
		for (int i = 0; i < piece.shape[0].length; i++) {
			for (int j = piece.shape.length - 1; j > -1; j--) {
				//height is in inner loop, needs to start from lowest point for each horizontal point
				if (piece.shape[j][i]) {
					lowPointPiece[i] = piece.shape.length - 1 - j;
					break;
				}
			}
		}
		//getting the relevant board information for the piece

		for (int i = 0; i < piece.shape[0].length; i++) {
			for (int j = arrY + piece.shape.length - 1 - lowPointPiece[i]; j < blocks.length; j++) {
				if(blocks[j][i + arrX] != null) {
					if(debug) {
						System.out.println("blocks[i + arrX][j] was" + blocks[j][i + arrX]);
						System.out.println("assigning and exiting");
					}
					
					lowPointBlock[i] = j - 1;
					break;
				}
				else if (j == blocks.length - 1) {
					lowPointBlock[i] = j;
					//break - will break next cycle regardless
				}
			}
		}
		
		for (int i = 0; i < piece.shape[0].length; i++) {
			//check that it fits all other sides of the block
			if (lowestPoint > lowPointBlock[i] + lowPointPiece[i] - piece.shape.length + 1) {
				if (debug) {
					System.out.println("Testing: lowPointBlock: " + lowPointBlock[i] + 
							" and lowPointPiece: " + lowPointPiece[i] + " at: " + i);
					System.out.println("lowest point to assignment: " + 
							lowestPoint + " to " + (lowPointBlock[i] + lowPointPiece[i] - piece.shape.length + 1));
				}
				//this is probably right
				lowestPoint = lowPointBlock[i] + lowPointPiece[i] - piece.shape.length + 1;
			}
		}
		
		if (lowestPoint + piece.shape.length - 1 > blocks.length - 1) {
			//this will likely never trigger
			if(debug) {
				System.out.println("reached the catch all if");
			}
			lowestPoint = blocks.length - piece.shape.length + 1;
		}
		
	return lowestPoint;
	}
}