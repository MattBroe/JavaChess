import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class BoardMouseListener implements MouseListener {
	//The game is click based. Every JButton in the BoardComponent object has a 
	//BoardMouseListener that responds when the JButton is clicked. When a piece is
	//clicked, its position (startingX, startingY) is saved, then when a destination 
	//space (destX,destY) is clicked, Game.pieceArray[startingX][startingY].move(destX,destY)
	//is called.
	
	//memory stores the starting point:
	public static Point memory = new Point();
		
	//endLoc stores the ending point:
	private Point endLoc = new Point();
	
	//i keeps track of whether it's the first or second click:
	public static int i = 0;

	public BoardMouseListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//When i = 1, the space clicked is stored in memory.
		//When i = 0, the piece at the space stored in memory tries to move to the space that was just
		//clicked.
		i = (i+1) % 2;  
		if (i==1) {
		//Store the button that was just clicked:
		JButton btn = (JButton)e.getSource(); 
		//Find the coordinates of the piece associated with that button by searching 
		//the buttonArray for it:
		for (int x = 0; x<=7; x++) {
			for (int y = 0; y<=7;y++){
				if (BoardComponent.buttonArray[x][y] == btn) { 
					memory = new Point(x,y);
				}
			}
			
		}
		//If the tile you initially clicked didn't have a piece, nothing happens 
		//and i resets so you can choose a new piece without having to click twice:
		if (Game.pieceArray[memory.x][memory.y] == null) {
			//memory = new Point();
			i = 0;
		}
		//If i==0:
		else {
			if (Game.pieceArray[memory.x][memory.y].color != Piece.whoseTurn) {
				i = 0;
				System.out.println("Wait your turn");
			} else {
		//currentPiece is the piece that was clicked immediately before the destination
		//space was clicked
		Piece currentPiece= Game.pieceArray[memory.x][memory.y];
		
		if (currentPiece.rank == "P")
			System.out.println("You clicked the pawn at ("+(currentPiece.getX()+1)+","+(8-currentPiece.getY())+").");
		if (currentPiece.rank == "Q")
			System.out.println("You clicked the queen at ("+(currentPiece.getX()+1)+","+(8-currentPiece.getY())+").");
		if (currentPiece.rank == "R")
			System.out.println("You clicked the rook at ("+(currentPiece.getX()+1)+","+(8-currentPiece.getY())+").");
		if (currentPiece.rank == "N")
			System.out.println("You clicked the knight at ("+(currentPiece.getX()+1)+","+(8-currentPiece.getY())+").");
		if (currentPiece.rank == "B")
			System.out.println("You clicked the bishop at ("+(currentPiece.getX()+1)+","+(8-currentPiece.getY())+").");
		if (currentPiece.rank == "K")
			System.out.println("You clicked the king at ("+(currentPiece.getX()+1)+","+(8-currentPiece.getY())+").");
			
		System.out.println("Click where you want to move.");
			}
		}
		} 
		//if i == 0:
		else {
			//Essentially the same grid searching loop as above, with the same purpose:
			//to find and store the tile that was clicked
			JButton btn = (JButton)e.getSource();
			for (int x = 0; x<=7; x++) {
				for (int y = 0; y<=7;y++){
					if (BoardComponent.buttonArray[x][y] == btn) {
						this.endLoc.x = x;
						this.endLoc.y = y;
					}
				}
			}
			//Boolean check to avoid NullPointerException by calling this.move on a
			//null piece:
			if (Game.pieceArray[memory.x][memory.y]!=null) {
			
			Game.pieceArray[memory.x][memory.y].move(endLoc.x,endLoc.y);
			
			}
			}
		}
	

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
