import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * FileIO- This class fills in the appropriate data structures from the 
 * config file.
 * @author Stephen, Michaela
 *
 */
public class FileIO {
	
	private Scanner in;
	private ArrayList<Lecture> lectures;
	private ArrayList<Time> groupTimes;
	private PriorityQueue<Student> students;
	private Objective objective;
	private String classDesc, studentDesc;
	
	/**
	 * Constructor
	 */
	public FileIO(){
		in = new Scanner(System.in);
		lectures = new ArrayList<Lecture>();
		groupTimes = new ArrayList<Time>();
		students = new PriorityQueue<Student>();
	}
	
	/**
	 * Fill in appropriate data structures from config file
	 */
	public void getInfo(){
		String newFile = removeComments();
		in = new Scanner(newFile);
		readObjectiveFunction();
		readClassInfoFormat();
		readStudentInfoFormat();
	}
	
	/**
	 * Remove all comments from config file for easier parsing
	 * @return
	 * A string representing the config file without comments
	 */
	private String removeComments(){
		StringBuilder sb = new StringBuilder("");
		while(in.hasNext()){
			String line = in.nextLine();
			if(line.equals("") || line.charAt(0) == '#')
				continue;
			sb.append(line);
			sb.append('\n');
		}
		return sb.toString();
	}
	
	/**
	 * Read the objective function 
	 * note: This is dumb, we should look into a better input format!
	 */
	private void readObjectiveFunction(){
		in.skip("Objective Function Format:");
		int format = in.nextInt();
		in.skip("\nDescription: ");		
		String desc = in.nextLine();
		in.skip("min group size:");
		int min = in.nextInt();
		in.skip("\nmax group size:");
		int max = in.nextInt();
		in.skip("\nbelow min penalty:");
		int minPen = in.nextInt();
		in.skip("\nabove max penalty:");
		int maxPen = in.nextInt();
		in.skip("\npossible choice penalty:");
		int posPen = in.nextInt();
		in.skip("\ndiff lecture penalty:");
		int diffLec = in.nextInt();
		in.skip("\ngender balance penalty:");
		int genBal = in.nextInt();
		in.skip("\nyear balance penalty:");
		int yearBal = in.nextInt();

		/* TODO, add diffLec genBal and yearBal */
		objective = new Objective(format, desc, min, max, minPen, maxPen, posPen, diffLec, genBal, yearBal);
	}
	
	/**
	 * Read the class info format
	 */
	private void readClassInfoFormat(){
		in.skip("\nClass Info Format:");
		/* Skipping the format for now */
		in.nextInt();
		in.skip("\nDescription: ");
		classDesc = in.nextLine();
		
		in.skip("Number of lectures:");
		int numLectures = in.nextInt();
		in.skip("\n");
		for(int i = 0; i < numLectures; i++){
			in.skip("Name: ");
			lectures.add( new Lecture(in.nextLine()));
		}
		
		in.skip("Number of groups:");
		int numGroups = in.nextInt();
		in.skip("\n");
		for(int i = 0; i < numGroups; i++){
			in.skip("Name: ");
			String taName = in.nextLine();
			in.skip("Email: ");
			String taEmail = in.nextLine();
			in.skip("Time: ");
			String time = in.nextLine();
			
			Time t = new Time(time);
			if(!groupTimes.contains(t)){
				groupTimes.add(t);
				t.addGroup(new Group(taName, taEmail, time));
			}
			else{
				for(Time tm : groupTimes){
					if(tm.equals(t))
						tm.addGroup(new Group(taName, taEmail, time));
				}
			}
		}
	}
	
	/**
	 * Read the student info format
	 */
	private void readStudentInfoFormat(){
		in.skip("Student Info Format:");
		/* Skipping the format for now */
		in.nextInt();
		in.skip("\nDescription: ");
		studentDesc = in.nextLine();
		
		in.skip("Number of students:");
		int numStudents = in.nextInt();
		in.skip("\n");
		for(int i = 0; i < numStudents; i++){
			in.skip("Name: ");
			String name = in.nextLine();
			in.skip("Email: ");
			String email = in.nextLine();
			in.skip("Lecture: ");
			in.nextLine();
			//Lecture lecture = new Lecture(in.nextLine());
			in.skip("Year: ");
			String year = in.nextLine();
			in.skip("Sex: ");
			String gender = in.nextLine();

			Student s = new Student(name, email, year, gender);
			//System.out.println(name + " " + email + " " + year + " " + gender);
			//System.out.println(name);
			students.add(s);
			
			in.skip("Number of good times:");
			int nGTimes = in.nextInt();
			//System.out.println("numG: " + nGTimes);
			in.skip("\n");
			for(int j = 0; j < nGTimes; j++){
				String time = in.nextLine();
				for(Time tm : groupTimes){
					if(tm.getTime().equals(time)){
						ArrayList<Group> groups = tm.getGroups();
						s.addGoodGroups(groups);
						for(Group g : groups)
							g.increaseDemand();
					}
				}
			}
			
			in.skip("Number of possible times:");
			int nPTimes = in.nextInt();
			//System.out.println("numP: " + nPTimes);
			in.skip("\n");
			for(int j = 0; j < nPTimes; j++){
				//System.out.println("possible: " + j);
				String time = in.nextLine();
				for(Time tm : groupTimes){
					//System.out.println(tm.getTime() + " " + time);
					if(tm.getTime().equals(time)){
						ArrayList<Group> groups = tm.getGroups();
						s.addPossibleGroups(groups);
						for(Group g : groups)
							g.increaseDemand();
					}
				}
			}
		}
	}
	
	
	
	/**
	 * @return the groupTimes
	 */
	public ArrayList<Time> getGroupTimes() {
		return groupTimes;
	}

	/**
	 * @return the students
	 */
	public PriorityQueue<Student> getStudents() {
		return students;
	}

	/**
	 * @return the objective
	 */
	public Objective getObjective() {
		return objective;
	}

	/**
	 * Print all the students (debug method)
	 */
	public void printStudents(){
		for(Student s : students){
			System.out.println(s);
			printGroupTimes(s);
		}
	}
	
	/**
	 * @return the classDesc
	 */
	public String getClassDesc() {
		return classDesc;
	}

	/**
	 * @return the studentDesc
	 */
	public String getStudentDesc() {
		return studentDesc;
	}

	/**
	 * Print the group times of this student (debug method)
	 * @param s
	 * The student to print
	 */
	private void printGroupTimes(Student s){
		System.out.println("   Good Groups:");
		for(Group g : s.getGoodGroups())
			System.out.println("\t" + g.getTime() + " " + g.getTA());
		System.out.println("   Possible Groups:");
		for(Group g : s.getPossibleGroups())
			System.out.println("\t" + g.getTime() + " " + g.getTA());
	}
}