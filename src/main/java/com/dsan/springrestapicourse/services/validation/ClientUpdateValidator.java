package com.dsan.springrestapicourse.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.dto.ClientDTO;
import com.dsan.springrestapicourse.repositories.ClientRepository;
import com.dsan.springrestapicourse.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository repository;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> mapAtt = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urlId = Integer.parseInt(mapAtt.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		Client clientBd = repository.findByEmail(objDto.getEmail());

		
		if(clientBd != null && !clientBd.getId().equals(urlId)) {
			list.add(new FieldMessage("email", "Email already exists"));
		}
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}