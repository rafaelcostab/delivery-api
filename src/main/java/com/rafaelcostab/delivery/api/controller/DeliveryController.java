package com.rafaelcostab.delivery.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.service.DeliveryOrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
	
	private DeliveryOrderService deliveryOrderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Delivery requestDelivery(@RequestBody Delivery delivery) {
		return deliveryOrderService.request(delivery);
	}
}
