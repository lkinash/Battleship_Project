package battleship.game;

public interface Play {

	public boolean basicPlay(Player player);
	
	public void basicWithBoatFinder(Player player);
	
	public void basicWithBoatFinderParity(Player player);
	
	public void basicWithSmartBoatFinder(Player player);
		
	public void basicWithSmartBoatFinderParity(Player player);
			
}
