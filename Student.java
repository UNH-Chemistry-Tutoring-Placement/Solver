import java.util.ArrayList;


public class Student {
	
	private String name, email, gender;
	private int year;
	private Lecture lecture;
	private ArrayList<Recitation> goodR, possibleR;
	
	public Student(String name, String email, int year, String gender){
		this.name = name;
		this.email = email;
		this.year = year;
		this.gender = gender;
		goodR = new ArrayList<Recitation>();
		possibleR = new ArrayList<Recitation>();
	}
	
	public void setLecture(Lecture l){
		lecture = l;
	}
	
	public void addGoodRecitation(Recitation r){
		 goodR.add(r);
	}
	
	public void addPossibleRecitation(Recitation r){
		 possibleR.add(r);
	}
	
}
