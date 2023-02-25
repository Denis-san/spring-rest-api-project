package com.dsan.springrestapicourse.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Order;
import com.dsan.springrestapicourse.domain.OrderItem;
import com.dsan.springrestapicourse.domain.PaymentWithTicket;
import com.dsan.springrestapicourse.domain.enums.PaymentStatus;
import com.dsan.springrestapicourse.repositories.OrderItemRepository;
import com.dsan.springrestapicourse.repositories.OrderRepository;
import com.dsan.springrestapicourse.services.exceptions.ObjectNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemRepository itemRepository;

	@Override
	public Order findById(Integer id) {
		Optional<Order> optOrder = orderRepository.findById(id);
		return optOrder.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! id: " + id + ", Type: " + Order.class.getName()));
	}

	@Override
	public Order insert(Order order) {
		order.setId(null);
		order.setInstant(new Date());
		
		order.getPayment().setPaymentStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		
		if(order.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket paymnt = (PaymentWithTicket) order.getPayment();
			ticketService.fillPaymentWithTicket(paymnt, order.getInstant());
		}
		
		order = orderRepository.save(order);
		paymentService.insert(order.getPayment());
		
		for(OrderItem item : order.getItems()) {
			item.setDiscount(0.0);
			item.setPrice(productService.findById(item.getProduct().getId()).getPrice());
			item.setOrder(order);
		}
		
		itemRepository.saveAll(order.getItems());
		
		return order;
	}

}
