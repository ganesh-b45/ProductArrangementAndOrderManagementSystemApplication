package model;

public class Customer {
	private int CustomerId;
	private String CustomerName;
	private String Email;
	private int age;
	private String gender;

	public Customer() {

	}

	public Customer(int CustomerId, String CustomerName, String Email, int age, String gender) {
		this.CustomerId = CustomerId;
		this.CustomerName = CustomerName;
		this.Email = Email;
		this.age = age;
		this.gender = gender;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
}
