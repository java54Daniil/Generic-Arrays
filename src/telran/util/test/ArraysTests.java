package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Objects;

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
	/*	Comparator<Integer> compare = 
				(o1,o2) ->{return Integer.compare(o1,o2);};
				Integer[] array = {1,2,3,4,5,6};
				int found = Arrays.binarySearch(array, 2, compare);
				assertEquals(1,found);
				int notFound = Arrays.binarySearch(array, 10, compare);
				assertEquals(-1,notFound);*/
		Integer[] sortedNumbers = {10, 20, 30, 40, 50};
		Comparator<Integer> comp = Integer::compare;
		assertEquals(0, Arrays.binarySearch(sortedNumbers, 10, comp));
		assertEquals(4, Arrays.binarySearch(sortedNumbers, 50, comp));
		assertEquals(2, Arrays.binarySearch(sortedNumbers, 30, comp));
		assertEquals(-1, Arrays.binarySearch(sortedNumbers, 5, comp));
		assertEquals(-4, Arrays.binarySearch(sortedNumbers, 35, comp));
		assertEquals(-6, Arrays.binarySearch(sortedNumbers, 55, comp));
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
	@Test
	void addTest() {
		Integer[] expectecd = {100,-3,23,4,8,41,56,-7,150};
		assertArrayEquals(expectecd, Arrays.add(number, 150));
	}
	@Test
	void personSortTest() {
		Person prs1 = new Person(123l, 1985);
		Person prs2 = new Person(120, 2000);
		Person prs3 = new Person(128, 1999);
		Person[] persons = {
			prs1,prs2,prs3
		};
		Arrays.bubbleSort(persons);
		Person[] expected = {
				new Person(120, 2000),
				new Person(123, 1985),
				new Person(128, 1999)
				};
		Person[] expectedAge = {
				new Person(120, 2000),
				new Person(128, 1999),
				new Person(123, 1985)
				};
		assertArrayEquals(expected, persons);
		Arrays.bubbleSort(persons,
				(p1,p2)->Integer.compare(p2.birthYear, p1.birthYear));
		assertArrayEquals(expectedAge, persons);
	}
	class Person implements Comparable<Person> {
		long id;
		int birthYear;
		public Person(long id ,int birthYear) {
			this.id= id;
			this.birthYear=birthYear;
		}
		@Override
		public int compareTo(Person o) {
			return Long.compare(id, o.id);
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(birthYear, id);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return birthYear == other.birthYear && id == other.id;
		}
		private ArraysTests getEnclosingInstance() {
			return ArraysTests.this;
		}
		
	}
	@Test
	void bublleSortWithoutComparatorTest() {
		Integer[] expected = {4,8,56,100,41,23,-3,-7};
		Integer[] numbersCopy = java.util.Arrays.copyOf(number, number.length);
		Comparator<Integer> evenOddComp =ArraysTests::evenOddComparator;
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
		
	};
}
