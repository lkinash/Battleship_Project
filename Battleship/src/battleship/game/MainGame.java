package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;
import battleship.service.SimType;
import battleship.service.Status;


/**
 * The Class MainGame.
 *
 * @author Lindsey
 */
public class MainGame {

	/** The player. */
	Player player;
	
	/** The player 2. */
	Player player2;
	
	/** The play. */
	Play play;
	
	/**
	 * Instantiates a new main game.
	 */
	public MainGame(){
		
	}
	
	/**
	 * Instantiates a new main game.
	 *
	 * @param humanPlayer the human player
	 */
	public MainGame(boolean humanPlayer){
		
		//player = new Player(humanPlayer);		
		//player2 = new Player(false);					//player 2 is always a computer player
	
	}
	
	/**
	 * Builds a new game.
	 *
	 * @param type the type
	 */
	public void build(boolean type){
	
		player = new Player(false);
		player.createBoats();
		player.createGrid();
		//player.placeBoatsSet();
		player.placeBoats();
		//player.printGrid(true);
		//player.printProbability();
		
		if(type){
			play = new RandomPlay();
		}
		else{
			play = new HeatMapPlay();
		}
		
	}
	
	/**
	 * Runs the game of the type selected.
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public boolean run(SimType type){
	
		
		if(type.equals(SimType.RandomBasicPlay)){
			play.basicPlay(player);
		}
		else if(type.equals(SimType.RandomBasicWithBoatFinder)){
			play.basicWithBoatFinder(player);
		}
		else if(type.equals(SimType.RandomBasicWithBoatFinderParity)){
			play.basicWithBoatFinderParity(player);
		}
		else if(type.equals(SimType.RandomBasicWithSmartBoatFinder)){
			play.basicWithSmartBoatFinder(player);
		}
		else if(type.equals(SimType.RandomBasicWithSmartBoatFinderParity)){
			play.basicWithSmartBoatFinderParity(player);
		}
		
		else if(type.equals(SimType.ProbBasicPlay)){
			play.basicPlay(player);
		}
		else if(type.equals(SimType.ProbBasicWithBoatFinder)){
			play.basicWithBoatFinder(player);
		}
		else if(type.equals(SimType.ProbBasicWithSmartBoatFinder)){
			play.basicWithSmartBoatFinder(player);
		}
			
		return play.checkWinner(player);
		

	}
	
	
	/**
	 * Gets the player 1 square status.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the player 1 square status
	 */
	public Status getPlayer1SquareStatus(int x, int y){
		return player.getSquareStatus(x, y);
	}

	
}