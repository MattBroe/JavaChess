import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;


public abstract class Piece {

	protected boolean color; 
	//True if piece is white and false if piece is black
	
	public String rank; 
	//P, N, B, R, Q, K
	
	private int x;
	private int y;
	//(x,y) coordinates of the piece in pieceArray

	public static boolean whoseTurn = true; 
	//whoseTurn alternates in Piece.move after every move. If true it's white's turn
	//and if false it's black's turn.
	
	public boolean hasMoved;
	//Some moves, such as castling and moving pawns two spaces, depend on whether or
	//not a piece has already been moved.
	
	
	
	//The icons of the various pieces. Used in drawPiece(). Files are contained in
	// Final Chess Project/src/Icons
	public static ImageIcon BPIcon = new ImageIcon("src/Icons/Black P_64x64.png");
	public static ImageIcon BBIcon = new ImageIcon("src/Icons/Black B_64x64.png");
	public static ImageIcon BRIcon = new ImageIcon("src/Icons/Black R_64x64.png");
	public static ImageIcon BNIcon = new ImageIcon("src/Icons/Black N_64x64.png");
	public static ImageIcon BQIcon = new ImageIcon("src/Icons/Black Q_64x64.png");
	public static ImageIcon BKIcon = new ImageIcon("src/Icons/Black K_64x64.png");
	
	public static ImageIcon WPIcon = new ImageIcon("src/Icons/White P_64x64.png");
	public static ImageIcon WBIcon = new ImageIcon("src/Icons/White B_64x64.png");
	public static ImageIcon WRIcon = new ImageIcon("src/Icons/White R_64x64.png");
	public static ImageIcon WNIcon = new ImageIcon("src/Icons/White N_64x64.png");
	public static ImageIcon WQIcon = new ImageIcon("src/Icons/White Q_64x64.png");
	public static ImageIcon WKIcon = new ImageIcon("src/Icons/White K_64x64.png");

