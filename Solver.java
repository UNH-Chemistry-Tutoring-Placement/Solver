import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class is responsible for solving the algorithm.
 * @author Stephen, Michaela
 *
 */
public class Solver {
	
	private ArrayList<Student> students;
	private FileIO fileIO;
	private static boolean localTesting = true;
	
	/**
	 * Constructor
	 */
	public Solver(){
		getInfo();
	}
	
	public void solve(){
		/* Create a depth first search instance */
		DepthFirstSearch dfs = new DepthFirstSearch(
				fileIO.getObjective(), 
				students, 
				fileIO.getGroupTimes());
		
		/* Solve and print the solution */
		dfs.solve();
		printStudentSolution(dfs.getSolution());
	}
	
	/**
	 * Parse input and store results into FileInfo
	 */
	private void getInfo(){
		fileIO = new FileIO();
		fileIO.getInfo();
		students = fileIO.getStudents();
	}
	
	/**
	 * Print the student solution
	 * @param studs
	 * The studly students assigned to their study groups
	 */
	private void printStudentSolution(ArrayList<Student> studs) {
		System.out.println("Solution Format: 1");
		System.out.println("Objective Function: " + fileIO.getObjective().getDescription());
		System.out.println("Class Info: " + fileIO.getClassDesc());
		System.out.println("Student Info:  " + fileIO.getStudentDesc());
		System.out.println("Number of students: " + studs.size());

		for(Student s : studs){
			System.out.println(s.getName() + ": " + s.getGroupAssignment().getTAEmail());
		}
	}

	public static void main(String[] args){
		
		/* Populate initial world state */
		String filename = args[0];
		//String filename = "bigTest";
		
		if(localTesting){
			try {
				System.setIn(new FileInputStream(filename));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Solver s = new Solver();
		s.solve();
	}

}
