package battleship.graphicalUserInterface;

import javax.swing.JFrame;

public class InterfaceMain {

	public static void main(String[] args)
	{
		
		BattleshipWindow window = new BattleshipWindow();				//create a window object
		
		window.addButtons();				//add the items to the window
		
		window.setSize(600, 1000);			//set the frame size
		
		window.setVisible(true);				//make it visable
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//add a close option
			
	}
	
	
}