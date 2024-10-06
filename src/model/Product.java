package model;

public class Product {
	public Product(int ProductId, String ProductName, int SubCategoryId, int ProductPrice) {
		this.ProductId = ProductId;
		this.ProductName = ProductName;
		this.SubCategoryId = SubCategoryId;
		this.ProductPrice = ProductPrice;
	}

	public Product() {

	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getSubCategoryId() {
		return SubCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		SubCategoryId = subCategoryId;
	}

	public int getProductPrices() {
		return ProductPrice;
	}

	public void setProductPrices(int productPrices) {
		ProductPrice = productPrices;
	}

	private int ProductId;
	private String ProductName;
	private int SubCategoryId;
	private int ProductPrice;

}
