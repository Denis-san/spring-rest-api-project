package com.dsan.springrestapicourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsan.springrestapicourse.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
