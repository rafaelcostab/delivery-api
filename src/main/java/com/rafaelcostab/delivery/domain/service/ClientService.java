package com.rafaelcostab.delivery.domain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelcostab.delivery.domain.exception.BusinessException;
import com.rafaelcostab.delivery.domain.model.Client;
import com.rafaelcostab.delivery.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientService {

	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client client) {
		
		validateEmail(client);
		
		return clientRepository.save(client);
	}

	private void validateEmail(Client client) {
		boolean isExistingEmail = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(clientExist -> !clientExist.equals(client));
		
		if (isExistingEmail) {
			throw new BusinessException("Email já existente!");
		}
	}
	
	@Transactional
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
	
	public Client findById(Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new BusinessException("Cliente não encontrado"));
	}
	
}
