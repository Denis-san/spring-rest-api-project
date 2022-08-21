package com.dsan.springrestapicourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsan.springrestapicourse.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
