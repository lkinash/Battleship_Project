package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

public class RandomPlay implements Play {

	public RandomPlay(){
		
	}
	
	public void basicPlay(Player player){
			
		int x, y;
		
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
	
	public void basicWithBoatFinder(Player player){
		
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
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(y < 9){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						player.shoot(x , y + 1);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(y > 0){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						player.shoot(x , y - 1);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
			}
			else{
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
	}
	
	
	public void basicWithBoatFinderParity(Player player){
		
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
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(y < 9){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						player.shoot(x , y + 1);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(y > 0){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						player.shoot(x , y - 1);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
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
					}
				
					if(player.getWinner()){
						break;
					}	
				}
			}
		}
	}
	
	
	public void basicWithSmartBoatFinder(Player player){
		
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
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(y < 9 && !player.coordinateFromSunkBoat(top)  && bY){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						if(player.smartShoot(x , y + 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	

						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(x > 0 && !player.coordinateFromSunkBoat(top)  && bX){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						if(player.smartShoot(x - 1 , y)){
							if((!player.isHit(new Coordinate(x, y + 1))) && (!player.isHit(new Coordinate(x, y - 1))))
								bY = false;
						}	
							
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
			
				
				if(y > 0 && !player.coordinateFromSunkBoat(top) && bY){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						if(player.smartShoot(x , y - 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	
					}
				
					if(player.getWinner()){
						break;
					}	
				}
			}
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
				if(!player.hasBeenShot(x, y)){
					player.smartShoot(x, y);
					//player.printGrid(true);
				}
				
				if(player.getWinner()){
					break;
				}	
			}
		}
	}		
	

	public void basicWithSmartBoatFinderParity(Player player){
	
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
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(y < 9 && !player.coordinateFromSunkBoat(top)  && bY){
					if(!player.hasBeenShot(x , y + 1)){			//y + 1
						if(player.smartShoot(x , y + 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	

						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
				
				if(x > 0 && !player.coordinateFromSunkBoat(top)  && bX){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						if(player.smartShoot(x - 1 , y)){
							if((!player.isHit(new Coordinate(x, y + 1))) && (!player.isHit(new Coordinate(x, y - 1))))
								bY = false;
						}	
							
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}	
				}
			
				
				if(y > 0 && !player.coordinateFromSunkBoat(top) && bY){
					if(!player.hasBeenShot(x , y - 1)){			//y - 1
						if(player.smartShoot(x , y - 1)){
							if((!player.isHit(new Coordinate(x + 1 ,y ))) && (!player.isHit(new Coordinate(x - 1, y ))))
								bX = false;
						}	
					}
				
					if(player.getWinner()){
						break;
					}	
				}
			}
			
			else{
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
				if((x + y)%2 == 0){
					if(!player.hasBeenShot(x, y)){
						player.smartShoot(x, y);
						//player.printGrid(true);
					}
				
					if(player.getWinner()){
						break;
					}		
				}
			}
		}
		
	}

	

}

