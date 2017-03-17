package battleship.game;

import java.util.List;
import java.util.Stack;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.GridProb;
import battleship.service.RandomNumber;

public class Player {

	private boolean isHuman;
	
	private boolean winner;
	
	private int shotCount;
	
	private int hitCount;
	
	private Boat[] boats;
	
	private GridSquare[][] grid;
	
	private Stack<Coordinate> hits;
	
	
	public Player(boolean humanPlayer){
		
		this.isHuman = humanPlayer;

		this.shotCount = 0;

		this.hitCount = 0;
		
		this.boats = new Boat[Constants.BOAT_COUNT];
		createBoats();											//boat set in battleship is always the same.
		
		this.grid = new GridSquare[Constants.GRID_SIZE][Constants.GRID_SIZE];
		createGrid();
		
		this.winner = false;
		
		hits = new Stack<Coordinate>();
		

	}
	
	public void createBoats(){
		
		boats[0] = new Boat(5, "Carrier");
		boats[1] = new Boat(4, "Battleship");
		boats[2] = new Boat(3, "Cruiser");
		boats[3] = new Boat(3, "Submarine");
		boats[4] = new Boat(2, "Destroyer");
		
	}
	
	public void createGrid(){
		
		for(int i = 0; i < Constants.GRID_SIZE; i++)
			for(int j = 0; j < Constants.GRID_SIZE; j++)
				grid[i][j] = new GridSquare(false, false, GridProb.getDefaultSquareProb(i, j) );			//all squares start with no boat and no shot tried
				
	}
	
	public void printGrid(boolean hits){
		
		System.out.println("----------");
		
		for(int i = 0; i < Constants.GRID_SIZE; i++){
			for(int j = 0; j < Constants.GRID_SIZE; j++){
				if(grid[i][j].getBoat() && grid[i][j].getShot() && hits)
					System.out.print("■");
				else if(grid[i][j].getShot() && hits)
					System.out.print("x");
				else if(grid[i][j].getBoat())
					System.out.print("□");
				else
					System.out.print("-");
				
				System.out.print(" ");
			}
			
			System.out.println("");
			
		}	
		System.out.println("----------");
	}
	
	public void printProbability(){
		
		System.out.println("----------");
		
		for(int i = 0; i < Constants.GRID_SIZE; i++){
			for(int j = 0; j < Constants.GRID_SIZE; j++){
				
				System.out.print(grid[i][j].getProb() + " ");
			}
			
			System.out.println("");
			
		}	
		System.out.println("----------");
	}
	
	public boolean getBoatSunk(){
		
		for(int i = 0; i < Constants.BOAT_COUNT; i++){
			
			int hitCount = 0;
			List<Coordinate> coordinates = boats[i].getCoordinatesList();
			
			for(Coordinate temp: coordinates){
				
				if(isHit(temp))
					hitCount++;
				
			}
			
			if(hitCount == boats[i].getLength() && !boats[i].getSunk()){
				boats[i].setSunk(true);
				//System.out.println("Sunk: " + boats[i].getName());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isHit(Coordinate coordinate){
		
		if(coordinate.getX() < 0 || coordinate.getX() > 9 || coordinate.getY() < 0 || coordinate.getY() > 9){
			return false;
		}
		if(grid[coordinate.getX()][coordinate.getY()].getShot() && grid[coordinate.getX()][coordinate.getY()].getBoat())
			return true;
		else
			return false;
		
	}
	
	public void placeBoats(){
		
		int x, y;
		
		for(int i = 0; i < Constants.BOAT_COUNT; i++){
			
			while(true){
				x = RandomNumber.getRandom(Constants.GRID_SIZE);
				y = RandomNumber.getRandom(Constants.GRID_SIZE);
			
				if(RandomNumber.getRandom(2) < 1){
					if(tryPlacingBoatsX(x, y, i))
						break;
				}
				else
					if(tryPlacingBoatsY(x, y, i))
						break;
			}
		}
	}
	
	private boolean tryPlacingBoatsY(int x, int y, int i){
		
		boolean placeable = true;
		
		if(y + boats[i].getLength() < Constants.GRID_SIZE){
			
			for(int j = 0; j < boats[i].getLength(); j++){
				if(grid[x][y + j].getBoat()){
					placeable = false;
				}
			}
			if(placeable){
				for(int j = 0; j < boats[i].getLength(); j++){
					grid[x][y + j].setBoat(true);
					boats[i].addCoordinates(new Coordinate(x, y+j));
				}
				return true;
			}
				
		}
		
		return false;
	}
	
	private boolean tryPlacingBoatsX(int x, int y, int i){
		
		boolean placeable = true;
		
		if(x + boats[i].getLength() < Constants.GRID_SIZE){
			
			for(int j = 0; j < boats[i].getLength(); j++){
				if(grid[x+j][y].getBoat()){
					placeable = false;
				}
			}
			if(placeable){
				for(int j = 0; j < boats[i].getLength(); j++){
					grid[x+j][y].setBoat(true);
					boats[i].addCoordinates(new Coordinate(x+j, y));
				}
				return true;
			}
					
		}
		
		return false;
	}
	

	public boolean hasBeenShot(int x, int y){
		return grid[x][y].getShot();
	}
	
	public boolean shoot(int x, int y){
		
		grid[x][y].setShot(true);
		shotCount++;
		
		if(grid[x][y].getBoat()){
			hitCount++;
			hits.push(new Coordinate(x, y));
			return true;
		}
		
		return false;
		
	}
	
	
	public boolean smartShoot(int x, int y){
		
		grid[x][y].setShot(true);
		shotCount++;
		
		if(grid[x][y].getBoat()){
			hitCount++;
			if(!getBoatSunk()){
				hits.push(new Coordinate(x, y));
				//new Coordinate(x, y).printCoordinate();
			}

			return true;
		}
		
		return false;
		
	}
	
	public boolean coordinateFromSunkBoat(Coordinate coordinate){
	
		for(int i = 0; i < Constants.BOAT_COUNT; i++){
			
			if(boats[i].getSunk()){
				
				List<Coordinate> coordinateList = boats[i].getCoordinatesList();
			
				for(Coordinate temp: coordinateList){
				
					if(temp.getX() == coordinate.getX() && temp.getY() == coordinate.getY()){
						//System.out.println("True");
						//coordinate.printCoordinate();
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	
	public boolean getWinner(){
		if(hitCount > 16){
			this.winner = true;
		}
		if(boats[0].getSunk() && boats[1].getSunk() && boats[2].getSunk() && boats[3].getSunk() && boats[4].getSunk()){
			this.winner = true;
		}
		
		return this.winner;
	}
	
	public Coordinate getHighestProbUnshot(){
		
		int prob = 0;
		Coordinate result = new Coordinate(0, 0);
		
		for(int i = 0; i < Constants.GRID_SIZE; i++){
			for(int j = 0; j < Constants.GRID_SIZE; j++){
				if(grid[i][j].getProb() > prob){
					result = new Coordinate(i,j);
					prob = grid[i][j].getProb();
				}
			}
		}
		
		return result;
	}
	
	public int getShotCount(){
		return this.shotCount;
	}
	
	public boolean getStackIsEmpty(){
		return hits.isEmpty();
	}
	
	public Coordinate getStackTop(){
		return hits.pop();
	}
	
}
