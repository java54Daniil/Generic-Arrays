package telran.employees;

public class SalesPerson extends WageEmployee {

	 private float precent;
	 private long sales;
	//Constructor of class SalesPerson must take additional parameters(see UML schema)
	public SalesPerson(long id, int basicSalary, String deepartment, int hours, int wage,
			float precent,long sales) {
		super(id, basicSalary, deepartment, hours, wage);
		this.precent = precent;
		this.sales = sales;
	}
	
	@Override
	public int computeSalary() {
		return super.computeSalary() +(int)(sales*precent/100) ;
	}
}
