package battleship.service;

import java.util.Random;

public class RandomNumber {

	static Random random = new Random();
	
	public static int getRandom(int max){
		return random.nextInt(max);
	}

}
