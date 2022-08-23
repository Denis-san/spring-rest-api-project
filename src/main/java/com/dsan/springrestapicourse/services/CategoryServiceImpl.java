package com.dsan.springrestapicourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.repositories.CategoryRepository;
import com.dsan.springrestapicourse.services.exceptions.DataIntegrityException;
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

	@Override
	public Category update(Category category) {
		findById(category.getId());
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		findById(id);

		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException exc) {
			throw new DataIntegrityException("Can't delete a category that has products!");
		}
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
}
