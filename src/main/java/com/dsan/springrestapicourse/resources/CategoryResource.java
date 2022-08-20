package com.dsan.springrestapicourse.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@GetMapping("/list")
	public String list() {
		return "Rest testing!";
	}

}
