
public class Pawn extends Piece {
	
	//TODO: Pawn movement working. Need to make sure pawns change to queens after reaching
	//last file.
	


	public Pawn(boolean isWhite, int x, int y) {
		this.color = isWhite;
		this.rank = "P"; 
		this.setX(x);
		this.setY(y);
		this.hasMoved=false;
	}

	//Pawns have a complicated movement pattern 
	
	public boolean canMove(int destX, int destY){
		if (destX == this.getX() && destY == this.getY()) {
			//A piece can't move to a space it's already on
			return false;
		}
		
		
		
		
		if (Game.pieceArray[destX][destY] != null) {
			//If there's a piece in the destination tile
			if ((Game.pieceArray[destX][destY].color == this.color)) {
				//Pieces cannot move to spaces containing pieces of the same type
				return false;
			}
		}
		
		//Straight line movement:
		if (this.getX() == destX) {
			if (this.color == true) {
				//If the pawn is white:
				if (this.hasMoved == false) {
				//If the pawn hasn't moved yet:
				if (1 <= (this.getY()-destY) && (this.getY()-destY) <= 2) {
					//White pawns move in the direction of decreasing y-value
					for (int i = destY; i<this.getY(); i++) {
						if (Game.pieceArray[this.getX()][i] != null) {
							//If there's a piece between the pawn and its destination:
							return false;
							//Pawns cannot take pieces directly in front of them, so this
							//returns false if there's a piece in the destination tile
						}
						
					}
					
					//Return false if the move puts your own king in check
					if (this.checkYourself(destX, destY))
					return true;
					else
						return false;
				}
				//If the inequality 1 <= (destY-this.getY()) <= 2 is not true, meaning
				//the destination tile is more than two tiles ahead:
				return false;
				} 
				
				else { //if this.hasMoved == true:
					if (this.getY()-destY==1) {
						if (Game.pieceArray[this.getX()][destY] == null) {
							
							if (this.checkYourself(destX, destY))
							return true;
							else
								return false;
						}
					}
					//If destY is not exactly 1 less than this.getY():
					return false;
				}
			}
			else { //if the pawn's color is black:
				if (this.hasMoved == false) {
					if (1 <= (destY-this.getY()) && (destY-this.getY()) <= 2) {
						//Black pawns move in the direction of increasing y-value
						for (int i = this.getY()+1; i<=destY; i++) {
							if (Game.pieceArray[this.getX()][i] != null) {
								//If there's a piece between the pawn and its destination:
								return false;
								//Also returns false if there is a piece on the destination tile, since
								//pawns cannot take when moving straight
							}
						}
						if (this.checkYourself(destX, destY))
						return true;
						else
							return false;
					}
					//If the inequality 1 <= (this.getY()-destY) <= 2 is not true:
					return false;
					} 
					
					else { //if this.hasMoved == true:
						if (destY-this.getY()==1) {
							if (Game.pieceArray[this.getX()][destY] == null) {
								if (this.checkYourself(destX, destY))
								return true;
								else
									return false;
							}
						}
						//If destY is not exactly 1 greater than this.getY():
						return false;
					}
			}		
	}
		else {//if destX != this.getX(), meaning non-straight line movement along columns
			if (Game.pieceArray[destX][destY] != null) {
				//Pawns can only move diagonally if they're taking
					if (this.color == true) {
						if (this.getY()-destY == 1) {
							if (Math.abs(destX-this.getX()) == 1) { //Either the left or right diagonal in front of the pawn
								if (this.checkYourself(destX, destY))
								return true;
								else
									return false;
							}
							return false;
						}
						return false;
					}
					else { //if the pawn is black
						if (destY-this.getY() == 1) {
							if (Math.abs(destX-this.getX()) == 1) {
								if (this.checkYourself(destX, destY))
								return true;
								else
									return false;
							}
							return false;
						}
						return false;
					}
				}
				return false; //if there is no enemy in the destination tile of a diagonal move
			}
	}



}