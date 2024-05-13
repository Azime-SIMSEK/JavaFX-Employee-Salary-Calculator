package application;

public class CommissionEmployee extends Employee{
	
	private double grossSales;
	private double commissionRate;
	
	protected CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,double grossSales,double commissionRate) {
		super(firstName, lastName, socialSecurityNumber);
		
		if(grossSales>=0) {
			this.grossSales=grossSales;
		}
		else {
			System.out.println("!!! GrossSales must be greater than or equal to 0 !!!");
			System.exit(0);
		}
		
		if(commissionRate>0 && commissionRate<1) {
			this.commissionRate=commissionRate;
		}
		else {
			System.out.println("!!! CommisionRate must be greater than 0 and less than 1  !!!");
			System.exit(0);
		}
	}

	protected double getGrossSales() {
		return grossSales;
	}

	protected void setGrossSales(double grossSales) {
		if(grossSales>=0) {
			this.grossSales=grossSales;
		}
		else {
			System.out.println("!!! GrossSales must be greater than or equal to 0 !!!");
			System.exit(0);
		}
	}

	
	
	protected double getCommissionRate() {
		return commissionRate;
	}

	protected void setCommissionRate(double commissionRate) {
		if(commissionRate>0 && commissionRate<1) {
			this.commissionRate=commissionRate;
		}
		else {
			System.out.println("!!! CommisionRate must be greater than 0 and less than 1  !!!");
			System.exit(0);
		}
	}

	
	
	@Override
	public String toString() {
		return " Commission Employee: "+ super.toString()+"\n Gross Sales: "+grossSales+" ; CommissionRate: "+commissionRate;
	}

	@Override
	public double getPaymentAmount() {
	
		return commissionRate*grossSales;//super.getPaymentAmount();
	}
}
