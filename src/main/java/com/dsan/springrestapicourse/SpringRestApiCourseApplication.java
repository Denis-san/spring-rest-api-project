package com.dsan.springrestapicourse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.domain.City;
import com.dsan.springrestapicourse.domain.Product;
import com.dsan.springrestapicourse.domain.State;
import com.dsan.springrestapicourse.repositories.CategoryRepository;
import com.dsan.springrestapicourse.repositories.CityRepository;
import com.dsan.springrestapicourse.repositories.ProductRepository;
import com.dsan.springrestapicourse.repositories.StateRepository;

@SpringBootApplication
public class SpringRestApiCourseApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Categories and products instantiations

		Category cat = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat));
		p2.getCategories().addAll(Arrays.asList(cat, cat2));
		p3.getCategories().addAll(Arrays.asList(cat));

		categoryRepository.saveAll(Arrays.asList(cat, cat2));

		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		// Cities and states instantiations

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));

		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

	}

}