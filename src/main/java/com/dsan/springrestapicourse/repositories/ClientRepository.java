package com.dsan.springrestapicourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsan.springrestapicourse.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