	//(x,y) coordinate setters and getters:

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	
	public boolean checkYourself(int destX, int destY) {
		//Returns false if an attempted move puts your own king in check.
		//This is the last check run before canMove() can return true. If 
		//this.checkYourself(destX,destY) == false, this.canMove(destX,destY) = false.
		
		
		
		if (this.rank == "K") {
			//Save the state of the destination position:
			Piece savedPiece = Game.pieceArray[destX][destY];
			//Move the selected piece to the destination and set its starting position
			//to null:
			Game.pieceArray[destX][destY] = this;
			Game.pieceArray[this.getX()][this.getY()] = null;
			//Save the selected piece's starting (x,y) position
			int savedX = this.getX();
			int savedY = this.getY();
			//Set the selected piece's position fields to the destination:
			this.setX(destX);
			this.setY(destY);
			
			//Since we moved the king we have to update the static fields
			//containing the king's position
			//This is the only distinction between checkYourself for a king and checkYourself for
			//any other piece
			if (this.color == true) {
				King.whiteKingX = destX;
				King.whiteKingY = destY;
			} else {
				King.blackKingX = destX;
				King.blackKingY = destY;
			}
			
			//See if moving the king put it in check. If it did, we undo the move and 
			//return false.
			if (check(this.color) == true) {
				Game.pieceArray[savedX][savedY] = this;
				Game.pieceArray[destX][destY] = savedPiece;
				this.setX(savedX);
				this.setY(savedY);
				if (this.color == true) {
					King.whiteKingX = savedX;
					King.whiteKingY = savedY;
				} else {
					King.blackKingX = savedX;
					King.blackKingY = savedY;
				}
				return false;
			}
			
			//If the move did not put the king in check, we undo the move (it will be
			//made final when this.move(destX,destY) finishes executing) and return true.
			Game.pieceArray[savedX][savedY] = this;
			Game.pieceArray[destX][destY] = savedPiece;
			this.setX(savedX);
			this.setY(savedY);
			if (this.color == true) {
				King.whiteKingX = savedX;
				King.whiteKingY = savedY;
			} else {
				King.blackKingX = savedX;
				King.blackKingY = savedY;
			}
			return true;
		}
		
		//Essentially the same procedure if the piece moved isn't a king. Only difference
		//is the king's static location variables don't need to be updated
		Piece savedPiece = Game.pieceArray[destX][destY];
		Game.pieceArray[destX][destY] = this;
		Game.pieceArray[this.getX()][this.getY()] = null;
		int savedX = this.getX();
		int savedY = this.getY();
		this.setX(destX);
		this.setY(destY);
		if (check(this.color) == true) {
			Game.pieceArray[savedX][savedY] = this;
			Game.pieceArray[destX][destY] = savedPiece;
			this.setX(savedX);
			this.setY(savedY);
			return false;
		}
		Game.pieceArray[savedX][savedY] = this;
		Game.pieceArray[destX][destY] = savedPiece;
		this.setX(savedX);
		this.setY(savedY);
		return true;
		}

	
	public static boolean canAttack(boolean teamColor, int x, int y) {
		//A check to see if any piece of color teamColor can move to (x,y)

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j<=7; j++) {
				if (Game.pieceArray[i][j]!=null) {
					if (Game.pieceArray[i][j].color == teamColor) {
						if (Game.pieceArray[i][j].canMove(x, y)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean movesLeft(boolean teamColor) {
		//A check to see if teamColor has any possible moves remaining. Called on 
		//teamColor in the move method at the start of teamColor's turn. 
		//If this returns false, the game is over.
		
		//Check every tile in the board to see if teamColor can legally move a piece
		//there. If not, teamColor has no moves left. Since it is teamColor's turn,
		//that means the game is over.
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j<=7; j++) {
				if (Piece.canAttack(teamColor, i, j) == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	
 
	public static boolean check(boolean teamColor) {
		//A check to see if the king of color teamColor is in check. Called in the move
		//method at the start of teamColor's turn.
		
		if (whoseTurn!=teamColor)
			//Boolean to avoid a bug where you could always check the other team's king if your king
			//was in check, even if your move didn't get your king out of check
			return false;
		if (teamColor == true) {
			if (Piece.canAttack(false, King.whiteKingX, King.whiteKingY) == true) {
				return true;
			}
		} 
		if (teamColor == false) {
			if (Piece.canAttack(true, King.blackKingX, King.blackKingY) == true) {
				return true;
			}
		}
		return false;
	}
	
	


	
	public boolean move(int destX, int destY) {
		//Note that although move is a boolean it's not actually used that way anywhere:
		//the only reason it's not void is so that the method can be halted mid-execution by
		//a return statement
		//Move is the longest and probably the most important method, other than
		//canMove()
	
		{
			
			if (this.canMove(destX,destY) == true) {
		
				if (this.rank == "K" 
						&& Game.pieceArray[destX][destY]!=null
						&& Game.pieceArray[destX][destY].rank=="R" 
						&& this.color == Game.pieceArray[destX][destY].color) {
					//Castling. If either the king or the rook had been moved yet,
					//this.canMove(destX,destY) would have returned false. 
					
					//Save the rook's position:
					Piece other = Game.pieceArray[destX][destY];
					
					//Black queenside castle:
					if (destX == 0 && destY == 0) {
						Game.pieceArray[2][0] = this;
						Game.pieceArray[3][0] = other;
						Game.pieceArray[this.x][this.y] = null;
						Game.pieceArray[other.x][other.y] = null;
						this.setX(2);
						this.setY(0);
						other.setX(3);
					}
					//Black kingside castle:
					if (destX == 7 && destY == 0) {
						Game.pieceArray[6][0] = this;
						Game.pieceArray[5][0] = other;
						Game.pieceArray[this.x][this.y] = null;
						Game.pieceArray[other.x][other.y] = null;
						this.setX(6);
						other.setX(5);
					}
					//White queenside castle:
					if (destX == 0 && destY == 7) {
						Game.pieceArray[2][7] = this;
						Game.pieceArray[3][7] = other;
						Game.pieceArray[this.x][this.y] = null;
						Game.pieceArray[other.x][other.y] = null;
						this.setX(2);
						other.setX(3);
					}
					//White kingside castle
					if (destX == 7 && destY == 7) {
						Game.pieceArray[6][7] = this;
						Game.pieceArray[5][7] = other;
						Game.pieceArray[this.x][this.y] = null;
						Game.pieceArray[other.x][other.y] = null;
						this.setX(6);
						other.setX(5);
					}
					
					Game.updateButtonArray();} 
				else {
					//If you're not castling:
					
					
					//Move the piece and set its position variables:
					Game.pieceArray[destX][destY] = this;
					Game.pieceArray[this.getX()][this.getY()] = null;

					this.setX(destX);
					this.setY(destY);
					
					if (this.rank == "K") {
						if (this.color == true) {
							King.whiteKingX = destX;
							King.whiteKingY = destY;
						}
						else {
							King.blackKingX = destX;
							King.blackKingY = destY;
						}
					}
				
				//Print the result of the move:
				System.out.println("Moved to ("+(destX+1)+","+(8-destY)+").");
				}
				
				Game.updateButtonArray();
				
				if (this.color == true) {
					
					//White just moved, so it's black's turn
					whoseTurn = false;
					
					//Queening of white pawns who reach the last rank:
					if (this.rank == "P") {
						if (this.y == 0) {
							Game.pieceArray[this.x][this.y] = new Queen(true, this.x, this.y);
						}
					}
					
					//Check if black has any moves left at the start of black's turn:
					if (movesLeft(false)==false) {
						Game.frame.dispose();
						//If black has no moves left and its king is in check, it's a 
						//checkmate
						if (check(false) == true) {
							System.out.println("Checkmate 1-0");
						}
						else {
							System.out.println("Stalemate 0.5-0.5");
						}
						return false;
					}
				} else {
					
					//Black just moved, so it's white's turn
					
					whoseTurn = true;
					
					//Queening of black pawns:
					
					if (this.rank == "P") {
						if (this.y == 7) {
							Game.pieceArray[this.x][this.y] = new Queen(false, this.x, this.y);
						}
					}
					
					//Check if white has any moves left at the start of white's turn:
					if (movesLeft(true) == false) {
						Game.frame.dispose();
						if (check(true) == true) {
							System.out.println("Checkmate 0-1");
						}
						else {
							System.out.println("Stalemate 0.5-0.5");
						}
						return false;
					}
				}
				
				this.hasMoved = true;
				
				//Update the king's position after moving
				if (this.rank == "K") {
					if (this.color == true) {
						King.whiteKingX = destX;
						King.whiteKingY = destY;
					}
					else {
						King.blackKingX = destX;
						King.blackKingY = destY;
					}
				}
				
				Game.updateButtonArray();
				
				//Announce check on color whose turn is starting
				if (check(!this.color)==true){
					if (!this.color ==true) {
						System.out.println("White is in check");
					} else {
						System.out.println("Black is in check");
					}
				}
				
				return true;
			}
			else {
				System.out.println("Illegal move.");
				Game.updateButtonArray();
				return false;
			}
		}
	}
	
	
		
	





	public void drawPiece() {
		if (this.rank == null) {
			BoardComponent.buttonArray[this.x][this.y].setText("");
			BoardComponent.buttonArray[this.x][this.y].setIcon(null);
		} else {
			//Write the piece's rank on the button containing it:

			
			if (this.color == true) {
				if (this.rank == "P")
					BoardComponent.buttonArray[this.x][this.y].setIcon(WPIcon);
				if (this.rank == "B")
					BoardComponent.buttonArray[this.x][this.y].setIcon(WBIcon);
				if (this.rank == "N")
					BoardComponent.buttonArray[this.x][this.y].setIcon(WNIcon);
				if (this.rank == "R") 
					BoardComponent.buttonArray[this.x][this.y].setIcon(WRIcon);
				if (this.rank == "Q")
					BoardComponent.buttonArray[this.x][this.y].setIcon(WQIcon);
				if (this.rank == "K")
					BoardComponent.buttonArray[this.x][this.y].setIcon(WKIcon);
				//The panel is then updated in main by BoardComponent.update()
			}
			else {
				if (this.rank == "P")
					BoardComponent.buttonArray[this.x][this.y].setIcon(BPIcon);
				if (this.rank == "B")
					BoardComponent.buttonArray[this.x][this.y].setIcon(BBIcon);
				if (this.rank == "N")
					BoardComponent.buttonArray[this.x][this.y].setIcon(BNIcon);
				if (this.rank == "R") 
					BoardComponent.buttonArray[this.x][this.y].setIcon(BRIcon);
				if (this.rank == "Q")
					BoardComponent.buttonArray[this.x][this.y].setIcon(BQIcon);
				if (this.rank == "K")
					BoardComponent.buttonArray[this.x][this.y].setIcon(BKIcon);
				//After frame.repaint is called in Game.updateButtonArray, the display
				//is rewritten to reflect the changes
			}
		}
	}

	public abstract boolean canMove(int destX, int destY);

}

