package com.dsan.springrestapicourse.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dsan.springrestapicourse.domain.Product;

public interface ProductService {

	public Product findById(Integer id);
	
	public Page<Product> search(String nom, List<Integer> ids, Integer page, Integer linesPerPages, String orderBy, String direction);

}
