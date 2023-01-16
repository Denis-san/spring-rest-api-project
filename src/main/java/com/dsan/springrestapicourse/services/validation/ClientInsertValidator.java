package com.dsan.springrestapicourse.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsan.springrestapicourse.domain.enums.ClientType;
import com.dsan.springrestapicourse.dto.NewClientDTO;
import com.dsan.springrestapicourse.repositories.ClientRepository;
import com.dsan.springrestapicourse.resources.exceptions.FieldMessage;
import com.dsan.springrestapicourse.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientDTO> {
	
	@Autowired
	private ClientRepository repository;

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getClientType().equals(ClientType.PHYSICAL_PERSON.getCode()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
		}
		
		if (objDto.getClientType().equals(ClientType.LEGAL_PERSON.getCode()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
		}
		
		
		if(repository.findByEmail(objDto.getEmail()) != null) {
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