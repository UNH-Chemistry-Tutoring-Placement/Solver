/**
 * Objective- A class holding information related to the objective function
 * @author Stephen, Michaela
 *
 */
public class Objective {
	
	private static int format, minGroupSize, maxGroupSize;
	private static int minPenalty, maxPenalty, posPenalty;
	private static int diffLec, genBal;
	private static String description;
	
	/**
	 * @return the format
	 */
	public static int getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public static void setFormat(int format) {
		Objective.format = format;
	}

	/**
	 * @param minGroupSize the minGroupSize to set
	 */
	public static void setMinGroupSize(int minGroupSize) {
		Objective.minGroupSize = minGroupSize;
	}

	/**
	 * @param maxGroupSize the maxGroupSize to set
	 */
	public static void setMaxGroupSize(int maxGroupSize) {
		Objective.maxGroupSize = maxGroupSize;
	}

	/**
	 * @param minPenalty the minPenalty to set
	 */
	public static void setMinPenalty(int minPenalty) {
		Objective.minPenalty = minPenalty;
	}

	/**
	 * @param maxPenalty the maxPenalty to set
	 */
	public static void setMaxPenalty(int maxPenalty) {
		Objective.maxPenalty = maxPenalty;
	}

	/**
	 * @param posPenalty the posPenalty to set
	 */
	public static void setPosPenalty(int posPenalty) {
		Objective.posPenalty = posPenalty;
	}

	/**
	 * @param diffLec the diffLec to set
	 */
	public static void setDiffLec(int diffLec) {
		Objective.diffLec = diffLec;
	}

	/**
	 * @param genBal the genBal to set
	 */
	public static void setGenBal(int genBal) {
		Objective.genBal = genBal;
	}

	/**
	 * @param description the description to set
	 */
	public static void setDescription(String description) {
		Objective.description = description;
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
}
