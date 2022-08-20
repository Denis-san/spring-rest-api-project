package com.dsan.springrestapicourse.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsan.springrestapicourse.domain.Category;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@GetMapping("/list")
	public List<Category> list() {

		Category cat = new Category(1, "Computing");
		Category cat2 = new Category(2, "Office");

		List<Category> list = new ArrayList<Category>();

		list.add(cat);
		list.add(cat2);

		return list;
	}

}
