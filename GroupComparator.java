import java.util.Comparator;


public class GroupComparator implements Comparator<Group> {

	@Override
	public int compare(Group o1, Group o2) {
		int remSlots1 = Objective.getMaxGroupSize() - o1.getStudentCount();
		int remSlots2 = Objective.getMaxGroupSize() - o2.getStudentCount();
		int val1 = (remSlots1 > 0) ? o1.getDemand()/remSlots1 : 0;
		int val2 = (remSlots2 > 0) ? o2.getDemand()/remSlots2 : 0;
		return Integer.compare(val2, val1);
	}
}
