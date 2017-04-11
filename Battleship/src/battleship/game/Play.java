package battleship.game;

/**
 * @author Lindsey
 *
 */
public interface Play {

	/**
	 * Check if the game has been won
	 * @param player
	 * @return
	 */
	public boolean checkWinner(Player player);
	
	/**
	 * @param player
	 * @return
	 */
	public boolean basicPlay(Player player);
	
	/**
	 * @param player
	 * @return
	 */
	public boolean basicWithBoatFinder(Player player);
	
	/**
	 * @param player
	 * @return
	 */
	public boolean basicWithBoatFinderParity(Player player);
	
	/**
	 * @param player
	 * @return
	 */
	public boolean basicWithSmartBoatFinder(Player player);
		
	/**
	 * @param player
	 * @return
	 */
	public boolean basicWithSmartBoatFinderParity(Player player);
			
}
