package battleship.graphicalUserInterface;

import javax.swing.JFrame;

import battleship.game.MainGame;


/**
 * The Class InterfaceMain.
 *
 * @author Lindsey
 */
public class InterfaceMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
	
		
		BattleshipWindow window = new BattleshipWindow();				//create a window object
		
		window.runGame();				//add the items to the window
		
		window.setSize(650, 800);			//set the frame size
		
		window.setVisible(true);				//make it visable
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//add a close option
			
		
		
		/*
		MainGame game = new MainGame();
		
		for(int i = 0; i < 25000; i++){
			game.build();
			game.run();
		}
		*/
	}
	
	
}
