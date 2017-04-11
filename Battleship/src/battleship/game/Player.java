
package battleship.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import battleship.service.Constants;
import battleship.service.Coordinate;
import battleship.service.GridProb;
import battleship.service.RandomNumber;
import battleship.service.Status;

/**
 * The Class Player.
 *
 * @author Lindsey
 */
public class Player {

	/** The is human. */
	private boolean isHuman;
	
	/** The winner. */
	private boolean winner;
	
	/** The shot count. */
	private int shotCount;
	
	/** The hit count. */
	private int hitCount;
	
	/** The boats. */
	private Boat[] boats;
	
	/** The grid. */
	private GridSquare[][] grid;
	
	/** The hits. */
	private Stack<Coordinate> hits;
	
	/** The hit list. */
	private List<Coordinate> hitList;
	
	/** The sunk boat hit list. */
	private List<Coordinate> sunkBoatHitList;
	
	
	/**
	 * Instantiates a new player.
	 *
	 * @param humanPlayer the human player
	 */
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
	
	/**
	 * Creates the five boats that are default.
	 */
	public void createBoats(){
		
		boats[0] = new Boat(5, "Carrier");
		boats[1] = new Boat(4, "Battleship");
		boats[2] = new Boat(3, "Cruiser");
		boats[3] = new Boat(3, "Submarine");
		boats[4] = new Boat(2, "Destroyer");
		
	}
	
	/**
	 * Creates the game grid.
	 */
	public void createGrid(){
		
		for(int i = 0; i < Constants.GRID_SIZE; i++)
			for(int j = 0; j < Constants.GRID_SIZE; j++)
				grid[i][j] = new GridSquare(false, false, GridProb.getDefaultSquareProb(i, j) );			//all squares start with no boat and no shot tried
				
	}
	
	/**
	 * prints teh grid nicely in command line.
	 *
	 * @param hits the hits
	 */
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
	
	
	/**
	 * 	Prints the probability values for each square.
	 */
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
	
	
	/**
	 * Tests if a boat is sunk.
	 *
	 * @return the boat sunk
	 */
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
	
	
	/**
	 * Tests a sqaure fr a hit .
	 *
	 * @param coordinate the coordinate
	 * @return true, if is hit
	 */
	public boolean isHit(Coordinate coordinate){
		
		if(coordinate.getX() < 0 || coordinate.getX() > 9 || coordinate.getY() < 0 || coordinate.getY() > 9){
			return false;
		}
		if(grid[coordinate.getX()][coordinate.getY()].getShot() && grid[coordinate.getX()][coordinate.getY()].getBoat())
			return true;
		else
			return false;
		
	}
	
	
	/**
	 * 	Places a boat at random.
	 */
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
	
	
	/**
	 * 	Allows static placement of the boats.
	 */
	public void placeBoatsSet(){

		tryPlacingBoatsY(0, 3, 0);
		tryPlacingBoatsY(9, 3, 1);
		tryPlacingBoatsX(4, 4, 2);
		tryPlacingBoatsX(3, 7, 3);
		tryPlacingBoatsX(6, 8, 4);
		
		
	}
	
	/**
	 * TRies to place the boats, testing for an overlap .
	 *
	 * @param x the x
	 * @param y the y
	 * @param i the i
	 * @return true, if successful
	 */
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
	
	/**
	 * TRies to place the boats, testing for an overlap .
	 *
	 * @param x the x
	 * @param y the y
	 * @param i the i
	 * @return true, if successful
	 */
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
	

	/**
	 * Test if a square has been shot at .
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean hasBeenShot(int x, int y){
		return grid[x][y].getShot();
	}
	
	/**
	 * Shoots at a square.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
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
	
	
	/**
	 * Shoots and updates the stack for smart shoot.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
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
	
	/**
	 * Returns the just sunk boat.
	 *
	 * @param coordinate the coordinate
	 * @return true, if successful
	 */
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
	
	/**
	 * Adds a new sunk boat to list of sunk boats.
	 *
	 * @param i the i
	 */
	public void addToSunkBoatList(int i){
		
		if(boats[i].getSunk()){
			
			List<Coordinate> coordinateList = boats[i].getCoordinatesList();
		
			for(Coordinate temp: coordinateList){
			
				sunkBoatHitList.add(temp);
			}
		}
	}
	
	/**
	 * Tests if a player has won.
	 *
	 * @return the winner
	 */
	public boolean getWinner(){
		if(hitCount > 16){
			this.winner = true;
		}
		if(boats[0].getSunk() && boats[1].getSunk() && boats[2].getSunk() && boats[3].getSunk() && boats[4].getSunk()){
			this.winner = true;
		}
		
		return this.winner;
	}
	
	
	/**
	 * Finds the highest probability unshot square to shoot at next .
	 *
	 * @return the highest prob unshot
	 */
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
	
	
	/**
	 * Finds the highest probability unshot square with a smart adjustment.
	 *
	 * @return the highest prob unshot greater
	 */
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
	
	
	/**
	 * Updates squares probability .
	 *
	 * @param x the x
	 * @param y the y
	 */
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
	
	
	/**
	 * The update probability for the smart option.
	 *
	 * @param x the x
	 * @param y the y
	 */
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
	
	/**
	 * Updates probability around a square.
	 */
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

	
	/**
	 * Add to the list of hits.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void addHitToList(int x, int y){
		if(grid[x][y].getShot() && grid[x][y].getBoat()){
			hitList.add(new Coordinate(x, y));
		}
	}
	
	/**
	 * adds probability value of sqaures around.
	 *
	 * @param x the x
	 * @param y the y
	 */
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
	
	
	/**
	 * decreases probability value of squares around.
	 *
	 * @param x the x
	 * @param y the y
	 */
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
	
	/**
	 * Changes probability around a sunk boat.
	 *
	 * @param a the a
	 * @param b the b
	 */
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
	
	/**
	 * Tests which boat is sunk at a spot.
	 *
	 * @param coordinate the coordinate
	 * @return the sunk boat at grid spot
	 */
	public Boat getSunkBoatAtGridSpot(Coordinate coordinate){
		
		for(int i = 0; i < Constants.BOAT_COUNT; i++){
			if(boats[i].getIsInCoordinateList(coordinate))
				return boats[i];
		}
		
		return null;
		
	}
	
	/**
	 * Get the amount of probability to decrease based on the distance from the miss.
	 *
	 * @param i the i
	 * @return the int
	 */
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
	
	/**
	 * Tests if a square is hit or a miss or unshot.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the square status
	 */
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
	
	/**
	 * Gets the shot count.
	 *
	 * @return the shot count
	 */
	public int getShotCount(){
		return this.shotCount;
	}
	
	/**
	 * Gets the stack is empty.
	 *
	 * @return the stack is empty
	 */
	public boolean getStackIsEmpty(){
		return hits.isEmpty();
	}
	
	/**
	 * Gets the stack top.
	 *
	 * @return the stack top
	 */
	public Coordinate getStackTop(){
		return hits.pop();
	}
	
	/**
	 * Push stack top.
	 *
	 * @param coordinate the coordinate
	 */
	public void pushStackTop(Coordinate coordinate){
		hits.push(coordinate);
	}
	
}
