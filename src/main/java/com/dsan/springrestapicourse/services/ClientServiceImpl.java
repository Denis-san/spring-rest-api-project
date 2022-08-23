package com.dsan.springrestapicourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.repositories.ClientRepository;
import com.dsan.springrestapicourse.services.exceptions.DataIntegrityException;
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

	@Override
	public Client update(Client client) {
		Client clientToUpdate = findById(client.getId());

		clientToUpdate.setName(client.getName());
		clientToUpdate.setEmail(client.getEmail());

		return clientRepository.save(clientToUpdate);
	}

	@Override
	public void delete(Integer id) {
		findById(id);

		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException exc) {
			throw new DataIntegrityException("Can't delete a client that has orders!");
		}
	}

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Page<Client> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

}
