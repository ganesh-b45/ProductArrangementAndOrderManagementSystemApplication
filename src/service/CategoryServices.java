package service;

import java.util.List;

import model.Category;
import repository.CategoryRepository;

public class CategoryServices {
	CategoryRepository CRepo = new CategoryRepository();

	public boolean isAddNewCategory(Category category) {
		return CRepo.isAddNewCategory(category);

	}

	public List<Category> getAllCategories() {
		return CRepo.getAllCategories();
	}

	public int getCategoryIDByName(String cName) {
		return CRepo.getCategoryIDByName(cName);

	}
}
