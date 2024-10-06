package service;

import java.util.List;

import model.Order;
import model.Product;
import repository.OrderRepository;

public class OrderService {
	OrderRepository OrderRepoObj = new OrderRepository();

	public int getCustomerIdbyEmail(String CustomerEmail) {
		return OrderRepoObj.getCustomerIdbyEmail(CustomerEmail);
	}

	public boolean isAddOrder(Order om) {
		return OrderRepoObj.isAddOrder(om);
	}

	public boolean OrderDetail(Order orderModelObj, int productID, int quantity) {

		return OrderRepoObj.OrderDetail(orderModelObj, productID, quantity);
	}

	public List<Order> getOrdersByCustomerEmail(String email) {
		return OrderRepoObj.getOrdersByCustomerEmail(email);
	}

	public List<model.OrderDetail> getOrderDetailsByOrderId(int OrderID) {
		return OrderRepoObj.getOrderDetailsByOrderId(OrderID);

	}

	public List<Order> getOrdersByProductName(String productName) {

		return OrderRepoObj.getOrdersByProductName(productName);
	}

	public boolean isProductExistsByName(String productName) {
		return OrderRepoObj.isProductExistsByName(productName);
	}

	public List<Product> getProductDetailsByOrderId(int orderId) {
		return OrderRepoObj.getProductDetailsByOrderId(orderId);
	}

	public List<Product> getCommonProductsInAllOrders() {
		return OrderRepoObj.getCommonProductsInAllOrders();
	}
}
