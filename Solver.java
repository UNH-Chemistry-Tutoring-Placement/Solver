import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Solver {
	private static boolean localTesting = true;
	
	public Solver(){
		getInfo();
	}
	
	private void getInfo(){
		FileIO fileIO = new FileIO();
		fileIO.getInfo();
	}
	
	public static void main(String[] args){
		
		/* Populate initial world state */

		String filename = "test.txt";
		 //********** DELETE THIS DELETE THIS DELETE THIS ***************
		if(localTesting){
			try {
				System.setIn(new FileInputStream(filename));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Solver s = new Solver();
	}

}
