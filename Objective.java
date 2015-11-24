/**
 * Objective- A class holding information related to the objective function
 * @author Stephen, Michaela
 *
 */
public class Objective {
	
	private static int format, minGroupSize, maxGroupSize;
	private static int minPenalty, maxPenalty, posPenalty;
	private static int diffLec, genBal, yearBal;
	private static String description;
	
	public Objective(int format, String desc, int min, int max, int minPen,
			int maxPen, int posPen, int diffLec, int genBal, int yearBal) {
		this.format = format;
		this.description = desc;
		this.minGroupSize = min;
		this.maxGroupSize = max;
		this.minPenalty = minPen;
		this.maxPenalty = maxPen;
		this.posPenalty = posPen;
		this.diffLec = diffLec;
		this.genBal = genBal;
		this.yearBal = yearBal;
	}
	
	/**
	 * @return the format
	 */
	public static int getFormat() {
		return format;
	}

	/**
	 * @return the minGroupSize
	 */
	public static int getMinGroupSize() {
		return minGroupSize;
	}

	/**
	 * @return the maxGroupSize
	 */
	public static int getMaxGroupSize() {
		return maxGroupSize;
	}

	/**
	 * @return the minPenalty
	 */
	public static int getMinPenalty() {
		return minPenalty;
	}

	/**
	 * @return the maxPenalty
	 */
	public static int getMaxPenalty() {
		return maxPenalty;
	}

	/**
	 * @return the posPenalty
	 */
	public static int getPosPenalty() {
		return posPenalty;
	}

	/**
	 * @return the description
	 */
	public static String getDescription() {
		return description;
	}
	
	public static int getDiffLec(){
		return diffLec;
	}
	
	public static int getGenBal(){
		return genBal;
	}
	
	public static int getYearBal(){
		return yearBal;
	}
}
