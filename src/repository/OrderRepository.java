package repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.product.arrangement.config.DBHelper;

import model.Order;
import model.Product;

public class OrderRepository extends DBHelper {
	public int getCustomerIdbyEmail(String CustomerEmail) {
		try {
			stmt = conn.prepareStatement("select customer_id from customer where email=?");
			stmt.setString(1, CustomerEmail);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return 0;
		}

	}

	public boolean isAddOrder(Order om) {
		try {
			stmt = conn.prepareStatement("insert into orders (customer_id) values(?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, om.getCustomerId());
			int value = stmt.executeUpdate();
			if (value > 0) {
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					om.setOrderId(rs.getInt(1)); // Set the generated order ID
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return false;
		}

	}

	public boolean OrderDetail(Order orderModelObj, int productID, int quantity) {
		try {
			stmt = conn.prepareStatement("insert into order_details values('0',?,?,?)");
			stmt.setInt(1, orderModelObj.getOrderId());
			stmt.setInt(2, productID);
			stmt.setInt(3, quantity);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return false;
		}

	}

	public List<Order> getOrdersByCustomerEmail(String email) {
		List<Order> orders = new ArrayList<>();
		try {
			int customerId = this.getCustomerIdbyEmail(email);
			if (customerId != -1) {
				stmt = conn.prepareStatement("SELECT * FROM orders WHERE customer_id = ?");
				stmt.setInt(1, customerId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setCustomerId(rs.getInt("customer_id"));
					orders.add(order);
				}
//                return orders.isEmpty()?null:orders;
			}
		} catch (Exception e) {
			System.out.println("Exceptioon is :" + e);
			return null;
		}
		return orders.isEmpty() ? null : orders;

	}

	public List<model.OrderDetail> getOrderDetailsByOrderId(int orderID) {
		List<model.OrderDetail> orderdetail = new ArrayList<>();
		try {
			if (orderID != -1) {
				stmt = conn.prepareStatement("select *from order_details where order_id=?");

				stmt.setInt(1, orderID);
				rs = stmt.executeQuery();
				while (rs.next()) {
					model.OrderDetail od = new model.OrderDetail();
					od.setOrderDetailId(rs.getInt("order_details_id"));
					od.setOrderId(rs.getInt("order_id"));
					od.setProductId(rs.getInt("product_id"));
					od.setQuantity(rs.getInt("quantity"));
					orderdetail.add(od);
				}
//			return orderdetail.isEmpty()?null:orderdetail;
			}

		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return null;
		}
		return orderdetail.isEmpty() ? null : orderdetail;

	}

	public List<Order> getOrdersByProductName(String productName) {
		List<Order> orders = new ArrayList<>();
		try {

			stmt = conn.prepareStatement("SELECT o.order_id, o.customer_id FROM orders o "
					+ "JOIN order_details od ON o.order_id = od.order_id "
					+ "JOIN product p ON od.product_id = p.product_id " + "WHERE p.product_name = ?");
			stmt.setString(1, productName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id")); // Ensure column names are correct
				order.setCustomerId(rs.getInt("customer_id"));
				orders.add(order);
			}
		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return null;
		}
		return orders.isEmpty() ? null : orders;
	}

	public boolean isProductExistsByName(String productName) {
		try {
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM product WHERE product_name = ?");
			stmt.setString(1, productName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.out.println("Error checking if product exists: " + e.getMessage());
		}
		return false;
	}

	public List<Product> getProductDetailsByOrderId(int orderId) {
		List<Product> productList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(
					"SELECT p.product_id, p.product_name, od.quantity, p.Pprice " + "FROM order_details od "
							+ "JOIN product p ON od.product_id = p.product_id " + "WHERE od.order_id = ?");
			stmt.setInt(1, orderId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrices(rs.getInt("Pprice"));
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
			return null;
		}
		return productList;
	}

	public List<Product> getCommonProductsInAllOrders() {
		List<Product> commonProducts = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("SELECT p.product_id, p.product_name, p.Pprice " + "FROM order_details od "
					+ "JOIN product p ON od.product_id = p.product_id "
					+ "GROUP BY p.product_id, p.product_name, Pprice "
					+ "HAVING COUNT(DISTINCT od.order_id) = (SELECT COUNT(DISTINCT order_id) FROM order_details)");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrices(rs.getInt("Pprice"));
				commonProducts.add(product);
			}
		} catch (SQLException e) {
			System.out.println("Exception is :" + e);
		}
		return commonProducts;
	}
}
