package com.rafaelcostab.delivery.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcostab.delivery.domain.model.Client;
import com.rafaelcostab.delivery.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClientController {

	private ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public List<Client>find() {
		return clientRepository.findAll();
	}
	
}
