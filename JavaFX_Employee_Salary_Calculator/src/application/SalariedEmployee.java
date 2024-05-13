package application;

public class SalariedEmployee extends Employee{
	
	private double weeklySalary;
	
	protected SalariedEmployee(String firstName, String lastName, String socialSecurityNumber,double weeklySalary) {
		super(firstName, lastName,socialSecurityNumber);
		
		if(weeklySalary>=0) {
			this.weeklySalary=weeklySalary;
		}
		else {
			System.out.println("!!! WeeklySalary must be greater than or equal to 0 !!!");
			System.exit(0);
		}
	}

	protected double getWeeklySalary() {
		return weeklySalary;
	}

	protected void setWeeklySalary(double weeklySalary) {
		if(weeklySalary>=0) {
			this.weeklySalary=weeklySalary;
		}
		else {
			System.out.println("!!! WeeklySalary must be greater than or equal to 0 !!!");
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		return " Salaried employee:"+super.toString()+" \n WeeklySalary :"+weeklySalary;
	}
 
	@Override
	public double getPaymentAmount() {
		return weeklySalary;//super.getPaymentAmount();
	}


}
