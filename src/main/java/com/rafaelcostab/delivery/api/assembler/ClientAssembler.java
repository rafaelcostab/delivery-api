package com.rafaelcostab.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rafaelcostab.delivery.api.model.ClientModel;
import com.rafaelcostab.delivery.api.model.input.ClientInput;
import com.rafaelcostab.delivery.domain.model.Client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClientAssembler {

	private ModelMapper modelMapper;
	
	public ClientModel toModel(Client client) {
		return modelMapper.map(client, ClientModel.class);
	}
	
	public Client toEntity(ClientInput clientInput) {
		return modelMapper.map(clientInput, Client.class);
	}
	
	public List<ClientModel> toEntity(List<Client> clients){
		return clients.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
