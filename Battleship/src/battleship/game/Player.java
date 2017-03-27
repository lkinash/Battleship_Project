
package battleship.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.GridProb;
import battleship.service.RandomNumber;
import battleship.service.Status;

public class Player {

	private boolean isHuman;
	
	private boolean winner;
	
	private int shotCount;
	
	private int hitCount;
	
	private Boat[] boats;
	
	private GridSquare[][] grid;
	
	private Stack<Coordinate> hits;
	
	private List<Coordinate> hitList;
	
	private List<Coordinate> sunkBoatHitList;
	
	
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
		
		hitList = new ArrayList<Coordinate>();
		sunkBoatHitList = new ArrayList<Coordinate>();
		

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
				List<Coordinate> coordinate = boats[i].getCoordinatesList();
				
				for(Coordinate temp: coordinate){
					sunkBoatHitList.add(temp);
				}
				
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
	
	public void placeBoatsSet(){
		
		int x, y;
		
		tryPlacingBoatsY(0, 3, 0);
		tryPlacingBoatsY(9, 3, 1);
		tryPlacingBoatsX(4, 4, 2);
		tryPlacingBoatsX(3, 7, 3);
		tryPlacingBoatsX(6, 8, 4);
		
		
	}
	
	private boolean tryPlacingBoatsY(int x, int y, int i){
		
		boolean placeable = true;
		
		if(y + boats[i].getLength() <= Constants.GRID_SIZE){
			
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
		
		if(x + boats[i].getLength() <= Constants.GRID_SIZE){
			
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
						addToSunkBoatList(i);
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	
	public void addToSunkBoatList(int i){
		
		if(boats[i].getSunk()){
			
			List<Coordinate> coordinateList = boats[i].getCoordinatesList();
		
			for(Coordinate temp: coordinateList){
			
				sunkBoatHitList.add(temp);
			}
		}
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
	
	public Coordinate getHighestProbUnshotGreater(){
		
		int prob = 0;
		Coordinate result = new Coordinate(0, 0);
		
		for(int i = 0; i < Constants.GRID_SIZE; i++){
			for(int j = 0; j < Constants.GRID_SIZE; j++){
				if(grid[i][j].getProb() > prob){
					result = new Coordinate(i,j);
					prob = grid[i][j].getProb();
				}
				if(grid[i][j].getProb() == prob){
					if(RandomNumber.getRandom(1) == 1){
						result = new Coordinate(i,j);
						prob = grid[i][j].getProb();
					}
				}
			}
		}

		
		if(prob > 1){
			
			//System.out.println("Prob: " + prob);
			return result;
		}
		else
			return null;
	}
	
	
	public void updateProb(int x, int y){
		
		grid[x][y].setProb(0);
		
		if(grid[x][y].getBoat()){
			if(getBoatSunk()){
			
				decreaseProbAroundBoat(x,y);
				
			}
			else{
				
				increaseProbAround(x, y);

			}
				
		}
		else{
			decreaseProbAround(x, y);

		}
			
		
	}
	
	
	public void updateProbSmart(int x, int y){
		
		grid[x][y].setProb(0);
		
		if(grid[x][y].getBoat()){
			if(getBoatSunk()){
			
				decreaseProbAroundBoat(x,y);
			
				
			}
			else{
				
				increaseProbAround(x, y);

			}
				
		}
		else{
			decreaseProbAround(x, y);

		}
		

		
		
	}
	
	public void updateProbSmartAround(){
	
		boolean found = false;
		
		for(Coordinate temp: hitList){
			
			found = false;
			
			for(Coordinate tempSunk: sunkBoatHitList){
				
				
				if(temp.getX() == tempSunk.getX() && temp.getY() == tempSunk.getY()){
					found = true;

				}
				
				
			}
			
			if(!found){
					
				int s = temp.getX();
				int t = temp.getY();
						
				increaseProbAround(s,t);

			}
		}
			
	}

	
	public void addHitToList(int x, int y){
		if(grid[x][y].getShot() && grid[x][y].getBoat()){
			hitList.add(new Coordinate(x, y));
		}
	}
	
	public void increaseProbAround(int x, int y){
		
		int var = 2;
		
		if(x < 9 ){
			if(grid[x + 1][y].getProb() < 10){
				var = 3;
			}
			else{
				var = 2;
			}
			grid[x + 1][y].setProb(var * (grid[x + 1][y].getProb()));
		}

		if(x > 0){
			if(grid[x - 1][y].getProb() < 10){
				var = 3;
			}
			else{
				var = 2;
			}
			grid[x - 1][y].setProb(var * (grid[x - 1][y].getProb()));
		}
		
		if(y < 9){
			if(grid[x][y + 1].getProb() < 10){
				var = 3;
			}
			else{
				var = 2;
			}
			grid[x][y + 1].setProb(var * (grid[x][y + 1].getProb()));
		}
		
		if(y > 0){
			if(grid[x][y - 1].getProb() < 10){
				var = 3;
			}
			else{
				var = 2;
			}
			grid[x][y - 1].setProb(var * (grid[x][y - 1].getProb()));
		}
		
	}
	
	
	public void decreaseProbAround(int x, int y){
	
		//	1 2 3 7 12 
		
		
		for(int i = 1; i < 6; i++){
			if(x < (Constants.GRID_SIZE - i)){
				if(!(grid[x + i][y].getProb() == 0)){
					grid[x + i][y].setProb((grid[x + i][y].getProb()) - distDecrease(i));
					if(grid[x + i][y].getProb() < 1 && !(grid[x + i][y].getShot()))
						grid[x + i][y].setProb(1);
				}
			}
		}
		
		for(int i = 1; i < 6; i++){
			if(x >= i){
				if(!(grid[x - i][y].getProb() == 0)){
					grid[x - i][y].setProb((grid[x - i][y].getProb()) - distDecrease(i));
					if(grid[x - i][y].getProb() < 1 && !(grid[x - i][y].getShot()))
						grid[x - i][y].setProb(1);
				}
			}
		}

		for(int i = 1; i < 6; i++){
			if(y < (Constants.GRID_SIZE - i)){
				if(!(grid[x][y + i].getProb() == 0)){
					grid[x][y + i].setProb((grid[x][y + i].getProb()) - distDecrease(i));
					if(grid[x][y + i].getProb() < 1 && !(grid[x][y + i].getShot()))
						grid[x][y + i].setProb(1);
				}
			}
		}
		
		for(int i = 1; i < 6; i++){
			if(y >= i){
				if(!(grid[x][y - i].getProb() == 0)){
					grid[x][y - i].setProb((grid[x][y - i].getProb()) - distDecrease(i));
					if(grid[x][y - i].getProb() < 1 && !(grid[x][y - i].getShot()))
						grid[x][y - i].setProb(1);
				}
			}
		}
		
	}
	
	public void decreaseProbAroundBoat(int a, int b){
	
		Boat tempBoat = getSunkBoatAtGridSpot(new Coordinate(a,b));
		int x, y;
		
		if(tempBoat != null){
			List<Coordinate> list = tempBoat.getCoordinatesList();
			
			for(Coordinate temp: list){
				
				x = temp.getX();
				y = temp.getY();
				
				if(x < 9 ){
					grid[x + 1][y].setProb((grid[x + 1][y].getProb() / 2));
					if(grid[x + 1][y].getProb() < 1 && !(grid[x + 1][y].getShot()))
						grid[x + 1][y].setProb(1);
				}

				if(x > 0){
					grid[x - 1][y].setProb((grid[x - 1][y].getProb() / 2));
					if(grid[x - 1][y].getProb() < 1 && !(grid[x - 1][y].getShot()))
						grid[x - 1][y].setProb(1);
				}
				
				if(y < 9){
					grid[x][y + 1].setProb((grid[x][y + 1].getProb() / 2));
					if(grid[x][y + 1].getProb() < 1 && !(grid[x][y + 1].getShot()))
						grid[x][y + 1].setProb(1);
				}
				
				if(y > 0){
					grid[x][y - 1].setProb((grid[x][y - 1].getProb() / 2));
					if(grid[x][y - 1].getProb() < 1 && !(grid[x][y - 1].getShot()))
						grid[x][y - 1].setProb(1);
				}
			}
		}
		
	}
	
	public Boat getSunkBoatAtGridSpot(Coordinate coordinate){
		
		for(int i = 0; i < Constants.BOAT_COUNT; i++){
			if(boats[i].getIsInCoordinateList(coordinate))
				return boats[i];
		}
		
		return null;
		
	}
	
	public int distDecrease(int i){
		
		if(i == 1)
			return 12;
		else if(i == 2)
			return 7;
		else if(i == 3)
			return 3;
		else if(i == 4)
			return 2;
		else if(i == 5)
			return 1;
		else
			return 0;
		
	}
	
	public Status getSquareStatus(int x, int y){
		
		if(grid[x][y].getBoat()){
		
			if(grid[x][y].getShot()){
				return Status.HIT;
			}
			else{
				return Status.BOAT;
			}
		}
		else{
			
			if(grid[x][y].getShot()){
				return Status.MISS;
			}
			else{
				return Status.EMPTY;
			}
		}
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
	
	public void pushStackTop(Coordinate coordinate){
		hits.push(coordinate);
	}
	
}
