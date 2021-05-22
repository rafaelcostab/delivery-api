package com.rafaelcostab.delivery.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rafaelcostab.delivery.domain.model.Client;
import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.model.DeliveryStatus;
import com.rafaelcostab.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliveryOrderService {

	private DeliveryRepository deliveryRepository;
	private ClientService clientService;
	
	@Transactional
	public Delivery request(Delivery delivery) {
		
		Client client = clientService.findById(delivery.getClient().getId());
		
		delivery.setClient(client);
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setDateOrder(OffsetDateTime.now());
		
		return deliveryRepository.save(delivery);
	}
	
}
