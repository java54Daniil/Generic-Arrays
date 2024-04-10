package telran.employees;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;
//SO far we don't consider optimization
public class Company implements Iterable{
	private Employee[] employees;
	public void addEmplloee(Employee empl) {
		// adds new Employee to array of employees
		//if employee with id equal to id of employee exist throw IleegalStateExpection
		for(Employee employee:employees) {
			if(employee.getId() == empl.getId()) {
				throw new IllegalStateException("Employee already exist");
			}
		}
		 employees =Arrays.add(employees, empl);
	}
	public Employee getEemployees(long id) {
		//data about an employee with a given id value
		//if the company doesn't have such employee, then return null
		Employee result =null;
		for (Employee employee :employees) {
			if (employee.getId()==id) {
				result =employee;
			}
		}
		return result;
		
	}
	public Employee removeEmployee(long id) {
		//removes from the company an employee with a given id
		//if such employee doesn't exist, throw NoSuchElementException
		//returns reference to being removed employee
		 /*boolean found=false;
		  for (int i = 0; i < employees.length||found !=true; i++) {
		        if (employees[i].getId() == id&& employees[i]!=null) {
		            removedEmployee = employees[i];
		            for (int j = i; j < employees.length - 1; j++) {
		                employees[j] = employees[j + 1];
		            }
		            employees[employees.length - 1] = null;
		            found =true;
		        }
		    }
		  if(removedEmployee ==null) {
			  throw new NoSuchElementException("Employee with ID"+id+"dosent exist");
		  }*/
		 Employee removedEmployee =null;
		int index = -1;
	    for (int i = 0; i < employees.length; i++) {
	        if (employees[i] != null && employees[i].getId() == id) {
	            index = i;
	            removedEmployee = employees[i];
	        }
	    }
	    if (index != -1) {
	        Employee[] newEmployees = new Employee[employees.length - 1];
	        System.arraycopy(employees, 0, newEmployees, 0, index);
	        System.arraycopy(employees, index + 1, newEmployees, index, employees.length - index - 1);
	        employees = newEmployees;
	    } 
	    if(removedEmployee ==null) {
	        throw new NoSuchElementException("Employee with ID " + id + " doesn't exist");
	    }

		   return removedEmployee;
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
		Employee employee =employees[index];
		index++;
			return employee;
		}
		
	}

	
}
