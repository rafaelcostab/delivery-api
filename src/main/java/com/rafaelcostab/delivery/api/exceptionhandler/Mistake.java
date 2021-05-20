package com.rafaelcostab.delivery.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mistake {
	private Integer status;
	private LocalDateTime whenOccurred;
	private String description;
	private List<field> fields;
	
	@AllArgsConstructor
	@Getter
	public static class field {
		
		private String name;
		private String message;
		
	}
}
