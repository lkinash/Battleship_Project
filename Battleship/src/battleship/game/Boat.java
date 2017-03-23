package battleship.game;

import java.util.ArrayList;
import java.util.List;

import battleship.service.Coordinate;

public class Boat {

	private int length;
	
	private String name;
	
	private boolean sunk;
	
	private List<Coordinate> coordinates;
	
	public Boat(){
		
	}
	
	public Boat(int newLength, String newName){
		
		this.length = newLength;
		this.name = newName;
		this.coordinates = new ArrayList<Coordinate>() ;
		this.sunk = false;
		
	}
	
	public void setLength(int newLength){
		this.length = newLength;	
	}
	
	public int getLength(){
		return this.length;
	}
	
	public void setName(String newName){
		this.name = newName;	
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setSunk(boolean newSunk){
		this.sunk = newSunk;	
	}
	
	public boolean getSunk(){
		return this.sunk;
	}
	
	public void addCoordinates(Coordinate newCoordinates){
		this.coordinates.add(newCoordinates);	
	}
	
	public void setCoordinates(List<Coordinate> newCoordinates){
		this.coordinates = newCoordinates;	
	}
	
	public List<Coordinate> getCoordinatesList(){
		return this.coordinates;
	}
	
	public boolean getIsInCoordinateList(Coordinate coordinate){
		
		for(Coordinate temp: this.coordinates){
			if(coordinate.getX() == temp.getX() && coordinate.getY() == temp.getY()){
				return true;
			}
		}
		
		return false;
		
	}
	
}
