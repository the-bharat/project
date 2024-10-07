package com.zykaExpress.service;

import java.util.List;

import com.zykaExpress.dto.CategoryDto;
import com.zykaExpress.entities.Category;

public interface ICategoryService {

	public Category addCategory(Category cat);
	
	public List<Category> getAllMenuTypes() ;
	
	public Category editCategory(CategoryDto catdto,int id);
	
	public Category getCategoryById(int catId);
}
