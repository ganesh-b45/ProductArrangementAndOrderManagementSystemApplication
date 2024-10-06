package repository;

import java.util.ArrayList;
import java.util.List;

import org.product.arrangement.config.DBHelper;

import model.Customer;

public class CustomerRepository extends DBHelper {
	private List<Customer> list = new ArrayList<Customer>();

	public boolean isAddNewCustomer(Customer customer) {
		try {
			stmt = conn.prepareStatement("insert into customer values('0',?,?,?,?)");
			stmt.setString(1, customer.getCustomerName());
			stmt.setInt(2, customer.getAge());
			stmt.setString(3, customer.getGender());
			stmt.setString(4, customer.getEmail());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}

	public List<Customer> getAllCustomer() {
		try {
			stmt = conn.prepareStatement("select *from customer");
			rs = stmt.executeQuery();
			list.clear(); // Clear the list to avoid duplicates
			while (rs.next()) {
				Customer customerModel = new Customer();
				customerModel.setCustomerId(rs.getInt(1));
				customerModel.setCustomerName(rs.getString(2));
				customerModel.setAge(rs.getInt(3));
				customerModel.setGender(rs.getString(4));
				customerModel.setEmail(rs.getString(5));
				list.add(customerModel);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return null;
		}

	}

	public boolean isDeleteCustomer(String CustomerName) {
		try {
			stmt = conn.prepareStatement("delete from customer where customer_name=?");
			stmt.setString(1, CustomerName);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected != 0) {
				System.out.println("Customer deleted successfully.");
				return true;
			} else {
				System.out.println("No Customer found with the name: " + CustomerName);
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}

	public boolean isUpdateCustomerbyName(String OldName, String NewName) {
		try {
			stmt = conn.prepareStatement("update customer set customer_name=? where customer_name=?");
			stmt.setString(1, NewName);
			stmt.setString(2, OldName);

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Product name updated successfully.");
				return true;
			} else {
				System.out.println("No product found with the name: " + OldName);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}
}
