
/**
 * Group- This class represents a study group
 * @author Stephen, Michaela
 *
 */
public class Group {
	
	private String taName, taEmail, time;
	private int studentCount = 0, demand = 0;
	
	/**
	 * Constructor for the Group class
	 * @param taName
	 * The name of the TA responsible for teaching this study group
	 * @param time
	 * The time of the study group
	 */
	public Group(String taName, String taEmail, String time){
		this.taName = taName;
		this.taEmail = taEmail;
		this.time = time;
	}
	
	/**
	 * Returns if two objects are equal or not
	 * @param obj
	 * The object to compare against
	 */
	@Override
	public boolean equals(Object obj){
	       if (obj == null) {
	           return false;
	       }
	       if (getClass() != obj.getClass()) {
	           return false;
	       }
	       final Group rec = (Group) obj;
	       
	       if(taName.equals(rec.getTA()) && time.equals(rec.getTime()))
	    	   return true;

	       return false;
	}
	

	/**
	 * Get the time
	 * @return
	 * The time of this study group
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Get the TA
	 * @return
	 * The TA of this study group
	 */
	public String getTA() {
		return taName;
	}
	
	/**
	 * Get the TA Email
	 * @return
	 * The TA of this study group's email
	 */
	public String getTAEmail() {
		return taEmail;
	}
	
	/**
	 * Add a student to this study group
	 */
	public void addStudent(){
		studentCount++;
			//System.out.println("Add*************************" + studentCount);
	}
	
	/**
	 * Remove a student from this study group
	 */
	public void removeStudent(){
		studentCount--;
	}
	
	/**
	 * Get the current count of students in this study group
	 * @return
	 */
	public int getStudentCount(){
		return studentCount;
	}
	
	/**
	 * toString
	 * @return
	 * The string representation of this group
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder(time);
		sb.append(" with ");
		sb.append(taName);
		return sb.toString();
	}

	public void increaseDemand() {
		demand++;
	}

	public void decreaseDemand() {
		demand--;
	}
	
	public int getDemand(){
		return demand;
	}
}
