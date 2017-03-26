package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

public class HeatMapPlay implements Play {

	public HeatMapPlay(){
		
	}
	
	public boolean basicPlay(Player player) {
		
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
		
		return false;
	}

	public void basicWithBoatFinder(Player player) {
		
		int x, y;
		Coordinate coordinate;
		
		while(true){
			
			coordinate = player.getHighestProbUnshotGreater();
			
			if(coordinate != null){
				x = coordinate.getX();
				y = coordinate.getY();
			}
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
			}
				
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

	public void basicWithBoatFinderParity(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void basicWithSmartBoatFinder(Player player) {
		
		int x, y;
		Coordinate coordinate;
		
		int randomCount = 0;
		
		while(true){
			
			coordinate = player.getHighestProbUnshotGreater();
			
			if(coordinate != null){
				x = coordinate.getX();
				y = coordinate.getY();
				
				
				if(!player.hasBeenShot(x, y)){
					player.shoot(x, y);
					player.addHitToList(x, y);
					//player.printGrid(true);
					player.updateProbSmart(x, y);
					//player.printProbability();
				}
					
				if(player.getWinner()){
					break;
				}	
				
			}
			else{
				
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
			
				randomCount++;
				
				if((x + y)%2 == 0 || randomCount > 50 ){	
					if(!player.hasBeenShot(x, y)){
						player.shoot(x, y);
						player.addHitToList(x, y);
						//player.printGrid(true);
						player.updateProbSmartAround();
						player.updateProbSmart(x, y);
						//player.printProbability();
						randomCount = 0;
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
			}
		}
		
	}

	public void basicWithSmartBoatFinderParity(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
