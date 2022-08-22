package com.dsan.springrestapicourse.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.services.CategoryService;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Category tempCategory = categoryService.findById(id);
		return ResponseEntity.ok().body(tempCategory);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		category = categoryService.insert(category);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(category.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
