 package com.rafaelcostab.delivery.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rafaelcostab.delivery.domain.exception.BusinessException;
import com.rafaelcostab.delivery.domain.exception.EntityNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExcceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Mistake.field> fields = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Mistake.field(name, message));
			
		}
		
		Mistake mistake = new Mistake();
		mistake.setStatus(status.value());
		mistake.setWhenOccurred(OffsetDateTime.now());
		mistake.setDescription("Há campo(s) inválido(s)!");
		mistake.setFields(fields);
		 
		return handleExceptionInternal(ex, mistake, headers, status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleBusiness(EntityNotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Mistake mistake = new Mistake();
		mistake.setStatus(status.value());
		mistake.setWhenOccurred(OffsetDateTime.now());
		mistake.setDescription(ex.getMessage());
				
		return handleExceptionInternal(ex, mistake, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Mistake mistake = new Mistake();
		mistake.setStatus(status.value());
		mistake.setWhenOccurred(OffsetDateTime.now());
		mistake.setDescription(ex.getMessage());
				
		return handleExceptionInternal(ex, mistake, new HttpHeaders(), status, request);
	}
}
