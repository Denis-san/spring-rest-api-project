package com.dsan.springrestapicourse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.repositories.CategoryRepository;

@SpringBootApplication
public class SpringRestApiCourseApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		categoryRepository.saveAll(Arrays.asList(cat, cat2));

	}

}
