package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;


/**
 * The Class RandomPlay.
 *
 * @author Lindsey
 */
public class RandomPlay implements Play {

	/**
	 * Instantiates a new random play.
	 */
	public RandomPlay(){
		
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
	public boolean basicPlay(Player player){
			
		int x, y;
		
		while(true){
						
			x = RandomNumber.getRandom(Constants.GRID_SIZE);
			y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
			if(!player.hasBeenShot(x, y)){
				player.shoot(x, y);
				//player.printGrid(true);
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
	public boolean basicWithBoatFinder(Player player){
		
		int x, y;
		
		while(true){
					
			if(!player.getStackIsEmpty()){
				Coordinate top = player.getStackTop();
				
				x = top.getX();
				y = top.getY();
				
				if(x < 9){
					if(!player.hasBeenShot(x + 1, y)){			//x + 1
						player.shoot(x + 1 , y);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(y < 9){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						player.shoot(x , y + 1);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(y > 0){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						player.shoot(x , y - 1);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
			}
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
				if(!player.hasBeenShot(x, y)){
					player.shoot(x, y);
					//player.printGrid(true);
					return false;
				}
				
				if(player.getWinner()){
					return true;
				}	
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see battleship.game.Play#basicWithBoatFinderParity(battleship.game.Player)
	 */
	public boolean basicWithBoatFinderParity(Player player){
		
		int x, y;
		
		while(true){
					
			if(!player.getStackIsEmpty()){
				Coordinate top = player.getStackTop();
				
				x = top.getX();
				y = top.getY();
				
				if(x < 9){
					if(!player.hasBeenShot(x + 1, y)){			//x + 1
						player.shoot(x + 1 , y);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(y < 9){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						player.shoot(x , y + 1);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(y > 0){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						player.shoot(x , y - 1);
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				
			}
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
				if((x + y)%2 == 0 ){
					if(!player.hasBeenShot(x, y)){
						player.shoot(x, y);
						//player.printGrid(true);
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
	 * @see battleship.game.Play#basicWithSmartBoatFinder(battleship.game.Player)
	 */
	public boolean basicWithSmartBoatFinder(Player player){
		
		int x, y;
		boolean bX, bY;				//boolean x and y 
		
		while(true){
					
			if(!player.getStackIsEmpty()){
				Coordinate top = player.getStackTop();
				
				x = top.getX();
				y = top.getY();
				
				bX = true;
				bY = true;
				
				if(x < 9 && !player.coordinateFromSunkBoat(top) && bX){
					if(!player.hasBeenShot(x + 1, y)){			//x + 1
						if(player.smartShoot(x + 1 , y)){
							if((!player.isHit(new Coordinate(x, y + 1))) && (!player.isHit(new Coordinate(x, y - 1))))
								bY = false;
						}	
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(y < 9 && !player.coordinateFromSunkBoat(top)  && bY){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						if(player.smartShoot(x , y + 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	

						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(x > 0 && !player.coordinateFromSunkBoat(top)  && bX){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						if(player.smartShoot(x - 1 , y)){
							if((!player.isHit(new Coordinate(x, y + 1))) && (!player.isHit(new Coordinate(x, y - 1))))
								bY = false;
						}	
							
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
			
				
				if(y > 0 && !player.coordinateFromSunkBoat(top) && bY){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						if(player.smartShoot(x , y - 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				 
			}
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
				if(!player.hasBeenShot(x, y)){
					player.smartShoot(x, y);
					//player.printGrid(true);
					return false;
				}
				
				if(player.getWinner()){
					return true;
				}	
			}
		}
	}		
	

	/* (non-Javadoc)
	 * @see battleship.game.Play#basicWithSmartBoatFinderParity(battleship.game.Player)
	 */
	public boolean basicWithSmartBoatFinderParity(Player player){
	
		int x, y;
		boolean bX, bY;				//boolean x and y 
		
		int randomCount = 0;
		
		while(true){
					
			if(!player.getStackIsEmpty()){
				Coordinate top = player.getStackTop();
				
				x = top.getX();
				y = top.getY();
				
				bX = true;
				bY = true;
				
				if(x < 9 && !player.coordinateFromSunkBoat(top) && bX){
					if(!player.hasBeenShot(x + 1, y)){			//x + 1
						if(player.smartShoot(x + 1 , y)){
							if((!player.isHit(new Coordinate(x, y + 1))) && (!player.isHit(new Coordinate(x, y - 1))))
								bY = false;
						}	
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(y < 9 && !player.coordinateFromSunkBoat(top)  && bY){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						if(player.smartShoot(x , y + 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	

						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				if(x > 0 && !player.coordinateFromSunkBoat(top)  && bX){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						if(player.smartShoot(x - 1 , y)){
							if((!player.isHit(new Coordinate(x, y + 1))) && (!player.isHit(new Coordinate(x, y - 1))))
								bY = false;
						}	
							
						//player.printGrid(true);
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
			
				
				if(y > 0 && !player.coordinateFromSunkBoat(top) && bY){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						if(player.smartShoot(x , y - 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	
						player.pushStackTop(top);
						return false;
					}
				
					if(player.getWinner()){
						return true;
					}	
				}
				
				
			
			}
			
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
				randomCount++;
				
				if((x + y)%2 == 0 || randomCount > 50 ){	
					if(!player.hasBeenShot(x, y)){
						player.smartShoot(x, y);
						//player.printGrid(true);
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

	

}

