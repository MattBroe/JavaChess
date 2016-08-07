
public class King extends Piece {
	
	//Static variables containing the coordinates of both kings. Used in the check
	//method.
	public static int whiteKingX;
	public static int whiteKingY;
	public static int blackKingX;
	public static int blackKingY;

	public King(boolean isWhite, int x, int y) {
		this.color = isWhite;
		this.rank = "K";
		this.setX(x);
		this.setY(y);
		if (isWhite == true) {
			King.whiteKingX = x;
			King.whiteKingY = y;
		}
		else {
			King.blackKingX = x;
			King.blackKingY = y;
		}
	}
	
	

	public boolean canMove(int destX, int destY) {
		
		if (destX == this.getX() && destY == this.getY()) {
			//Pieces can't move to a space they're already on
			return false;
		}
		
		
		if (Game.pieceArray[destX][destY] != null) {
			
			if ((Game.pieceArray[destX][destY].color == this.color)) {
				
				//Kings cannot move to spaces containing pieces of the same color, except during
				//castling
				
				if (Game.pieceArray[destX][destY].rank == "R") {
					
					//You can only castle if both king and rook haven't moved
					
					if (Game.pieceArray[destX][destY].hasMoved == false && this.hasMoved == false) {
						
						//Kingside rook. destY = this.getY() because both rook and king haven't moved.
						if (destX > this.getX()) {
							
							for (int i = this.getX()+1; i<destX; i++) {
								
								//Can't castle if there's a piece between the king and the
								//rook
								
								if (Game.pieceArray[i][destY] != null) {
									return false;
								}
							}
							//Can't castle if one of the spaces between the king and the 
							//rook is within range of an enemy move. This includes the king and
							//rook's starting positions, so you can't castle if you're in check
							
							for (int j = this.getX(); j<=destX;j++) {
								if (canAttack(!this.color, j, destY) == true) {
								return false;
							}
						}
						}
						
						//Queenside rook:
						if (destX < this.getX()) {
							for (int i = destX+1; i<this.getX(); i++) {
								if (Game.pieceArray[i][destY] != null) {
									return false;
								}
							}
							
							//Can't castle if one of the spaces between the king and the 
							//rook is within range of an enemy move. This includes the king and
							//rook's starting positions, so you can't castle if you're in check
							
							for (int j = destX; j<=this.getX();j++) {
								if (canAttack(!this.color, j, destY) == true) {
								return false;
							}
						}
					}
						if (this.checkYourself(destX, destY))
							return true;
						else
							return false;
				}
			}
				if (Game.pieceArray[destX][destY].rank != "R") {
					//Check to prevent bug where the king could take pieces of his own color:
					return false;
				}
		}
		}
		
		//Left/right/up/down movement one space away
		if (Math.abs(destX-this.getX()) + Math.abs(destY-this.getY()) == 1) {
			if (this.checkYourself(destX, destY))
				return true;
			else
				return false;
		}
		
		//Diagonal movement one space away:
		if (Math.abs(destX-this.getX()) + Math.abs(destY-this.getY()) == 2 
				&& this.getX() != destX 
				&& this.getY() != destY) {
			if (this.checkYourself(destX, destY))
			return true;
			else
				return false;
		}
		//If move is more than one space away:
		return false;
	}


	}

