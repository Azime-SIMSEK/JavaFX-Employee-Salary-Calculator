package application;

public abstract class Employee implements Payable{
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	
	protected Employee(String firstName, String lastName, String socialSecurityNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}

	protected String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	protected String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	protected String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	protected void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	@Override
	public String toString() {
		return   firstName +"  "+ lastName + "\n SocialSecurityNumber :"
				+ socialSecurityNumber;
	}

	@Override
	public double getPaymentAmount() {
		return 0;
	}
	
	
}
