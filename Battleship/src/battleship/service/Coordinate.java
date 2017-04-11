package battleship.service;


/**
 * The Class Coordinate.
 *
 * @author Lindsey
 */
public class Coordinate {

	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/**
	 * Instantiates a new coordinate.
	 */
	public Coordinate(){
		
	}
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param newX the new X
	 * @param newY the new Y
	 */
	public Coordinate(int newX, int newY){
	
		this.x = newX;
		this.y = newY;
	
	}
	
	/**
	 * Sets the x.
	 *
	 * @param newX the new x
	 */
	public void setX(int newX){
		this.x = newX;	
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param newY the new y
	 */
	public void setY(int newY){
		this.y = newY;	
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * 	Print the coordinate called.
	 */
	public void printCoordinate(){
		System.out.println("X: " + x + ", Y: " + y);
	}
}
