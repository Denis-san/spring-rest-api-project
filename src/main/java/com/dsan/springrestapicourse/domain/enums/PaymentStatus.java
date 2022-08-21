package com.dsan.springrestapicourse.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pendente"), PAID_OF(2, "Quitado"), CANCELED(3, "Cancelado");

	private int code;
	private String description;

	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentStatus toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (PaymentStatus tempPaymentStatus : PaymentStatus.values()) {
			if (code.equals(tempPaymentStatus.getCode())) {
				return tempPaymentStatus;
			}
		}

		throw new IllegalArgumentException("Invalid id: " + code);

	}

}
