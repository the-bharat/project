package com.zykaExpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zykaExpress.custom_excpetions.ResourceNotFoundException;
import com.zykaExpress.dto.CategoryDto;
import com.zykaExpress.entities.Category;
import com.zykaExpress.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	CategoryRepository catRepo;

	public Category addCategory(Category cat) {

		return catRepo.save(cat);
	}

	public List<Category> getAllMenuTypes() {
		return catRepo.findAllAcending();
	}
	
	public Category editCategory(CategoryDto catdto,int id) {
		Category cat = catRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Category !!! Can not update details"));
		cat.setName(catdto.getName());
		return cat;
	}

	public Category getCategoryById(int catId) {
		return catRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id"));
	}
}
