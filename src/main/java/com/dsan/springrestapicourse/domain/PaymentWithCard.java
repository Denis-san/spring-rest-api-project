package com.dsan.springrestapicourse.domain;

import javax.persistence.Entity;

import com.dsan.springrestapicourse.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numberOfInstallments;

	public PaymentWithCard() {

	}

	public PaymentWithCard(Integer id, PaymentStatus paymentStatus, Order order, Integer numberOfInstallments) {
		super(id, paymentStatus, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

}
