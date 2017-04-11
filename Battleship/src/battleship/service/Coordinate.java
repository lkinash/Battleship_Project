package battleship.service;

/**
 * @author Lindsey
 *
 */
public class Coordinate {

	private int x;
	
	private int y;
	
	public Coordinate(){
		
	}
	
	/**
	 * @param newX
	 * @param newY
	 */
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
	
	/**
	 * 	Print the coordinate called
	 */
	public void printCoordinate(){
		System.out.println("X: " + x + ", Y: " + y);
	}
}
