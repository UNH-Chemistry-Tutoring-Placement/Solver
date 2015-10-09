
public class Recitation {
	
	private String taName, time;
	private int section;
	
	public Recitation(String taName, String time, int section){
		this.taName = taName;
		this.time = time;
		this.section = section;
	}
	
	@Override
	public boolean equals(Object obj){
	       if (obj == null) {
	           return false;
	       }
	       if (getClass() != obj.getClass()) {
	           return false;
	       }
	       final Recitation rec = (Recitation) obj;
	       
	       if(taName.equals(rec.getTA()) && time.equals(rec.getTime()))
	    	   return true;

	       return false;
	}
	
	@Override
	public int hashCode(){
		return section;
	}

	private String getTime() {
		return time;
	}

	private String getTA() {
		return taName;
	}
}
