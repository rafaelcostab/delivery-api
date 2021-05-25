package com.rafaelcostab.delivery.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInput {

	@NotNull
	private Long id;
	
	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String phone;
	
}
