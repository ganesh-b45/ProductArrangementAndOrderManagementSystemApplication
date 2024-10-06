package repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.product.arrangement.config.DBHelper;

import model.Category;
import model.SubCategory;

public class SubCategoryRepository extends DBHelper {
	private List<SubCategory> list = new ArrayList<SubCategory>();

	public int getCategoryIdByCategoryName(String CategoryName) {
		try {
			stmt = conn.prepareStatement("Select category_id from category where catogory_name=?");
			stmt.setString(1, CategoryName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1); // if Category id found it means category name is present into the database
			} else {
				return -1; // -1 indicate that Category Not Found Into Database
			}

		} catch (Exception e) {
			System.out.println("Error is :" + e);
		}
		return -1;
	}

	public boolean isAddNewSubCategory(SubCategory subCategory) {
		try {
			stmt = conn.prepareStatement("insert into subcategory values('0',?,?)");
			stmt.setString(1, subCategory.getSubcategoryName());
			stmt.setInt(2, subCategory.getCategoryId());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception e) {
			System.out.println("Error is :" + e);
			return false;
		}

	}

	public List<SubCategory> getAllSubCategory() {
		try {
			stmt = conn.prepareStatement("select *from subcategory");
			rs = stmt.executeQuery();
			list.clear(); // Clear the list to avoid duplicates
			while (rs.next()) {
				SubCategory SubCobj = new SubCategory();
				SubCobj.setSubcategoryId(rs.getInt(1));
				SubCobj.setSubcategoryName(rs.getString(2));
				SubCobj.setCategoryId(rs.getInt(3));
				list.add(SubCobj);
			}
			return list.size() > 0 ? list : null;

		} catch (SQLException e) {
			System.out.println("Error is" + e);
			return null;
		}

	}

	public int getSubCategoryIdByName(String input) {
		try {
			stmt=conn.prepareStatement("select subcategory_id from subcategory where subcategory_name like ?");
			stmt.setString(1, input);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				rs.getInt(1);
			}
			else
			{
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Exception is :"+e);
			
		}
		return -1;
	}
}
