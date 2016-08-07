import java.awt.GridLayout;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;


/*
 * List of (minor) deviations from standard chess rules:
 * Pawns cannot perform en passant captures (too complicated to explain, look it up)
 * 
 * Pawns that reach the last row are automatically promoted to queens: you cannot choose
 * between queen, bishop, rook and knight
 * 
 * Repetition of the same two moves from both players for three turns in a row does not
 * result in a stalemate
 * 
 * If both teams have only a bishop or only a knight left it's technically impossible for 
 * either to checkmate, but this scenario doesn't result in a stalemate being called
 */

public class Game {
	
	static Piece[][] pieceArray = new Piece[8][8];
	//Note that pieceArray[0][0] represents the tile in the top left corner. The console printouts
	//of board positions treat (0,0) as the bottom left corner, as per standard algebraic chess 
	//notation. Make sure not to confuse the coordinates of the moves read out on the console with
	//the coordinates of the array operations going on behind the scenes. 
	
	
	static BoardComponent board = new BoardComponent();
	//BoardComponent extends JPanel. Its purpose is to contain the clickable JButtons in
	//the display
	
	static JFrame frame = new JFrame();
	//frame is the game window.
	

	
	public static void updateButtonArray() {
		//This updates the buttonArray and the display frame to correspond to the state 
		//of the pieceArray. Called after every move.
		
		//Iterate over the pieceArray:
		for (int x = 0; x<=7;x++) {
			for (int y = 0; y<=7; y++) {
				if (pieceArray[x][y]!=null) {
				pieceArray[x][y].drawPiece(); 
				//drawPiece() updates the icons of the button in buttonArray[x][y] to correspond 
				//to the piece in pieceArray[x][y]
				}
				else {
					//No icon for null pieces
	
					BoardComponent.buttonArray[x][y].setIcon(null);
				}
			}
			//Repaint the frame to correspond to the updated positions.
			frame.repaint();
		}
	}

	public static void main(String[] args) {
		
		
		
		//Initialize pawns
		for (int i = 0; i<=7; i++){
			pieceArray[i][1] = new Pawn(false,i,1);
			pieceArray[i][6] = new Pawn(true,i,6);
		}
		//Initialize bishops
		pieceArray[2][0] = new Bishop(false,2,0);
		pieceArray[5][0] = new Bishop(false,5,0);
		pieceArray[2][7] = new Bishop(true,2,7);
		pieceArray[5][7] = new Bishop(true,5,7);
		
		//Initialize rooks
		pieceArray[0][0] = new Rook(false,0,0);
		pieceArray[7][0] = new Rook(false,7,0);
		pieceArray[0][7] = new Rook(true,0,7);
		pieceArray[7][7] = new Rook(true,7,7);
		
		//Initialize knights;
		pieceArray[1][0] = new Knight(false,1,0);
		pieceArray[6][0] = new Knight(false,6,0);
		pieceArray[1][7] = new Knight(true,1,7);
		pieceArray[6][7] = new Knight(true,6,7);
		
		//Initialize queens
		pieceArray[3][0] = new Queen(false,3,0);
		pieceArray[3][7] = new Queen(true,3,7);
		
		//Initialize kings
		pieceArray[4][7] = new King(true,4,7);
		pieceArray[4][0] = new King(false,4,0);
		

		
		
		board.init();
		
		frame.setSize(650, 650);
		frame.setTitle("JavaChess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		updateButtonArray();
		frame.add(board);
		
		
		frame.setVisible(true);
		
		
		
		
		
		

	}
}


