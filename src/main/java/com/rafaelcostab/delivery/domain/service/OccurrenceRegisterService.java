package com.rafaelcostab.delivery.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OccurrenceRegisterService {

	private DeliverySearchService deliverySearchService;
	
	@Transactional
	public Occurrence register(Long deliveryId, String description)  {
		Delivery delivery = deliverySearchService.find(deliveryId);
		
		return delivery.addOccurrence(description);
	}
	
}
