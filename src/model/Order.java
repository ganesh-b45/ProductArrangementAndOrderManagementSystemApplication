package model;

import java.sql.Date;

public class Order {
	private int OrderId;
	private int CustomerId;
	private String Date;

	public Order(int OrderId, int CustomerId) {
		this.OrderId = OrderId;
		this.CustomerId = CustomerId;
	}

	public Order() {

	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

}
