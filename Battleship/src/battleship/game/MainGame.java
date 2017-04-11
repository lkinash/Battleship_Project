package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;
import battleship.service.SimType;
import battleship.service.Status;

/**
 * @author Lindsey
 *
 */
public class MainGame {

	Player player;
	Player player2;
	Play play;
	
	public MainGame(){
		
	}
	
	/**
	 * @param humanPlayer
	 */
	public MainGame(boolean humanPlayer){
		
		//player = new Player(humanPlayer);		
		//player2 = new Player(false);					//player 2 is always a computer player
	
	}
	
	/**
	 * Builds a new game
	 * @param type
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
	 * Runs the game of the type selected
	 * @param type
	 * @return
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
	 * @param x
	 * @param y
	 * @return
	 */
	public Status getPlayer1SquareStatus(int x, int y){
		return player.getSquareStatus(x, y);
	}

	
}