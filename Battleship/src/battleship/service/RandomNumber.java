package battleship.service;

import java.util.Random;

public class RandomNumber {

	Random random;
	
	public RandomNumber(){
		
		this.random = new Random();
	}
	
	public int getRandom(int max){
		return random.nextInt(max);
	}
	
}
