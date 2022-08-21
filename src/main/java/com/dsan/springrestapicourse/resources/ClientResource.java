package com.dsan.springrestapicourse.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.services.ClientService;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Client tempClient = clientService.findById(id);
		return ResponseEntity.ok().body(tempClient);
	}

}
