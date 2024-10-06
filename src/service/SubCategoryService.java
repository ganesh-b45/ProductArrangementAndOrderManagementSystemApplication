package service;

import java.util.List;

import model.Category;
import model.SubCategory;
import repository.SubCategoryRepository;

public class SubCategoryService {
	SubCategoryRepository Srepo = new SubCategoryRepository();

	public int getCategoryIdByCategoryName(String CategoryName) {

		return Srepo.getCategoryIdByCategoryName(CategoryName);
	}

	public boolean isAddNewSubCategory(SubCategory subCategory) {
		return Srepo.isAddNewSubCategory(subCategory);
	}

	public List<SubCategory> getAllSubCategory() {
		return Srepo.getAllSubCategory();

	}

	public int getSubCategoryIdByName(String input) {
		
		return Srepo.getSubCategoryIdByName(input);
	}
}
