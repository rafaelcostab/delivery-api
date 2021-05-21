package com.rafaelcostab.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcostab.delivery.domain.model.Client;
import com.rafaelcostab.delivery.domain.repository.ClientRepository;
import com.rafaelcostab.delivery.domain.service.ClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

	private ClientRepository clientRepository;
	private ClientService clientService; 
	
	@GetMapping
	public List<Client>find() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> findById(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client add(@Valid @RequestBody Client client) {
		return clientService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update (@PathVariable Long clientId, @Valid @RequestBody Client client){
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		client = clientService.save(client);
	
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId){
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientService.delete(clientId);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
