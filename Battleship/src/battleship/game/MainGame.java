package battleship.game;

import battleship.service.Constants;
import battleship.service.RandomNumber;

public class MainGame {

	public MainGame(){
		
	}
	
	public MainGame(boolean humanPlayer){
		
		Player player1 = new Player(humanPlayer);		
		Player player2 = new Player(false);					//player 2 is always a computer player
	
	}
	
	public void run(){
	
		Player player = new Player(false);
		player.createBoats();
		player.createGrid();
		player.placeBoats();
		player.printGrid(true);
		
		int x, y;
		
		while(true){
					
			x = RandomNumber.getRandom(Constants.GRID_SIZE);
			y = RandomNumber.getRandom(Constants.GRID_SIZE);
			
			if(!player.hasBeenShot(x, y)){
				player.shoot(x, y);
				player.printGrid(true);
			}
			
			if(player.getWinner())
				break;
		}
		
		System.out.println(player.getShotCount());	
		}

	
}
