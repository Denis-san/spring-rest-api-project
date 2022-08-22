package com.dsan.springrestapicourse.services;

import com.dsan.springrestapicourse.domain.Category;

public interface CategoryService {

	public Category findById(Integer id);

	public Category insert(Category category);

}
