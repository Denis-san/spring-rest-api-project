package com.dsan.springrestapicourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.repositories.CategoryRepository;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findById(Integer id) {
		Optional<Category> optCategory = categoryRepository.findById(id);
		return optCategory.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! id: " + id + ", Type: " + Category.class.getName()));

	}

	@Override
	public Category insert(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}

}
