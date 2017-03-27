package battleship.game;

public interface Play {

	public boolean checkWinner(Player player);
	
	public boolean basicPlay(Player player);
	
	public boolean basicWithBoatFinder(Player player);
	
	public boolean basicWithBoatFinderParity(Player player);
	
	public boolean basicWithSmartBoatFinder(Player player);
		
	public boolean basicWithSmartBoatFinderParity(Player player);
			
}
