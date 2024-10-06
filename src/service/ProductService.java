package service;

import java.util.List;

import model.Product;
import model.SubCategory;
import repository.ProductRepository;

public class ProductService {
	ProductRepository PRepo = new ProductRepository();

	public int getSubCategoryIdBySubCategoryName(String subCategoryName) {

		return PRepo.getSubCategoryIdBySubCategoryName(subCategoryName);
	}

	public boolean isAddNewProduct(Product product) {

		return PRepo.isAddNewProduct(product);
	}

	public List<Product> getAllProduct() {
		return PRepo.getAllProducts();
	}

	public Boolean DeleteProduct(String ProductName) {
		return PRepo.deleteProduct(ProductName);
	}

	public boolean updateProduct(String OldProductName, String NewProductname) {
		return PRepo.updateProduct(OldProductName, NewProductname);
	}

	public List<Product> getProductsByCategoryName(String categoryName) {

		return PRepo.getProductsByCategoryName(categoryName);
	}

	public List<Product> getProductsByName(String input) {
		
		return PRepo.getProductsByName(input);
	}

	public List<Product> getProductsBySubCategoryId(Object subCategoryIdByName) {
		
		return null;
	}

	public List<Product> getProductsBySubCategoryName(String input) {
		
		return PRepo.getProductsBySubCategoryName(input);
	}

	

}
