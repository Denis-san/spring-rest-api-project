package com.dsan.springrestapicourse.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.dto.ClientDTO;
import com.dsan.springrestapicourse.services.ClientService;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@GetMapping("/{id}")
	public ResponseEntity<Client> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Client tempClient = clientService.findById(id);
		return ResponseEntity.ok().body(tempClient);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDto, @PathVariable Integer id) {
		Client client = clientDto.toClient();
		client.setId(id);
		client = clientService.update(client);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping()
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> listClient = clientService.findAll();
		List<ClientDTO> listDTO = listClient.stream().map(e -> new ClientDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPages,
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Client> listClient = clientService.findPage(page, linesPerPages, orderBy, direction);
		Page<ClientDTO> listDTO = listClient.map(e -> new ClientDTO(e));
		return ResponseEntity.ok().body(listDTO);
	}


}
