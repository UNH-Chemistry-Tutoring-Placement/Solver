import java.util.ArrayList;


/**
 * Student- A class representing a student and their currently
 * assigned study group
 * @author Stephen, Michaela
 *
 */
public class Student implements Comparable<Student>{
	
	private String name, email, year, gender, professor;
	private ArrayList<Group> goodG, possibleG, compGood, uncompGood, compPos, uncompPos;
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
	public Student(String name, String email, String year, String gender, String professor){
		this.name = name;
		this.email = email;
		this.year = year;
		this.gender = gender;
		this.professor = professor;
		goodG = new ArrayList<Group>();
		possibleG = new ArrayList<Group>();
		compGood = new ArrayList<Group>();
		uncompGood = new ArrayList<Group>();
		compPos = new ArrayList<Group>();
		uncompPos = new ArrayList<Group>();
	}
	
	/**
	 * Copy constructor
	 * @param s
	 * The student to copy
	 */
	public Student(Student s){
		this(s.getName(), s.getEmail(), s.getYear(), s.getGender(), s.getProfessor());
		assignedGroup = s.getGroupAssignment();
	}
	
	/**
	 * Assign this student to a group.
	 * @param g
	 */
	public void assignGroup(Group g){
		assignedGroup = g;
		g.addStudent(this);
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
		g.removeStudent(this);
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
		return goodG;
	}
	public ArrayList<Group> getGoodCompGroups(){
		return compGood;
	}
	public ArrayList<Group> getGoodUncompGroups(){
		return uncompGood;
	}

	public void sortGoodGroups() {
		goodG.sort(new GroupComparator());
		compGood.clear();
		uncompGood.clear();
		for(Group g : goodG){
			if(!g.getProfessor().equals("") && !g.getProfessor().equals(professor)){
				uncompGood.add(g);
			} else{
				compGood.add(g);
			}
		}
	}
	
	/**
	 * Get a list of this students possible groups
	 * @return
	 */
	public ArrayList<Group> getPossibleGroups(){
		return possibleG;
	}
	public ArrayList<Group> getPossibleCompGroups(){
		return compPos;
	}
	public ArrayList<Group> getPossibleUncompGroups(){
		return uncompPos;
	}

	public void sortPossibleGroups() {
		possibleG.sort(new GroupComparator());
		compPos.clear();
		uncompPos.clear();
		for(Group g : possibleG){
			if(!g.getProfessor().equals("") && !g.getProfessor().equals(professor)){
				uncompPos.add(g);
			} else{
				compPos.add(g);
			}
		}
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
	
	public String getProfessor() {
		return professor;
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
		int maxGSize = Objective.getMaxGroupSize();
		
		int gSum = 0;
		int pSum = 0;
		int oGSum = 0;
		int oPSum = 0;
		for(Group g: this.getGoodGroups()){
			if(maxGSize - g.getStudentCount() > 0){
				gSum += maxGSize - g.getStudentCount();
			}
		}
		for(Group g: other.getGoodGroups()){
			if(maxGSize - g.getStudentCount() > 0){
				oGSum += maxGSize - g.getStudentCount();
			}
		}
		for(Group g: this.getPossibleGroups()){
			if(maxGSize - g.getStudentCount() > 0){
				pSum += maxGSize - g.getStudentCount();
			}
		}
		for(Group g: other.getPossibleGroups()){
			if(maxGSize - g.getStudentCount() > 0){
				oPSum += maxGSize - g.getStudentCount();
			}
		}
		
		if(gSum + pSum != oGSum + oPSum)
			return Integer.compare(gSum + pSum, oGSum + oPSum);
		if(gSum == 0)
			return 1;
		if(oGSum == 0)
			return -1;
		return Integer.compare(gSum, oGSum);
	}
}
