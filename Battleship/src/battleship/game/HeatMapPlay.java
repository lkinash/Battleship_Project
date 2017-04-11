package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

/**
 * @author Lindsey
 *
 */
public class HeatMapPlay implements Play {

	public HeatMapPlay(){
		
	}
	
	/* (non-Javadoc)
	 * @see battleship.game.Play#checkWinner(battleship.game.Player)
	 */
	public boolean checkWinner(Player player){
		if(player.getWinner()){
			return true;
		}
		else 
			return false;
	}
	
	/* (non-Javadoc)
	 * @see battleship.game.Play#basicPlay(battleship.game.Player)
	 */
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
				return false;
			}
				
			if(player.getWinner()){
				return true;
			}	
		}
	}

	/* (non-Javadoc)
	 * @see battleship.game.Play#basicWithBoatFinder(battleship.game.Player)
	 */
	public boolean basicWithBoatFinder(Player player) {
		
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
				return false;
			}
				
			if(player.getWinner()){
				return true;
				
			}	
		}
		
	}

	/* (non-Javadoc)
	 * @see battleship.game.Play#basicWithBoatFinderParity(battleship.game.Player)
	 */
	public boolean basicWithBoatFinderParity(Player player) {
		// TODO Auto-generated method stub
		return false;
		
	}

	/* (non-Javadoc)
	 * @see battleship.game.Play#basicWithSmartBoatFinder(battleship.game.Player)
	 */
	public boolean basicWithSmartBoatFinder(Player player) {
		
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
					return false;
				}
					
				if(player.getWinner()){
					return true;
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
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see battleship.game.Play#basicWithSmartBoatFinderParity(battleship.game.Player)
	 */
	public boolean basicWithSmartBoatFinderParity(Player player) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
}
