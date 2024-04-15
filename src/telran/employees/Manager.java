package telran.employees;

public class Manager extends Employee {
	 private float factor;
	//Constructor of class Manager must take factor(see UML schema)
	public Manager(long id, int basicSalary, String deepartment,float factor) {
		super(id, basicSalary, deepartment);
		this.factor =factor;
		
	}
	
	@Override
	public int computeSalary() {
		return (int)(super.computeSalary() *factor);
	}
}
