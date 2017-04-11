package battleship.game;

import java.util.ArrayList;
import java.util.List;

import battleship.service.Coordinate;

/**
 * @author Lindsey
 *
 */
public class Boat {

	private int length;
	
	private String name;
	
	private boolean sunk;
	
	/**
	 * 	The set of coordinates the boat is on
	 */
	private List<Coordinate> coordinates;
	
	/**
	 * 
	 */
	public Boat(){
		
	}
	
	/**
	 * @param newLength
	 * @param newName
	 */
	public Boat(int newLength, String newName){
		
		this.length = newLength;
		this.name = newName;
		this.coordinates = new ArrayList<Coordinate>() ;
		this.sunk = false;
		
	}
	
	/**
	 * @param newLength
	 */
	public void setLength(int newLength){
		this.length = newLength;	
	}
	
	/**
	 * @return
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * @param newName
	 */
	public void setName(String newName){
		this.name = newName;	
	}
	
	/**
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @param newSunk
	 */
	public void setSunk(boolean newSunk){
		this.sunk = newSunk;	
	}
	
	/**
	 * @return
	 */
	public boolean getSunk(){
		return this.sunk;
	}
	
	/**
	 * @param newCoordinates
	 */
	public void addCoordinates(Coordinate newCoordinates){
		this.coordinates.add(newCoordinates);	
	}
	
	/**
	 * @param newCoordinates
	 */
	public void setCoordinates(List<Coordinate> newCoordinates){
		this.coordinates = newCoordinates;	
	}
	
	/**
	 * @return
	 */
	public List<Coordinate> getCoordinatesList(){
		return this.coordinates;
	}
	
	/**
	 * @param coordinate
	 * @return
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
