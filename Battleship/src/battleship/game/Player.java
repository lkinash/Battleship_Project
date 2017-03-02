package battleship.game;

import battleship.service.Constants;

public class Player {

	private boolean isHuman;
	
	private boolean winner;
	
	private Boat[] boats;
	
	private GridSquare[][] grid;
	
	public Player(boolean humanPlayer){
		
		this.isHuman = humanPlayer;

		this.boats = new Boat[Constants.BOAT_COUNT];
		createBoats();											//boat set in battleship is always the same.
		
		this.grid = new GridSquare[Constants.GRID_SIZE][Constants.GRID_SIZE];
		createGrid();
		
		this.winner = false;
		
	}
	
	public void createBoats(){
		
		boats[0] = new Boat(2, "Destroyer", null);
		boats[1] = new Boat(3, "Cruiser", null);
		boats[2] = new Boat(3, "Submarine", null);
		boats[3] = new Boat(4, "Battleship", null);
		boats[4] = new Boat(5, "Carrier", null);
		
	}
	
	public void createGrid(){
		
		for(int i = 0; i < Constants.GRID_SIZE; i++)
			for(int j = 0; j < Constants.GRID_SIZE; j++)
				grid[i][j] = new GridSquare(false, false);			//all squares start with no boat and no shot tried
				
	}
	
	
	
}
