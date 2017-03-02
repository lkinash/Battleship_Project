package battleship.game;

public class Player {

	private boolean isHuman;
	
	private boolean winner;
	
	private Boat[] boats;
	
	private GridSquare[][] grid;
	
	public Player(boolean humanPlayer){
		
		this.isHuman = humanPlayer;
		this.boats = new Boat[5];
		this.grid = new GridSquare[10][10];
		this.winner = false;
	}
	
}
