
public class Rook extends Piece {
	//TODO: Rook movement works

	public Rook(boolean isWhite, int x, int y) {
		// TODO Auto-generated constructor stub
		this.color = isWhite;
		this.rank = "R";
		this.setX(x);
		this.setY(y);
	}

	@Override
	public boolean canMove(int destX, int destY) {
		
		if (destX == this.getX() && destY == this.getY()) {
			//Pieces cannot move to a space they're already on
			return false;
		}
		

		if (Game.pieceArray[destX][destY] != null) {
			//Pieces cannot move to spaces containing pieces of the same type
			if ((Game.pieceArray[destX][destY].color == this.color)) {
				//Pieces cannot move to spaces containing pieces of the same color
				return false;
			}
		}

		//Movement along columns:
		if (destX != this.getX() && destY == this.getY()) {
			if (destX > this.getX()) {
				for (int i=this.getX()+1; i<destX; i++) {
					if (Game.pieceArray[i][destY] != null) {
						//False if there is a piece between the rook and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
			else { //If destX<this.getX()
				for (int i = destX+1; i<this.getX(); i++) {
					if (Game.pieceArray[i][destY] != null) {
						//False if there is a piece between the rook and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else 
					return false;
			}
		}

		//Movement along rows:
		if (destX == this.getX() && destY != this.getY()) {
			if (destY > this.getY()) {
				for (int i=this.getY()+1; i<destY; i++) {
					if (Game.pieceArray[destX][i] != null) {
						//False if there is a piece between the rook and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
			else { //If destY < this.getY()
				for (int i = destY+1; i<this.getY(); i++) {
					if (Game.pieceArray[destX][i] != null) {
						//False if there is a piece between the rook and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
		}
		//If movement is not along column or row:
		return false;
	}

}
