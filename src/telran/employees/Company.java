package telran.employees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;
//SO far we don't consider optimization
public class Company implements Iterable<Employee>{
	private Employee[] employees;
	public void addEmplloee(Employee empl) {
		// adds new Employee to array of employees
		//if employee with id equal to id of employee exist throw IleegalStateExpection
		long id = empl.getId();
		if(getEmployee(id) != null) {
			throw new IllegalStateException(String.format("employee with id %d already exists",
					id));
		}
		employees = Arrays.insertSorted(employees, empl, Comparator.naturalOrder());
	}
	public Employee getEmployee(long id) {
		//data about an employee with a given id value
		//if the company doesn't have such employee, then return null
		int index = Arrays.binarySearch(employees,  new Employee(id, 0, null), Comparable::compareTo);
		return index < 0 ? null : employees[index];
		
	}
	public Employee removeEmployee(long id) {
		//removes from the company an employee with a given id
		//if such employee doesn't exist, throw NoSuchElementException
		//returns reference to being removed employee
		 
		Employee result = getEmployee(id);
		if(result == null) {
			throw new NoSuchElementException
			(String.format("Employee with id %d doesn't exist", id));
		}
		employees = Arrays.removeIf(employees, e -> e.getId() == id);
		return result;
	}
	public int getDepartmentBudget (String department) {
		// return sum of  basic salary values for all employees of a given department
		//if employees of  a given department don't exist   returns 0
		int budget = 0;
	    for (Employee employee : employees) {
	    	if(employee.getDeepartment().equals(department)) {
	    		budget+=employee.getBasicSalary();
	    	}
	    }
		return budget;
		
	}
	public Company (Employee[] employees) {
		this.employees = Arrays.copy(employees);
		Arrays.bubbleSort(this.employees);
	}
	
	@Override
	public Iterator<Employee> iterator() {
		
		return new CompanyIterator();
	}
	private class CompanyIterator implements Iterator<Employee> {

		int index = 0;
		//iterating all employees in the ascending order of the ID values
		@Override
		public boolean hasNext() {
			
			return index<employees.length;
		}
		@Override
		public Employee next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return employees[index++];
		}
		
	}

	
}
