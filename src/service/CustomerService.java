package service;

import java.util.List;

import model.Customer;
import repository.CustomerRepository;

public class CustomerService {
	CustomerRepository CustomerRepo = new CustomerRepository();

	public boolean isAddNewCustomer(Customer customer) {
		return CustomerRepo.isAddNewCustomer(customer);
	}

	public List<Customer> getAllCustomer() {
		return CustomerRepo.getAllCustomer();
	}

	public boolean isDeleteCustomer(String CustomerName) {
		return CustomerRepo.isDeleteCustomer(CustomerName);
	}

	public boolean isUpdateCustomerbyName(String OldName, String NewName) {
		return CustomerRepo.isUpdateCustomerbyName(OldName, NewName);
	}
}
