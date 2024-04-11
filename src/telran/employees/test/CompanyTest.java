package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.Company;
import telran.employees.*;

class CompanyTest {
	private static final long ID1 = 123;
	private static final int SALARY1 = 1000;
	private static final String DEPARTMENT1 = "Development";
	private static final long ID2 = 120;
	private static final long ID3 = 125;
	private static final int SALARY2 = 2000;
	private static final int SALARY3 = 3000;
	private static final String DEPARTMENT2 = "QA";
	private static final long ID4 = 127;
	Employee empl1= new Employee(ID1,SALARY1,DEPARTMENT1);
	Employee empl2= new Employee(ID2,SALARY2,DEPARTMENT1);
	Employee empl3= new Employee(ID3,SALARY3,DEPARTMENT2);
	Employee empl4= new Employee(ID4,SALARY3,DEPARTMENT2);
	Company company;
	@BeforeEach
	void setCompany () {
		//before each test there will be created new object company
		//with array of the given employee objects
		company = new Company(new Employee[] {empl1,empl2,empl3});
	}
	@Test
	void testAddEmplloee() {
		
		company.addEmplloee(empl4);
		 assertEquals(empl4, company.getEmployee(ID4));
		assertThrowsExactly(IllegalStateException.class,
				() ->company.addEmplloee(empl1) );
		
	}

	@Test
	void testGetEemployees() {
		assertEquals(empl1, company.getEmployee(ID1));
		assertEquals(null, company.getEmployee(ID4));
	}

	@Test
	void testRemoveEmployee() {
		company.removeEmployee(ID1);
		assertNull(company.getEmployee(1));
		assertThrowsExactly(NoSuchElementException.class,
				() ->company.removeEmployee(ID4) );
	}

	@Test
	void testGetDepartmentBudget() {
		int FirstDepartmentBudgetEexpected =company.getDepartmentBudget(DEPARTMENT1);
		int SecondDepartmentBudgetEexpected= company.getDepartmentBudget(DEPARTMENT2);
		assertEquals(SALARY1+SALARY2, FirstDepartmentBudgetEexpected);
		assertEquals(SALARY3, SecondDepartmentBudgetEexpected);
	}
	@Test
	void testIterator() {
		
			Employee[] expected = {empl2, empl1, empl3};
			Iterator<Employee> it = company.iterator();
			int index = 0;
			while(it.hasNext()) {
				assertEquals(expected[index++], it.next());
			}
			assertEquals(expected.length, index);
			assertThrowsExactly(NoSuchElementException.class, it::next);
		
	}

}
