package battleship.game;

import java.util.ArrayList;
import java.util.List;

import battleship.service.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class Boat.
 *
 * @author Lindsey
 */
public class Boat {

	/** The length. */
	private int length;
	
	/** The name. */
	private String name;
	
	/** The sunk. */
	private boolean sunk;
	
	/** 	The set of coordinates the boat is on. */
	private List<Coordinate> coordinates;
	
	/**
	 * Instantiates a new boat.
	 */
	public Boat(){
		
	}
	
	/**
	 * Instantiates a new boat.
	 *
	 * @param newLength the new length
	 * @param newName the new name
	 */
	public Boat(int newLength, String newName){
		
		this.length = newLength;
		this.name = newName;
		this.coordinates = new ArrayList<Coordinate>() ;
		this.sunk = false;
		
	}
	
	/**
	 * Sets the length.
	 *
	 * @param newLength the new length
	 */
	public void setLength(int newLength){
		this.length = newLength;	
	}
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param newName the new name
	 */
	public void setName(String newName){
		this.name = newName;	
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the sunk.
	 *
	 * @param newSunk the new sunk
	 */
	public void setSunk(boolean newSunk){
		this.sunk = newSunk;	
	}
	
	/**
	 * Gets the sunk.
	 *
	 * @return the sunk
	 */
	public boolean getSunk(){
		return this.sunk;
	}
	
	/**
	 * Adds the coordinates.
	 *
	 * @param newCoordinates the new coordinates
	 */
	public void addCoordinates(Coordinate newCoordinates){
		this.coordinates.add(newCoordinates);	
	}
	
	/**
	 * Sets the coordinates.
	 *
	 * @param newCoordinates the new coordinates
	 */
	public void setCoordinates(List<Coordinate> newCoordinates){
		this.coordinates = newCoordinates;	
	}
	
	/**
	 * Gets the coordinates list.
	 *
	 * @return the coordinates list
	 */
	public List<Coordinate> getCoordinatesList(){
		return this.coordinates;
	}
	
	/**
	 * Gets the checks if is in coordinate list.
	 *
	 * @param coordinate the coordinate
	 * @return the checks if is in coordinate list
	 */
	public boolean getIsInCoordinateList(Coordinate coordinate){
		
		for(Coordinate temp: this.coordinates){
			if(coordinate.getX() == temp.getX() && coordinate.getY() == temp.getY()){
				return true;
			}
		}
		
		return false;
		
	}
	
}
