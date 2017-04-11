package battleship.game;

import battleship.service.Coordinate;


/**
 * The Class GridSquare.
 *
 * @author Lindsey
 */
public class GridSquare {
	
	/** The boat. */
	private boolean boat;
	
	/** The shot. */
	private boolean shot;
	
	/** The probability. */
	private int probability;
	
	/**
	 * Instantiates a new grid square.
	 */
	public GridSquare(){
		
	}
	
	/**
	 * Instantiates a new grid square.
	 *
	 * @param hasBoat the has boat
	 * @param hasShot the has shot
	 * @param prob the prob
	 */
	public GridSquare(boolean hasBoat, boolean hasShot, int prob){
		
		this.boat = hasBoat;
		this.shot = hasShot;
		this.probability = prob;
		
	}
	
	/**
	 * Sets the boat.
	 *
	 * @param newBoat the new boat
	 */
	public void setBoat(boolean newBoat){
		this.boat = newBoat;	
	}
	
	/**
	 * Gets the boat.
	 *
	 * @return the boat
	 */
	public boolean getBoat(){
		return this.boat;
	}
	
	/**
	 * Sets the shot.
	 *
	 * @param newShot the new shot
	 */
	public void setShot(boolean newShot){
		this.shot = newShot;	
	}
	
	/**
	 * Gets the shot.
	 *
	 * @return the shot
	 */
	public boolean getShot(){
		return this.shot;
	}
	
	/**
	 * Sets the prob.
	 *
	 * @param newProb the new prob
	 */
	public void setProb(int newProb){
		this.probability = newProb;	
	}
	
	/**
	 * Gets the prob.
	 *
	 * @return the prob
	 */
	public int getProb(){
		return this.probability;
	}
	
}
