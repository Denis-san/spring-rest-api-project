package com.dsan.springrestapicourse.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.dsan.springrestapicourse.domain.enums.PaymentStatus;

@Entity
public class PaymentWithTicket extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date paymentDate;
	
	public PaymentWithTicket() {

	}

	public PaymentWithTicket(Integer id, PaymentStatus paymentStatus, Order order, Date dueDate, Date paymentDate) {
		super(id, paymentStatus, order);
		
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
