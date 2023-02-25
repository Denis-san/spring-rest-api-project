package com.dsan.springrestapicourse.services;

import java.util.Date;

import com.dsan.springrestapicourse.domain.PaymentWithTicket;

public interface TicketService {

	void fillPaymentWithTicket(PaymentWithTicket paymnt, Date instant);

}
