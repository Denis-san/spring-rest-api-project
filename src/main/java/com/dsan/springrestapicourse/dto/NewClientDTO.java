package com.dsan.springrestapicourse.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dsan.springrestapicourse.domain.Address;
import com.dsan.springrestapicourse.domain.City;
import com.dsan.springrestapicourse.domain.Client;
import com.dsan.springrestapicourse.domain.enums.ClientType;
import com.dsan.springrestapicourse.services.validation.ClientInsert;

@ClientInsert
public class NewClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Length(min = 5, max = 120)
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String cpfOrCnpj;
	
	private Integer clientType;

	@NotEmpty
	private String publicPlace;
	
	@NotEmpty
	private String number;
	
	private String complement;
	private String district;
	
	@NotEmpty
	private String zipCode;

	@NotEmpty
	private String phone1;
	private String phone2;

	private Integer cityId;

	public NewClientDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Client toClient() {
		Client client = new Client(null, name, email, cpfOrCnpj, ClientType.toEnum(clientType));
		Address address = new Address(null, publicPlace, number, complement, district, zipCode, client,
				new City(cityId, null, null));
		client.getAddresses().add(address);
		client.getPhones().add(phone1);
		if (phone2 != null) {
			client.getPhones().add(phone2);
		}

		return client;

	}

}
