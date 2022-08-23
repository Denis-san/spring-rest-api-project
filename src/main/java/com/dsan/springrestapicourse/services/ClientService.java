package com.dsan.springrestapicourse.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dsan.springrestapicourse.domain.Client;

public interface ClientService {

	public Client findById(Integer id);

	public Client update(Client client);

	public void delete(Integer id);

	public List<Client> findAll();

	public Page<Client> findPage(Integer page, Integer linesPerPages, String orderBy, String direction);
	
}
