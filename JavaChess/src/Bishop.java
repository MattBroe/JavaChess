
public class Bishop extends Piece {

	//TODO: Bishop class looks more or less good right now.

	public Bishop(boolean isWhite, int x, int y) {
		// TODO Auto-generated constructor stub
		this.color = isWhite;
		this.rank = "B";
		this.setX(x);
		this.setY(y);
	}

	public boolean canMove(int destX, int destY) {
		//Pieces cannot move to the space they're already on:
		if (destX == this.getX() && destY == this.getY()) {
			return false;
		}
		
		
		if (Game.pieceArray[destX][destY] != null) {
			//If true, means the destination space contains a piece
			
			if ((Game.pieceArray[destX][destY].color == this.color)) {
				//Pieces cannot move to spaces containing pieces of the same type
				return false;
			}
		}
		

		//If [destX][destY] is on a diagonal with the current position:
		if (Math.abs(this.getX()-destX) == Math.abs(this.getY()-destY)){

			
			if (destX > this.getX() && destY > this.getY()) {
				//If the destination is down and to the right
				
				for (int i = 1; i<destX-this.getX(); i++) {
					//Make sure there are no pieces in between the bishop and the
					//destination:
					if (Game.pieceArray[this.getX()+i][this.getY()+i] != null) {
						return false;
					}
				}
				
				//checkYourself is always checked before canMove is allowed to return
				//true
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
	
			if (destX > this.getX() && destY < this.getY()) {
				//If the destination is up and to the right
				
				for (int i = 1; i<destX-this.getX(); i++) {
					//Make sure there are no pieces in between the bishop and the
					//destination:
					if (Game.pieceArray[this.getX()+i][this.getY()-i] != null) {
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
			
			if (destX < this.getX() && destY > this.getY()) {
				//If the destination is down and to the left
				
				for (int i = 1; i<destY-this.getY(); i++) {
					//Make sure there are no pieces in between the bishop and the
					//destination:
					if (Game.pieceArray[this.getX()-i][this.getY()+i] != null) {
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
			if (destX < this.getX() && destY < this.getY()) {
				//If the destination is up and to the left
				
				for (int i = 1; i<this.getX()-destX;i++) {
					//Make sure there are no pieces in between the bishop and the
					//destination:
					if (Game.pieceArray[this.getX()-i][this.getY()-i] != null) {
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else 
					return false;
			}
		}
		//If the destination is not on a diagonal with the bishop:
		return false;
	}
}
