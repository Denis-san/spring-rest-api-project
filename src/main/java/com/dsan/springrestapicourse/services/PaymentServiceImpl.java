package com.dsan.springrestapicourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsan.springrestapicourse.domain.Payment;
import com.dsan.springrestapicourse.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public void insert(Payment payment) {
		paymentRepository.save(payment);
	}


}
