package battleship.service;

public class Stack {

	private int index;
	
	private Coordinate[] array;
	
	public Stack(){
		
		index = -1;
		array = new Coordinate[50];
	}
	
	public void push(Coordinate newCoordinate){
		this.index++;
		this.array[index] = newCoordinate;
	}
	
	public void pop(){
		this.index--;
	}
	
	public Coordinate getTop(){
		return array[index];
	}
	
	public boolean isEmpty(){
		return (index < 0);
	}
	
	public int getIndex(){
		return this.index;
	}

	
}
