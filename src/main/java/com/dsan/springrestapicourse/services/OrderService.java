package com.dsan.springrestapicourse.services;

import com.dsan.springrestapicourse.domain.Order;

public interface OrderService {

	public Order findById(Integer id);

	public Order insert(Order order);

}
