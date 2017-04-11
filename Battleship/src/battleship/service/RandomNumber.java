package battleship.service;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomNumber.
 *
 * @author Lindsey
 */
public class RandomNumber {

	/** The random. */
	static Random random = new Random();
	
	/**
	 * Gets the random number with the max value set .
	 *
	 * @param max the max
	 * @return the random
	 */
	public static int getRandom(int max){
		return random.nextInt(max);
	}
	
}
