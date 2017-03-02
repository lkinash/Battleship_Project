package battleship.game;

import java.util.List;

import battleship.service.Coordinate;

public class Boat {

	private int length;
	
	private String name;
	
	private List<Coordinate> coordinates;
	
	public Boat(){
		
	}
	
	public Boat(int newLength, String newName, List<Coordinate> newCoordinates ){
		
		this.length = newLength;
		this.name = newName;
		this.coordinates = newCoordinates;
		
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
	
	public void addCoordinates(Coordinate newCoordinates){
		this.coordinates.add(newCoordinates);	
	}
	
	public void setCoordinates(List<Coordinate> newCoordinates){
		this.coordinates = newCoordinates;	
	}
	
	public List<Coordinate> getCoordinatesList(){
		return this.coordinates;
	}
	
}
