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

import com.rafaelcostab.delivery.api.assembler.DeliveryAssembler;
import com.rafaelcostab.delivery.api.model.DeliveryModel;
import com.rafaelcostab.delivery.api.model.input.DeliveryInput;
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
	private DeliveryAssembler deliveryAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryModel requestDelivery(@Valid @RequestBody DeliveryInput deliveryInput) {
		Delivery newDelivery = deliveryAssembler.toEntity(deliveryInput);
		
		Delivery deliveryRegisted = deliveryOrderService.request(newDelivery);
		
		return deliveryAssembler.toModel(deliveryRegisted);
	}
	
	@GetMapping
	public List<DeliveryModel> findAll(){
		return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryModel> find(@PathVariable Long deliveryId){
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
	}
	
}
