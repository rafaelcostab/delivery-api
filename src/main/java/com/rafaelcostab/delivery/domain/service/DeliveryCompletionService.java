package com.rafaelcostab.delivery.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliveryCompletionService {

	DeliverySearchService deliverySearchService;
	DeliveryRepository deliveryRepository;
	
	
	@Transactional
	public void finish(Long deliveryId) {
		Delivery delivery = deliverySearchService.find(deliveryId);
		
		delivery.finish();
		
		deliveryRepository.save(delivery);
	}
	
}
