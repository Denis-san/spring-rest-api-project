package com.dsan.springrestapicourse.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{


	@Query("select distinct p from Product p inner join p.categories c "
			+ "where "
			+ "p.name like %:name% and "
			+ "c in :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);

}
