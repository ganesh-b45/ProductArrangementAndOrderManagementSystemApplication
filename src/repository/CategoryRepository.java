package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.product.arrangement.config.DBConfig;
import org.product.arrangement.config.DBHelper;

import model.Category;

public class CategoryRepository extends DBHelper {
	private List<Category> list = new ArrayList<Category>();

	public boolean isAddNewCategory(Category category) {
		try {
			stmt = conn.prepareStatement("insert into category values('0',?)");
			stmt.setString(1, category.getCategory_name());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("Exception is" + e);
			return false;
		}

	}

	public List<Category> getAllCategories() {
		try {
			stmt = conn.prepareStatement("select *from category order by category_id asc ");
			rs = stmt.executeQuery();
			list.clear(); // Clear the list to avoid duplicates
			while (rs.next()) {
				Category c = new Category();
				c.setCategory_id(rs.getInt(1));
				c.setCategory_name(rs.getString(2));
				list.add(c);
			}
			return list.isEmpty() ? null : list;

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return null;
		}

	}

	public int getCategoryIDByName(String cName) {
		try {
			stmt = conn.prepareStatement("select category_id from category where catogory_name=?");
			stmt.setString(1, cName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}

		} catch (Exception e) {
			System.out.println("Exception is :" + e);
			return -1;
		}

	}

}
