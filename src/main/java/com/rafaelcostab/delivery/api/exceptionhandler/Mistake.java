package com.rafaelcostab.delivery.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Mistake {
	
	private Integer status;
	private OffsetDateTime whenOccurred;
	private String description;
	private List<field> fields;
	
	@AllArgsConstructor
	@Getter
	public static class field {
		
		private String name;
		private String message;
		
	}
}
