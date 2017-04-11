package battleship.service;

/**
 * @author Lindsey
 *
 */
public class GridProb {

	/**
	 * Gets the starting probability
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getDefaultSquareProb(int x, int y){
		
		int prob = 10;
		
		if(x > 0 && x < 9)
			prob += 5;
		if(x > 1 && x < 8)
			prob += 4;
		if(x > 2 && x < 7)
			prob += 2;
		if(x > 3 && x < 6)
			prob += 1;
		
		
		if(y > 0 && y < 9)
			prob += 5;
		if(y > 1 && y < 8)
			prob += 4;
		if(y > 2 && y < 7)
			prob += 2;
		if(y > 3 && y < 6)
			prob += 1;

		
		return prob;
	}


	
}
