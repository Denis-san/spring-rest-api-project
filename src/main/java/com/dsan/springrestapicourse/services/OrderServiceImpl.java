package com.dsan.springrestapicourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Order;
import com.dsan.springrestapicourse.repositories.OrderRepository;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findById(Integer id) {
		Optional<Order> optOrder = orderRepository.findById(id);
		return optOrder.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! id: " + id + ", Type: " + Order.class.getName()));

	}

}
