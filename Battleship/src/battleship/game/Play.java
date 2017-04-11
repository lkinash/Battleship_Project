package battleship.game;


/**
 * The Interface Play.
 *
 * @author Lindsey
 */
public interface Play {

	/**
	 * Check if the game has been won.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean checkWinner(Player player);
	
	/**
	 * Basic play.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean basicPlay(Player player);
	
	/**
	 * Basic with boat finder.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean basicWithBoatFinder(Player player);
	
	/**
	 * Basic with boat finder parity.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean basicWithBoatFinderParity(Player player);
	
	/**
	 * Basic with smart boat finder.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean basicWithSmartBoatFinder(Player player);
		
	/**
	 * Basic with smart boat finder parity.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean basicWithSmartBoatFinderParity(Player player);
			
}
