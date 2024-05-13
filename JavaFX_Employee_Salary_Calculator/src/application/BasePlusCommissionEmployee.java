package application;

public class BasePlusCommissionEmployee extends CommissionEmployee {
	
	private double baseSalary;

	protected BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
			double grossSales, double commissionRate,double baseSalary) {
		super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
		
		if(baseSalary>=0) {
			this.baseSalary=baseSalary;
		}
		else{
			System.out.println("!!! BaseSalary must be greater than or equal to 0  !!!");
			System.exit(0);
		}
	}

	protected double getBaseSalary() {
		return baseSalary;
	}

	protected void setBaseSalary(double baseSalary) {
		if(baseSalary>=0) {
			this.baseSalary=baseSalary;
		}
		else{
			System.out.println("!!! BaseSalary must be greater than or equal to 0  !!!");
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		return  getFirstName() +"  "+ getLastName() + "\n SocialSecurityNumber :"
				+ getSocialSecurityNumber()+ "\n Gross Sales: "+getGrossSales()+" ; CommissionRate: "+getCommissionRate()+" Base Salary: "+baseSalary;
	}

	@Override
	public double getPaymentAmount() {
		return super.getPaymentAmount()+baseSalary;
	}
	
}
