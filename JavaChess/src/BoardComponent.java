import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.Border;



public class BoardComponent extends JPanel {
	
	//Array to contain the buttons that are displayed in the panel:
	public static JButton[][] buttonArray =  new JButton[8][8]; 
	//Important for the graphical and interactive user interface. Make a note of the 
	//distinction between BoardComponent and buttonArray.
	
	public BoardComponent() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardComponent(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}
	
	//The two following methods are just here because BoardComponent extends JPanel;
	//they're never actually called
	public BoardComponent(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public BoardComponent(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	

	public void init() {
		//Set 8x8 grid layout:
		this.setLayout(new GridLayout(0,8)); 
		
		//Initialize the style of the border between tiles:
		Border border = BorderFactory.createLineBorder(Color.black); 
		
		for (int y = 0; y<=7; y++) {
			for (int x = 0; x<=7; x++) {
				
				JButton btn = new JButton("");
				btn.setEnabled(true);
				//Every button has a MouseListener:
				btn.addMouseListener(new BoardMouseListener());
				
				buttonArray[x][y] = btn;
				
				//Even-numbered rows in the array have a white leftmost tile and 
				//alternate from white to grey as you move left to right. Odd-numbered
				//rows have a grey leftmost tile.
				
				if (y%2 == 0) {
					if (x%2 == 0){
						btn.setBackground(new Color(255,253,208));
					} else {
						btn.setBackground(new Color(139,137,137));
					}
				} else {
					if (x%2 == 0) {
						btn.setBackground(new Color(139,137,137));
					} else {
						btn.setBackground(new Color(255,253,208));
					}
				}
				btn.setBorder(border);
				btn.setOpaque(true);
				
				//The buttons are added to the panel starting with buttonArray[0][0] and
				//moving right along the rows. This ensures that the layout of the buttons
				//in the panel directly corresponds to the layout of the array containing 
				//them.
				this.add(buttonArray[x][y]);
			}
		}
	}
	

}
