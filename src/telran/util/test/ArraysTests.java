package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArraysTests {
	Integer[] number = {100,-3,23,4,8,41,56,-7};
	String[] strings = {"abc","lmn","123",null,"a"};
	String[] stringsMin ={"abc","lmn","123","y"};
	@Test
	void inexOfTest() {
		assertEquals(1,Arrays.indexOf(strings, "lmn"));
		assertEquals(3,Arrays.indexOf(strings, null));
		assertEquals(-1,Arrays.indexOf(number, 150) );
		assertEquals(4,Arrays.indexOf(number, 8));
	}
	@Test
	void minValueTest(){
		Comparator<String> compLengt = 
			(s1,s2) -> s1.length() - s2.length();
		
		assertEquals( "y", Arrays.min(stringsMin,compLengt));
		Comparator<String> compNative = (s1,s2)-> s1.compareTo(s2);
		assertEquals("123", Arrays.min(stringsMin,compNative ));
	};

	@Test
	void bublleSortTest() {
		Integer[] expected = {4,8,56,100,41,23,-3,-7};
		Integer[] numbersCopy = java.util.Arrays.copyOf(number, number.length);
		//Comparator<Integer> evenOddComp = (num1,num2)->
		//evenOddComparator(num1,num2);
		Comparator<Integer> evenOddComp =ArraysTests::evenOddComparator;
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
	};
	
	static int evenOddComparator(Integer num1,Integer num2) {
		boolean isNum1Even = num1 % 2 == 0;
		boolean isNum2Even = num2 % 2 == 0;
		int res = 1;
		if (isNum1Even && isNum2Even) {
			res = Integer.compare(num1, num2);
		} else if (!isNum1Even && !isNum2Even) {
			res = Integer.compare(num2, num1);
		} else if (isNum1Even) {
			res = -1;
		}
		return res;
	}
	@Test
	void searchTest() {
		Integer[] expectedEven = {100,4,8,56};
		assertArrayEquals(expectedEven,Arrays.search(number, 
				a ->a%2 ==0));
		Integer[] expectedNegative = {-3,-7};
		assertArrayEquals(expectedNegative,Arrays.search(number, 
				a->a<0));
	}
	//HW
	@Test 
	void binarySearchTest() {
		Comparator<Integer> compare = 
				(o1,o2) ->{return Integer.compare(o1,o2);};
				Integer[] array = {1,2,3,4,5,6};
				int found = Arrays.binarySearch(array, 2, compare);
				assertEquals(1,found);
				int notFound = Arrays.binarySearch(array, 10, compare);
				assertEquals(-1,notFound);
	}
	@Test 
	void removeIfTest() {
		
		Integer[] onlyOdd = {-3,23,41,-7};
		assertArrayEquals(onlyOdd,Arrays.removeIf(number, 
				a ->a%2 ==0));
		Integer[] onlyPositive = {100,23,4,8,41,56};
		assertArrayEquals(onlyPositive,Arrays.removeIf(number, 
				a->a<0));
		Integer[] notInRange = number;
		assertArrayEquals (notInRange,Arrays.removeIf(number, a->a>2000));
		
	}
}
