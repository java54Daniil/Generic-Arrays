package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*; 

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.*;

class CompanyTest {
	private static final long ID1 = 123;
	private static final long ID2 = 120;
	private static final long ID3 = 125;
	private static final long ID4 = 127;
	private static final long ID5 = 150;
	private static final long ID6 = 151;
	private static final long ID7 = 152;
	private static final int SALARY1 = 1000;
	private static final String DEPARTMENT1 = "Development";
	private static final String DEPARTMENT2 = "QA";
	private static final String DEPARTMENT3 = "Sales";
	private static final int SALARY2 = 2000;
	private static final int SALARY3 = 3000;


	private static final int HOURS1 = 10;
	private static final int WAGE1 = 100;

	private static final float FACTOR1 = 2;

	private static final int HOURS2 = 10;
	private static final int WAGE2 = 10;
	private static final float PRECENT1 = 10;
	private static final long SAELES1 = 100;
	
	// there should be at least one object for all classes
	// (WageEmploee,Manager,SalesPerson)
	Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
	Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
	Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
	Employee empl4 = new Employee(ID4, SALARY3, DEPARTMENT2);
	Employee wEmp = new WageEmployee(ID5, SALARY3, DEPARTMENT1, HOURS1, WAGE1);
	Employee mEmp = new Manager(ID6, SALARY1, DEPARTMENT3, FACTOR1);
	Employee spEmp = new SalesPerson(ID7, SALARY2, DEPARTMENT3, HOURS2, WAGE2, PRECENT1, SAELES1);
	Company company;

	@BeforeEach
	void setCompany() {
		// before each test there will be created new object company
		// with array of the given employee objects
		company = new Company(new Employee[] { empl1, empl2, empl3, wEmp, mEmp, spEmp });
	}

	@Test
	void testAddEmplloee() {

		company.addEmplloee(empl4);
		assertEquals(empl4, company.getEmployee(ID4));
		assertThrowsExactly(IllegalStateException.class, () -> company.addEmplloee(empl1));

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
		assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(ID4));
	}

	@Test
	void testGetDepartmentBudget() {

		// there should be another value for budget's of Department1
		int FirstDepartmentBudgetEexpected = company.getDepartmentBudget(DEPARTMENT1);
		int SecondDepartmentBudgetEexpected = company.getDepartmentBudget(DEPARTMENT2);
		int ThiredDepartmentBudgetEexpected = company.getDepartmentBudget(DEPARTMENT3);
		int ThiredDepartmentBudgetActual = (int) ((SALARY1 * FACTOR1)
				+ (SALARY2 + (HOURS2 * WAGE2) + (SAELES1 * PRECENT1 / 100)));
		assertEquals(SALARY1 + SALARY2 + SALARY3 + (HOURS1 * WAGE1), FirstDepartmentBudgetEexpected);
		assertEquals(SALARY3, SecondDepartmentBudgetEexpected);
		assertEquals(ThiredDepartmentBudgetActual, ThiredDepartmentBudgetEexpected);

	}

	@Test
	void testIterator() {
		
		Employee[] expected = { empl2, empl1, empl3,wEmp,mEmp ,spEmp};
		Iterator<Employee> it = company.iterator();
		int index = 0;
		while (it.hasNext()) {
			assertEquals(expected[index++], it.next());
		}
		assertEquals(expected.length, index);
		assertThrowsExactly(NoSuchElementException.class, it::next);

	}

	@Test
	void testGetDepartments() {
		String[] actual = company.getDepartments();
		String[] expected = {DEPARTMENT1,DEPARTMENT2,DEPARTMENT3};
		assertArrayEquals(expected, actual);
		
	}

}
