package com.dsan.springrestapicourse.domain.enums;

public enum ClientType {
	PHYSICAL_PERSON(1, "Pessoa Física"), LEGAL_PERSON(2, "Pessoa Jurídica");

	private int code;
	private String description;

	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (ClientType tempClientType : ClientType.values()) {
			if (code.equals(tempClientType.getCode())) {
				return tempClientType;
			}
		}

		throw new IllegalArgumentException("Invalid id: " + code);

	}

}
