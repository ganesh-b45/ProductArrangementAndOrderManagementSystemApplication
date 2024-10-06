package repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.product.arrangement.config.DBHelper;

import model.Product;
import model.SubCategory;

public class ProductRepository extends DBHelper {
	private List<Product> list = new ArrayList<Product>();

	public int getSubCategoryIdBySubCategoryName(String subCategoryName) {
		try {
			stmt = conn.prepareStatement("select subcategory_id from subcategory where subcategory_name=?");
			stmt.setString(1, subCategoryName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1); // if Category id found it means category name is present into the database
			} else {
				return -1; // -1 indicate that Category Not Found Into Database
			}

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return -1;
		}

	}

	public boolean isAddNewProduct(Product product) {
		try {
			stmt = conn.prepareStatement("insert into product values('0',?,?,?)");
			stmt.setString(1, product.getProductName());
			stmt.setInt(2, product.getSubCategoryId());
			stmt.setInt(3, product.getProductPrices());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}

	public List<Product> getAllProducts() {
		try {
			stmt = conn.prepareStatement("select *from product");
			rs = stmt.executeQuery();
			list.clear(); // Clear the list to avoid duplicates
			while (rs.next()) {
				Product PModel = new Product();

				PModel.setProductId(rs.getInt(1));
				PModel.setProductName(rs.getString(2));
				PModel.setSubCategoryId(rs.getInt(3));
				PModel.setProductPrices(rs.getInt(4));
				list.add(PModel);
			}
			return list.size() > 0 ? list : null;

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return null;
		}

	}

	public Boolean deleteProduct(String ProductName) {
		try {
			stmt = conn.prepareStatement("delete from product where product_name=?");
			stmt.setString(1, ProductName);
			int value = stmt.executeUpdate();
			if (value != 0) {
				System.out.println("Product deleted successfully.");
				return true;
			} else {
				System.out.println("No product found with the name: " + ProductName);
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}

	public boolean updateProduct(String OldProductName, String NewProductName) {
		try {

			stmt = conn.prepareStatement("update product set product_name=? where product_name=?");
			stmt.setString(1, NewProductName);
			stmt.setString(2, OldProductName);

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Product name updated successfully.");
				return true;
			} else {
				System.out.println("No product found with the name: " + OldProductName);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}

	public List<Product> getProductsByCategoryName(String categoryName) {
		List<Product> products = new ArrayList<>();
		try {

			String query = "select p.product_id,p.product_name,p.subcategory_id,p.Pprice from product p "
					+ "join subcategory s on p.subcategory_id=s.subcategory_id join category c on s.category_id=c.category_id"
					+ " where c.catogory_name like ? OR SOUNDEX(c.catogory_name) = SOUNDEX(?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + categoryName + "%");
			stmt.setString(2, categoryName);
//			stmt.setString(1, categoryName);
			rs = stmt.executeQuery();
			products.clear(); // Clear the list to avoid duplicates
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductPrices(rs.getInt(3));
				products.add(product);
			}

		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return null;
		}
		return products.isEmpty() ? new ArrayList<>() : products;
	}

	public List<Product> getProductsByName(String productName) {
		List<Product> products = new ArrayList<>();
		try {
//			String query = "SELECT * FROM product WHERE product_name LIKE ?";
			 String query = "SELECT * FROM product WHERE product_name LIKE ? OR SOUNDEX(product_name) = SOUNDEX(?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + productName + "%"); // Use wildcard for partial matching
			stmt.setString(2, productName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product PModel = new Product();

				PModel.setProductId(rs.getInt(1));
				PModel.setProductName(rs.getString(2));
				PModel.setSubCategoryId(rs.getInt(3));
				PModel.setProductPrices(rs.getInt(4));
				products.add(PModel);
			}
		} catch (Exception e) {
			System.out.println("Excpeion is :" + e);
			return null;
		}
		return products.isEmpty() ? new ArrayList<>() : products;
	}

	public List<Product> getProductsBySubCategoryName(String input) {
		List<Product> products = new ArrayList<>();
		try {
			String query = "SELECT p.product_id, p.product_name, p.subcategory_id, p.Pprice FROM product p " +
                    "JOIN subcategory s ON p.subcategory_id = s.subcategory_id " +
                    "WHERE s.subcategory_name LIKE ? OR SOUNDEX(s.subcategory_name) = SOUNDEX(?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + input + "%");
			stmt.setString(2, input);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product PModel = new Product();

				
				
				PModel.setProductId(rs.getInt(1));
				PModel.setProductName(rs.getString(2));
				PModel.setSubCategoryId(rs.getInt(3));
				PModel.setProductPrices(rs.getInt(4));
				products.add(PModel);
			}
		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return null;
		}
		return products.isEmpty() ? new ArrayList<>() : products;
	}
}
