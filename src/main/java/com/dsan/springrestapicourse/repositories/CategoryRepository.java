package com.dsan.springrestapicourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsan.springrestapicourse.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
