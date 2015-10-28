import java.util.Comparator;


public class GroupComparator implements Comparator<Group> {

	@Override
	public int compare(Group o1, Group o2) {
		return Integer.compare(o1.getDemand(), o2.getDemand());
	}

}
