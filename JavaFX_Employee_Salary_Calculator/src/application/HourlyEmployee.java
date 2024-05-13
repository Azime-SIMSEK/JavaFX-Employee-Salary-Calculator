package application;

public class HourlyEmployee extends Employee {
	
	private double wage;
	private double hours;
	
	
	protected HourlyEmployee(String firstName, String lastName, String socialSecurityNumber,double wage,double hours) {
		super(firstName, lastName, socialSecurityNumber);
		
		if(wage>=0) {
			this.wage=wage;
		}
		else {
			System.out.println("!!! Wage must be greater than or equal to 0 !!!");
			System.exit(0);
		}
		
		if(hours>=0 && hours<168) {
			this.hours=hours;
		}
		else {
			System.out.println("!!! Hours must be greater than or equal to 0 and less than 168!!!");
			System.exit(0);
		}
	}


	protected double getWage() {
		return wage;
	}


	protected void setWage(double wage) {
		if(wage>=0) {
			this.wage=wage;
		}
		else {
			System.out.println("!!! Wage must be greater than or equal to 0 !!!");
			System.exit(0);
		}
	}


	protected double getHours() {
		return hours;
	}


	protected void setHours(double hours) {
		if(hours>=0 && hours<168) {
			this.hours=hours;
		}
		else {
			System.out.println("!!! Hours must be greater than or equal to 0 and less than 168!!!");
			System.exit(0);
		}	
	}


	@Override
	public String toString() {
		return " Hourly Employee: "+super.toString()+"\n Hourly Wage: "+wage+" ; Hours worked: "+hours;
	}


	@Override
	public double getPaymentAmount() {
		
		double paymentAmount =0;
		
		if(hours<=40) {
			paymentAmount=wage*hours;
		}
		else if(hours>40) {
			paymentAmount=40*wage+(hours-40)*wage*1.5;
		}
		
		return paymentAmount ;//super.getPaymentAmount();
	}
}
