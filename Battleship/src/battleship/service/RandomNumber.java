package battleship.service;

import java.util.Random;

/**
 * @author Lindsey
 *
 */
public class RandomNumber {

	static Random random = new Random();
	
	/**
	 * Gets the random number with the max value set 
	 * @param max
	 * @return
	 */
	public static int getRandom(int max){
		return random.nextInt(max);
	}
	
}
