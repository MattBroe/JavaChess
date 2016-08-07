
public class Queen extends Piece {
	
	public Queen(boolean isWhite, int x, int y) {
		this.color = isWhite;
		this.rank = "Q";
		this.setX(x);
		this.setY(y);
	}

	//Essentially Bishop.canMove and Rook.canMove put together:
	public boolean canMove(int destX, int destY) {
		// TODO Auto-generated method stub
		if (destX == this.getX() && destY == this.getY()) {
			//Pieces can't move to a space they're already on
			return false;
		}
		

		if (Game.pieceArray[destX][destY] != null) {
			//Pieces cannot move to spaces containing pieces of the same type
			if ((Game.pieceArray[destX][destY].color == this.color)) {
				return false;
			}
		}

		//Movement along columns:
		if (destX != this.getX() && destY == this.getY()) {
			if (destX > this.getX()) {
				for (int i=this.getX()+1; i<destX; i++) {
					if (Game.pieceArray[i][destY] != null) {
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
					return true;
				else
					return false;
			}
			else { //if destX < this.getX()
				for (int i = destX+1; i<this.getX(); i++) {
					if (Game.pieceArray[i][destY] != null) {
						//False if there is a piece between the queen and the destination:
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
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else 
					return false;
			}
			else { //if destY < this.getY()
				for (int i = destY+1; i<this.getY(); i++) {
					if (Game.pieceArray[destX][i] != null) {
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY))
				return true;
				else
					return false;
			}
		}

		//Movement along diagonals:
		if (Math.abs(this.getX()-destX) == Math.abs(this.getY()-destY)){

			if (destX > this.getX() && destY > this.getY()) {
				//Down and to the right
				for (int i = 1; i<destX-this.getX(); i++) {
					if (Game.pieceArray[this.getX()+i][this.getY()+i] != null) {
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY)){
				return true;
				} else 
					return false;
			}
			if (destX > this.getX() && destY < this.getY()) {
				//Up and to the right
				for (int i = 1; i<destX-this.getX(); i++) {
					if (Game.pieceArray[this.getX()+i][this.getY()-i] != null) {
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY)) {
				return true;
				} else
					return false;
			}
			if (destX < this.getX() && destY > this.getY()) {
				//Down and to the left
				for (int i = 1; i<destY-this.getY(); i++) {
					if (Game.pieceArray[this.getX()-i][this.getY()+i] != null) {
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY)) {
				return true;
				} else
					return false;
			}
			if (destX < this.getX() && destY < this.getY()) {
				//Up and to the left
				for (int i = 1; i<this.getX()-destX;i++) {
					if (Game.pieceArray[this.getX()-i][this.getY()-i] != null) {
						//False if there is a piece between the queen and the destination:
						return false;
					}
				}
				if (this.checkYourself(destX, destY)) {
				return true;
				} else
					return false;
			}
		}
		//If movement is not along rows, columns, or diagonals:
		return false;
	}

}
