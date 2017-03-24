package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

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
		player.placeBoats();
		//player.printGrid(true);
		//player.printProbability();
		
		
	}
	
	public void run(){
	
		
		play = new RandomPlay();
			
		//play.basicPlay(player);
		play.basicWithBoatFinder(player);
		//play.basicWithBoatFinderParity(player);
		//play.basicWithSmartBoatFinder(player);
		//play.basicWithSmartBoatFinderParity(player);
		
		//play = new HeatMapPlay();
			
		//play.basicPlay(player);
		//play.basicWithBoatFinder(player);
		//play.basicWithBoatFinderParity(player);
		//play.basicWithSmartBoatFinder(player);
		//play.basicWithSmartBoatFinderParity(player);
			
		System.out.println(player.getShotCount());	
		
		
	}

	
}