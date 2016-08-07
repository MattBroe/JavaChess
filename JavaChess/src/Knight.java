
public class Knight extends Piece {

	public Knight(boolean isWhite, int x, int y) {
		this.color = isWhite;
		this.rank = "N";
		this.setX(x);
		this.setY(y);
	}
	
	//Simplest canMove method. Only returns false if the move puts the piece's own
	//king in check or if the move isn't in an "L".
	public boolean canMove(int destX, int destY) {
		
		if (destX == this.getX() && destY == this.getY()) {
			return false;
		}
		
		

		if (Game.pieceArray[destX][destY] != null) {
			//Pieces cannot move to spaces containing pieces of the same type
			if ((Game.pieceArray[destX][destY].color == this.color)) {
				return false;
			}
		}
		
		//Movement in L's
		if (Math.abs(destX-this.getX())==2 && Math.abs(destY-this.getY())==1){
			if (this.checkYourself(destX, destY))
				return true;
			else
				return false;
		}
		if (Math.abs(destX-this.getX())==1 && Math.abs(destY-this.getY())==2) {
			if (this.checkYourself(destX, destY))
				return true;
			else
				return false;
		}
		
		return false;
	}

}
