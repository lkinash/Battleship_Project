package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;
import battleship.service.Status;

public class MainGame {

	Player player;
	Player player2;
	Play play;
	
	public MainGame(){
		
	}
	
	public MainGame(boolean humanPlayer){
		
		//player = new Player(humanPlayer);		
		//player2 = new Player(false);					//player 2 is always a computer player
	
	}
	
	public void build(){
	
		player = new Player(false);
		player.createBoats();
		player.createGrid();
		//player.placeBoatsSet();
		player.placeBoats();
		//player.printGrid(true);
		//player.printProbability();
		
		play = new RandomPlay();
		
		
	}
	
	public boolean run(){
	
		return play.basicPlay(player);

		
		//play.basicWithBoatFinder(player);
		//play.basicWithBoatFinderParity(player);
		//play.basicWithSmartBoatFinder(player);
		//play.basicWithSmartBoatFinderParity(player);
		
		//play = new HeatMapPlay();
			
		//play.basicPlay(player);
		//play.basicWithBoatFinder(player);
		//play.basicWithBoatFinderParity(player);
		//play.basicWithSmartBoatFinder(player);
		//play.basicWithSmartBoatFinderParity(player);
			
		//System.out.println(player.getShotCount());	
		
		
	}
	
	
	public Status getPlayer1SquareStatus(int x, int y){
		return player.getSquareStatus(x, y);
	}

	
}