package com.dsan.springrestapicourse.services;

import java.util.List;

import com.dsan.springrestapicourse.domain.Category;

public interface CategoryService {

	public Category findById(Integer id);

	public Category insert(Category category);

	public Category update(Category category);

	public void delete(Integer id);

	public List<Category> findAll();

}
