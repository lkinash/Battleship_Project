package battleship.game;

import battleship.service.Coordinate;

/**
 * @author Lindsey
 *
 */
public class GridSquare {
	
	private boolean boat;
	
	private boolean shot;
	
	private int probability;
	
	public GridSquare(){
		
	}
	
	/**
	 * @param hasBoat
	 * @param hasShot
	 * @param prob
	 */
	public GridSquare(boolean hasBoat, boolean hasShot, int prob){
		
		this.boat = hasBoat;
		this.shot = hasShot;
		this.probability = prob;
		
	}
	
	/**
	 * @param newBoat
	 */
	public void setBoat(boolean newBoat){
		this.boat = newBoat;	
	}
	
	/**
	 * @return
	 */
	public boolean getBoat(){
		return this.boat;
	}
	
	/**
	 * @param newShot
	 */
	public void setShot(boolean newShot){
		this.shot = newShot;	
	}
	
	/**
	 * @return
	 */
	public boolean getShot(){
		return this.shot;
	}
	
	/**
	 * @param newProb
	 */
	public void setProb(int newProb){
		this.probability = newProb;	
	}
	
	/**
	 * @return
	 */
	public int getProb(){
		return this.probability;
	}
	
}
