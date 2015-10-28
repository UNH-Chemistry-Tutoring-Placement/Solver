import java.util.ArrayList;


/**
 * Student- A class representing a student and their currently
 * assigned study group
 * @author Stephen, Michaela
 *
 */
public class Student implements Comparable<Student>{
	
	private String name, email, year, gender;
	private Lecture lecture;
	private ArrayList<Group> goodG, possibleG;
	private Group assignedGroup;
	
	/**
	 * Constructor for the Student class
	 * @param name
	 * The name of the student
	 * @param email
	 * The email of the student
	 * @param year
	 * The year of the student
	 * @param gender
	 * The gender of the student
	 */
	public Student(String name, String email, String year, String gender){
		this.name = name;
		this.email = email;
		this.year = year;
		this.gender = gender;
		goodG = new ArrayList<Group>();
		possibleG = new ArrayList<Group>();
	}
	
	/**
	 * Copy constructor
	 * @param s
	 * The student to copy
	 */
	public Student(Student s){
		this(s.getName(), s.getEmail(), s.getYear(), s.getGender());
		assignedGroup = s.getGroupAssignment();
	}
	
	/**
	 * Assign this student to a group.
	 * @param g
	 */
	public void assignGroup(Group g){
		assignedGroup = g;
		g.addStudent();
		g.decreaseDemand();
	}
	
	/**
	 * Returns true if the student has a group set
	 * @return 
	 */
	public boolean isGroupSet(){
		return assignedGroup != null;
	}
	
	/**
	 * Unset the student from this group
	 * @param g
	 */
	public void unsetGroup(Group g){
		assignedGroup = null;
		g.removeStudent();
		g.increaseDemand();
	}
	
	/**
	 * Get group assignment for this student
	 * @return
	 */
	public Group getGroupAssignment(){
		return assignedGroup;
	}
	
	/**
	 * Set the lecture for this student
	 * @param l
	 * The lecture to set
	 */
	public void setLecture(Lecture l){
		lecture = l;
	}
	
	/**
	 * Add a list of good study groups to this student
	 * @param g
	 */
	public void addGoodGroups(ArrayList<Group> goodGroups){
		 goodG.addAll(goodGroups);
	}
	
	/**
	 * Add a list of possible study groups to this student
	 * @param g
	 */
	public void addPossibleGroups(ArrayList<Group> possibleGroups){
		 possibleG.addAll(possibleGroups);
	}
	
	/**
	 * Get a list of this students good groups
	 * @return
	 */
	public ArrayList<Group> getGoodGroups(){
		goodG.sort(new GroupComparator());
		return goodG;
	}
	
	/**
	 * Get a list of this students possible groups
	 * @return
	 */
	public ArrayList<Group> getPossibleGroups(){
		possibleG.sort(new GroupComparator());
		return possibleG;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Returns a string representation of this student
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder("Name: ");
		sb.append(name);
		sb.append("\nEmail: ");
		sb.append(email);
		return sb.toString();
	}

	@Override
	public int compareTo(Student other) {
		int val = Integer.compare(this.getGoodGroups().size(), other.getGoodGroups().size());
		if(val == 0){
			val = Integer.compare(this.getPossibleGroups().size(), other.getPossibleGroups().size());
		}
		return val;
	}
}
