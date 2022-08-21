package com.dsan.springrestapicourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.repositories.ClientRepository;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client findById(Integer id) {
		Optional<Client> optClient = clientRepository.findById(id);
		return optClient.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! id: " + id + ", Type: " + Client.class.getName()));

	}

}
