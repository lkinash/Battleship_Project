package battleship.game;

import battleship.service.Coordinate;

public class GridSquare {
	
	private boolean boat;
	
	private boolean shot;
	
	public GridSquare(){
		
	}
	
	public GridSquare(boolean hasBoat, boolean hasShot){
		
		this.boat = hasBoat;
		this.shot = hasShot;
		
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
	
}
