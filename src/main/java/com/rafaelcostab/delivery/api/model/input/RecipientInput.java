package com.rafaelcostab.delivery.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientInput {

	@NotBlank
	private String name;
	
	@NotBlank
	private String adress;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String complement;
	
	@NotBlank
	private String neighborhood;
	
}
