package battleship.game;

import battleship.service.Coordinate;

public class GridSquare {
	
	private boolean boat;
	
	private boolean shot;
	
	private int probability;
	
	public GridSquare(){
		
	}
	
	public GridSquare(boolean hasBoat, boolean hasShot, int prob){
		
		this.boat = hasBoat;
		this.shot = hasShot;
		this.probability = prob;
		
	}
	
	public void setBoat(boolean newBoat){
		this.boat = newBoat;	
	}
	
	public boolean getBoat(){
		return this.boat;
	}
	
	public void setShot(boolean newShot){
		this.shot = newShot;	
	}
	
	public boolean getShot(){
		return this.shot;
	}
	
	public void setProb(int newProb){
		this.probability = newProb;	
	}
	
	public int getProb(){
		return this.probability;
	}
	
}
