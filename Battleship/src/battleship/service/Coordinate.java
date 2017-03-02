package battleship.service;

public class Coordinate {

	private int x;
	
	private int y;
	
	public Coordinate(){
		
	}
	
	public Coordinate(int newX, int newY){
	
		this.x = newX;
		this.y = newY;
	
	}
	
	public void setX(int newX){
		this.x = newX;	
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setY(int newY){
		this.y = newY;	
	}
	
	public int getY(){
		return this.y;
	}
}
