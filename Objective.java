/**
 * Objective- A class holding information related to the objective function
 * @author Stephen, Michaela
 *
 */
public class Objective {
	
	private int format, minGroupSize, maxGroupSize;
	private int minPenalty, maxPenalty, posPenalty;
	private String description;
	
	public Objective(int format, String desc, int min, int max, int minPen,
			int maxPen, int posPen) {
		this.format = format;
		this.description = desc;
		this.minGroupSize = min;
		this.maxGroupSize = max;
		this.minPenalty = minPen;
		this.maxPenalty = maxPen;
		this.posPenalty = posPen;
	}
	
	/**
	 * @return the format
	 */
	public int getFormat() {
		return format;
	}

	/**
	 * @return the minGroupSize
	 */
	public int getMinGroupSize() {
		return minGroupSize;
	}

	/**
	 * @return the maxGroupSize
	 */
	public int getMaxGroupSize() {
		return maxGroupSize;
	}

	/**
	 * @return the minPenalty
	 */
	public int getMinPenalty() {
		return minPenalty;
	}

	/**
	 * @return the maxPenalty
	 */
	public int getMaxPenalty() {
		return maxPenalty;
	}

	/**
	 * @return the posPenalty
	 */
	public int getPosPenalty() {
		return posPenalty;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
