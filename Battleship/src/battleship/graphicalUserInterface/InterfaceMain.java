package battleship.graphicalUserInterface;

import javax.swing.JFrame;

import battleship.game.MainGame;

public class InterfaceMain {

	public static void main(String[] args)
	{
		
		//BattleshipWindow window = new BattleshipWindow();				//create a window object
		
		//window.runGame();				//add the items to the window
		
		//window.setSize(800, 1000);			//set the frame size
		
		//window.setVisible(true);				//make it visable
		
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//add a close option
			
		MainGame game = new MainGame();
		game.run();
		
	}
	
	
}
