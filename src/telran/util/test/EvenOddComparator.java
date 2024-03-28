package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		
		//first even numbers sorted in the ascending order
		//last odd numbers sorted in the descending order
		 //boolean isO1Even = o1 % 2 == 0;
		// boolean isO2Even = o2 % 2 == 0;
		//return (isO1Even && !isO2Even)?-1:(!isO1Even && isO2Even)? 1:o1.compareTo(o2);
		int result = 0;
		if(o1%2 == 0 && o2%2 == 0) {
			result = o1 - o2;
		} else if(o1 %2 != 0 && o2%2 != 0) {
			result = o2 - o1;
		} else {
			result = o1 % 2 == 0 ? -1 : 1;
		}
		return result;
	}
}
