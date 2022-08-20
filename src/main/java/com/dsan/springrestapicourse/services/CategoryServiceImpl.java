package com.dsan.springrestapicourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findById(Integer id) {
		Optional<Category> optCategory = categoryRepository.findById(id);
		return optCategory.orElse(null);

	}

}
