package com.dsan.springrestapicourse.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	private List<FieldMessage> listError = new ArrayList<FieldMessage>();

	public List<FieldMessage> getErrors() {
		return listError;
	}

	public void addError(String field, String message) {
		listError.add(new FieldMessage(field, message));
	}

}
