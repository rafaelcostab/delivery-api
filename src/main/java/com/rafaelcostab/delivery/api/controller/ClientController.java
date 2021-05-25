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

import com.rafaelcostab.delivery.api.assembler.ClientAssembler;
import com.rafaelcostab.delivery.api.model.ClientModel;
import com.rafaelcostab.delivery.api.model.input.ClientInput;
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
	private ClientAssembler clientAssembler;
	
	@GetMapping
	public List<ClientModel>find() {
		return clientAssembler.toEntity(clientRepository.findAll());
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<ClientModel> findById(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
			.map(client -> ResponseEntity.ok(clientAssembler.toModel(client)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientModel add(@Valid @RequestBody ClientInput clientInput) {
		Client newClient = clientAssembler.toEntity(clientInput);
		
		Client clientRegisted = clientService.save(newClient);
		
		return clientAssembler.toModel(clientRegisted);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ClientModel> update (@Valid @RequestBody ClientInput clientInput){
		if (!clientRepository.existsById(clientInput.getId())) {
			return ResponseEntity.notFound().build();
		}
		
		Client client = clientAssembler.toEntity(clientInput);
		
		Client clientUpdated = clientService.save(client);
	
		return ResponseEntity.ok(clientAssembler.toModel(clientUpdated));
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
