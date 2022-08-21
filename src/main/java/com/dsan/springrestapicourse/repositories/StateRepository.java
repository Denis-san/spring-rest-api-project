package com.dsan.springrestapicourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsan.springrestapicourse.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
