package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

public class HeatMapPlay implements Play {

	public HeatMapPlay(){
		
	}
	
	public void heatMapPlay(Player player){
			
		int x, y;
		Coordinate coordinate;
		
		while(true){
			
			
			x = RandomNumber.getRandom(Constants.GRID_SIZE);
			y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
			if(!player.hasBeenShot(x, y)){
				player.shoot(x, y);
				//player.printGrid(true);
			}
				
			if(player.getWinner()){
				break;
			}	
		}
	}

	
	public void basicPlay(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void basicWithBoatFinder(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void basicWithBoatFinderParity(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void basicWithSmartBoatFinder(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void basicWithSmartBoatFinderParity(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
