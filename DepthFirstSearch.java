import java.util.ArrayList;


/**
 * DepthFirstSearch- assigns each student to a study group, minimizing
 * the objective function using a depth first search.
 * @author Stephen, Michaela
 *
 */
public class DepthFirstSearch {
	
	private Objective objective;
	private ArrayList<Student> students, bestAssignments;
	private ArrayList<Time> times;
	private int sSize;
	private int initialScore;
	private int maxScore = Integer.MAX_VALUE;
	
	/**
	 * Constructor for the DepthFirstSearch class
	 * @param obj
	 * The objective function
	 * @param s
	 * The list of students that need groups
	 * @param t
	 * An array list of times, which hold possible study groups
	 */
	public DepthFirstSearch(Objective obj, ArrayList<Student> s, ArrayList<Time> t){
		objective = obj;
		students = s;
		times = t;
		sSize = students.size();
		initialScore = getInitialScore();
		bestAssignments = new ArrayList<Student>();
	}
	
	/**
	 * Find the initial score of the search based on the objective function
	 * @return
	 * The initial score of the search
	 */
	private int getInitialScore() {
		int groupCount = 0;
		for(Time t : times){
			groupCount += t.getGroups().size();
		}
		int score = objective.getMinGroupSize() * objective.getMinPenalty();
		score *= groupCount;
		return score;
	}

	/**
	 * Assign each student to a study group, minimizing the objective score
	 */
	public void solve(){
		if(sSize > 0){
			//System.out.println("FinalScore: " + solve(0, initialScore));
			solve(0, initialScore);
		}
		else{
			System.err.println("No students provided.");
			System.exit(-1);
		}
	}
	
	/**
	 * The recursive helper function that does most of the work
	 * @param sIndex
	 * The index representing the current student trying to be filled in
	 * @param curScore
	 * The current score of this descent
	 * @return
	 */
	private int solve(int sIndex, int curScore){
		
		if(sIndex == sSize){
			if(curScore <= maxScore){
				//System.out.println("HEREEE! " + curScore);
				maxScore = curScore;
				bestAssignments.clear();
				for(Student s : students){
					bestAssignments.add(new Student(s));
				}
			}
			
			return curScore;
		}
		
		Student cur = students.get(sIndex);
		for(Group g : cur.getGoodGroups()){
			cur.assignGroup(g);
			int score = calculateScore(curScore, g, true);
			//System.out.println(score + ": " + cur + " " + g + " " + curScore);
			
			//if(score < maxScore){
				solve(sIndex+1, score);
			//}
			/* Assigning group g to the student failed. Try another group. */
			cur.unsetGroup(g);
		}
		for(Group g : cur.getPossibleGroups()){
			cur.assignGroup(g);
			int score = calculateScore(curScore, g, false);
			//System.out.println(score + ": " + cur + " " + g + " " + curScore);
			
			//if(score < maxScore){
				solve(sIndex+1, score);
			//}
			/* Assigning group g to the student failed. Try another group. */
			cur.unsetGroup(g);
		}
		return maxScore;
	}
	
	/**
	 * Return the solution
	 * @return
	 * A list of assigned students
	 */
	public ArrayList<Student> getSolution(){
		return bestAssignments;
	}

	/**
	 * Calculate the objective score after a group assignment or removal
	 * @param curScore
	 * The current score
	 * @param g
	 * The new group to look at
	 * @param isGood
	 * Boolean representing if the time is a "Good" time
	 * @return
	 */
	private int calculateScore(int curScore, Group g, boolean isGood) {
		int studentCount = g.getStudentCount();
		int dScore = 0;
		if(studentCount <= objective.getMinGroupSize()){
			dScore -= objective.getMinPenalty();
		}
		else if(studentCount > objective.getMaxGroupSize())
			dScore += objective.getMaxPenalty();
		
		if(!isGood)
			dScore += objective.getPosPenalty();
		return curScore + dScore;
	}
}
