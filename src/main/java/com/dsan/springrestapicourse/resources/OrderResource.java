package com.dsan.springrestapicourse.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dsan.springrestapicourse.domain.Category;
import com.dsan.springrestapicourse.domain.Order;
import com.dsan.springrestapicourse.dto.CategoryDTO;
import com.dsan.springrestapicourse.services.OrderService;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{id}")
	public ResponseEntity<Order> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Order tempOrder = orderService.findById(id);
		return ResponseEntity.ok().body(tempOrder);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Order order) {
		orderService.insert(order);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(order.getId())
					.toUri();
		return ResponseEntity.created(uri).build();
	}

}
