package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

public class HeatMapPlay implements Play {

	public HeatMapPlay(){
		
	}
	
	public void basicPlay(Player player) {
		
		int x, y;
		Coordinate coordinate;
		
		while(true){
			
			coordinate = player.getHighestProbUnshot();
			x = coordinate.getX();
			y = coordinate.getY();
				
			if(!player.hasBeenShot(x, y)){
				player.shoot(x, y);
				//player.printGrid(true);
				player.updateProb(x, y);
				//player.printProbability();
			}
				
			if(player.getWinner()){
				break;
			}	
		}
		
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
