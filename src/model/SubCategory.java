package model;

public class SubCategory {
	private int subcategoryId;
	private String subcategoryName;
	private int categoryId;

	public SubCategory(int subcategoryId, String subcategoryName, int categoryId) {
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
		this.categoryId = categoryId;
	}

	public SubCategory() {

	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
