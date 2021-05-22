package com.rafaelcostab.delivery.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Recipient {

	@NotBlank
	@Column(name = "recipient_name")
	private String name;
	
	@NotBlank
	@Column(name = "recipient_adress")
	private String adress;
	
	@NotBlank
	@Column(name = "recipient_number")
	private String number;
	
	@NotBlank
	@Column(name = "recipient_complement")
	private String complement;
	
	@NotBlank
	@Column(name = "recipient_neighborhood")
	private String neighborhood;
	
}
