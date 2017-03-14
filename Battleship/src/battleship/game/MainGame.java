package battleship.game;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.RandomNumber;

public class MainGame {

	Player player;
	Player player2;
	
	public MainGame(){
		
	}
	
	public MainGame(boolean humanPlayer){
		
		//player = new Player(humanPlayer);		
		//player2 = new Player(false);					//player 2 is always a computer player
	
	}
	
	public void run(){
	
		
		for(int i = 0; i < 1; i++){
		
		player = new Player(false);
		player.createBoats();
		player.createGrid();
		player.placeBoats();
		player.printGrid(true);

		
			//randomPlay();
			//randomWithBoatFinder();
			//randomWithBoatFinderParity();
			randomWithSmartBoatFinder();
			//randomWithSmartBoatFinderParity();
		
			System.out.println(player.getShotCount());	
		}
	}

	
	public void randomPlay(){
			
		int x, y;
		
		while(true){
						
			x = RandomNumber.getRandom(Constants.GRID_SIZE);
			y = RandomNumber.getRandom(Constants.GRID_SIZE);
				
			if(!player.hasBeenShot(x, y)){
				player.shoot(x, y);
				player.printGrid(true);
				player.getBoatSunk();
			}
				
			if(player.getWinner()){
				break;
			}	
		}
	}
	
	public void randomWithBoatFinder(){
		
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
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
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
	
	
	public void randomWithBoatFinderParity(){
		
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
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
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
	
	
	public void randomWithSmartBoatFinder(){
			
			int x, y;
			
			while(true){
						
				if(!player.getStackIsEmpty()){
					Coordinate top = player.getStackTop();
					
					x = top.getX();
					y = top.getY();
					
					if(x < 9 && !player.coordinateFromSunkBoat(top)){
						if(!player.hasBeenShot(x + 1, y)){			//x + 1
							player.smartShoot(x + 1 , y);
							player.printGrid(true);
						}
					
						if(player.getWinner()){
							break;
						}	
					}
					
					if(x > 0 && !player.coordinateFromSunkBoat(top)){
						if(!player.hasBeenShot(x - 1, y)){				//x - 1
							player.smartShoot(x - 1 , y);
							player.printGrid(true);
						}
					
						if(player.getWinner()){
							break;
						}	
					}
					
					if(y < 9 && !player.coordinateFromSunkBoat(top)){
						if(!player.hasBeenShot(x , y + 1)){			//y + 1
							player.smartShoot(x , y + 1);
							player.printGrid(true);
						}
					
						if(player.getWinner()){
							break;
						}	
					}
					
					if(y > 0 && !player.coordinateFromSunkBoat(top)){
						if(!player.hasBeenShot(x , y - 1)){			//y - 1
							player.smartShoot(x , y - 1);
							player.printGrid(true);
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
						player.printGrid(true);
					}
					
					if(player.getWinner()){
						break;
					}	
				}
			}
		}		
		
	
	public void randomWithSmartBoatFinderParity(){
		
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
				
				if(x > 0){
					if(!player.hasBeenShot(x - 1, y)){				//x - 1
						player.shoot(x - 1 , y);
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
	
	
}
