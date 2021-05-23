package com.rafaelcostab.delivery.domain.service;

import org.springframework.stereotype.Service;

import com.rafaelcostab.delivery.domain.exception.EntityNotFoundException;
import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliverySearchService {
	
	private DeliveryRepository deliveryRepository;
	
	public Delivery find(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
	}
}
