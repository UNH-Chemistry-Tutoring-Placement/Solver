import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class FileIO {
	
	private Scanner in;
	private ArrayList<Lecture> lectures;
	private HashSet<Recitation> recitations;
	
	public FileIO(){
		in = new Scanner(System.in);
		lectures = new ArrayList<Lecture>();
		recitations = new HashSet<Recitation>();
	}
	
	public void getInfo(){
		String newFile = removeComments();
		in = new Scanner(newFile);
		readObjectiveFunction();
		readClassInfoFormat();
		readStudentInfoFormat();
	}
	
	private String removeComments(){
		StringBuilder sb = new StringBuilder("");
		while(in.hasNext()){
			String line = in.nextLine();
			if(line.charAt(0) == '#')
				continue;
			sb.append(line);
			sb.append('\n');
		}
		return sb.toString();
	}
	
	/**
	 * This is dumb, we should look into a better input format!
	 */
	private void readObjectiveFunction(){
		in.skip("Objective Function Format:");
		System.out.println(in.nextInt());
		in.skip("\nDescription: ");
		System.out.println(in.nextLine());
		in.skip("min group size:");
		System.out.println(in.nextInt());
		in.skip("\nmax group size:");
		System.out.println(in.nextInt());
		in.skip("\nbelow min penalty:");
		System.out.println(in.nextInt());
		in.skip("\nabove max penalty:");
		System.out.println(in.nextInt());
		in.skip("\npossible choice penalty:");
		System.out.println(in.nextInt());
	}
	
	private void readClassInfoFormat(){
		in.skip("\nClass Info Format:");
		System.out.println(in.nextInt());
		in.skip("\nDescription: ");
		System.out.println(in.nextLine());
		
		in.skip("Number of lectures:");
		int numLectures = in.nextInt();
		System.out.println(numLectures);
		in.skip("\n");
		for(int i = 0; i < numLectures; i++){
			in.skip("Name: ");
			lectures.add( new Lecture(in.nextLine()));
		}
		
		in.skip("Number of groups:");
		int numGroups = in.nextInt();
		System.out.println(numGroups);
		in.skip("\n");
		for(int i = 0; i < numGroups; i++){
			in.skip("Name: ");
			String taName = in.nextLine();
			in.skip("Time: ");
			String time = in.nextLine();
			recitations.add( new Recitation(taName, time, i));
		}
	}
	
	private void readStudentInfoFormat(){
		in.skip("Student Info Format:");
		System.out.println(in.nextInt());
		in.skip("\nDescription: ");
		System.out.println(in.nextLine());
		
		in.skip("Number of students:");
		int numStudents = in.nextInt();
		System.out.println(numStudents);
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
			int year = in.nextInt();
			in.skip("\nSex: ");
			String gender = in.nextLine();

			Student s = new Student(name, email, year, gender);
			//s.setLecture(lecture);
			
			in.skip("Number of good times:");
			int nGTimes = in.nextInt();
			System.out.println(nGTimes);
			in.skip("\n");
			for(int j = 0; j < nGTimes; j++){
				String time = in.nextLine();
				//Recitation r = new Recitation(in.nextLine());
//				for(int k = 0; k < recitations.size(); k++){
//					if(time.equals(time))
//				}
				//s.addGoodRecitation(r);
			}
			
			in.skip("Number of possible times:");
			int nPTimes = in.nextInt();
			System.out.println(nPTimes);
			in.skip("\n");
			for(int j = 0; j < nPTimes; j++){
				System.out.println(in.nextLine());
			}
		}
	}
	
	

}