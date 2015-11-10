import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 * DepthFirstSearch- assigns each student to a study group, minimizing
 * the objective function using a depth first search.
 * @author Stephen, Michaela
 *
 */
public class ILDS {
	
	private Objective objective;
	private ArrayList<Student> bestAssignments;
	private ArrayList<Student> curAssignments;
	private PriorityQueue<Student> students;
	private ArrayList<Time> times;
	private int sSize;
	private int initialScore;
	private int maxScore = Integer.MAX_VALUE;
	private boolean timeout = false;
	private int discLimit = 0;
	
	/**
	 * Constructor for the DepthFirstSearch class
	 * @param obj
	 * The objective function
	 * @param students
	 * The list of students that need groups
	 * @param t
	 * An array list of times, which hold possible study groups
	 */
	public ILDS(Objective obj, PriorityQueue<Student> students, ArrayList<Time> t){
		objective = obj;
		this.students = students;
		times = t;
		sSize = students.size();
		initialScore = 0;
		bestAssignments = new ArrayList<Student>();
		curAssignments = new ArrayList<Student>();
	}

	/**
	 * Assign each student to a study group, minimizing the objective score
	 */
	public void solve(){
		if(sSize > 0){
			//System.out.println(students);
			while(discLimit < sSize){
				solve(0, initialScore, 0);
				discLimit++;
			}
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
	private int solve(int sIndex, int curScore, int numDiscs){
		if(timeout){
			return maxScore;
		}
		if(students.isEmpty()){
			curScore += updateMinScore();
			if(curScore <= maxScore){
				maxScore = curScore;
				bestAssignments.clear();
				for(Student s : curAssignments){
					bestAssignments.add(new Student(s));
				}
			}
			
			return curScore;
		}
		
		Student cur = students.poll();
		curAssignments.add(cur);
		if(cur.getGoodGroups().isEmpty() && cur.getPossibleGroups().isEmpty() ){
			System.out.println(sIndex);
		}
		int choice = 0;
		for(Group g : cur.getGoodGroups()){
			cur.assignGroup(g);
			int score = calculateScore(curScore, g, true);
			if(score < maxScore){
				if(choice == 0){
					solve(sIndex+1, score, numDiscs );
				}
				else if(numDiscs < discLimit){
					solve(sIndex+1, score, numDiscs + 1);
				}
			}
			
			/* Assigning group g to the student failed. Try another group. */
			cur.unsetGroup(g);
			choice++;
		}
		for(Group g : cur.getPossibleGroups()){
			cur.assignGroup(g);
			int score = calculateScore(curScore, g, false);
			if(score < maxScore)
			{
				if(choice == 0){
					solve(sIndex+1, score, numDiscs );
				}
				else if (numDiscs < discLimit){
					solve(sIndex+1, score, numDiscs + 1);
				}
			}
			
			/* Assigning group g to the student failed. Try another group. */
			cur.unsetGroup(g);
			choice++;
		}
		/* backtrack and add student back into priority queue */
		curAssignments.remove(cur);
		students.add(cur);
		
		return maxScore;
	}
	
	/**
	 * Calculate the minimum penalty when a leaf node is reached. 
	 * @return The minimum penalty
	 */
	private int updateMinScore() {
		int min = objective.getMinGroupSize();
		int minPen = objective.getMinPenalty();
		int score = 0;
		for(Time t : times){
			for(Group g : t.getGroups()){
				if(g.getStudentCount() < min){
					score += minPen*(min - g.getStudentCount());
				}
			}
		}
		return score;
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
	 * Return the solution cost
	 * @return
	 * The solution cost
	 */
	public int getSolutionCost(){
		return maxScore;
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
		if(studentCount > objective.getMaxGroupSize())
			dScore += objective.getMaxPenalty();
		
		if(!isGood)
			dScore += objective.getPosPenalty();
		return curScore + dScore;
	}

	public void end() {
		timeout = true;
	}
}
