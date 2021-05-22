package com.rafaelcostab.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.repository.DeliveryRepository;
import com.rafaelcostab.delivery.domain.service.DeliveryOrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
	
	private DeliveryRepository deliveryRepository;
	private DeliveryOrderService deliveryOrderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Delivery requestDelivery(@Valid @RequestBody Delivery delivery) {
		return deliveryOrderService.request(delivery);
	}
	
	@GetMapping
	public List<Delivery> findAll(){
		return deliveryRepository.findAll();
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<Delivery> find(@PathVariable Long deliveryId){
		return deliveryRepository.findById(deliveryId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
}
