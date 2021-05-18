package com.rafaelcostab.delivery.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.model.Client;

@RestController
public class ClientController {

	@GetMapping("/clients")
	public List<Client>find() {
		Client client = new Client();
		
		client.setId(1L);
		client.setName("Rafael Costa");
		client.setEmail("rafael.costab@hotmail.com");
		client.setPhone("11 92233-4455");
		
		return Arrays.asList(client);
	}
	
}
