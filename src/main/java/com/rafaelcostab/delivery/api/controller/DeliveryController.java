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

import com.rafaelcostab.delivery.api.model.DeliveryModel;
import com.rafaelcostab.delivery.api.model.RecipientModel;
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
	public ResponseEntity<DeliveryModel> find(@PathVariable Long deliveryId){
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> {
					DeliveryModel model = new DeliveryModel();
					model.setId(delivery.getId());
					model.setClientName(delivery.getClient().getName());

					model.setRecipient(new RecipientModel());
					model.getRecipient().setName(delivery.getRecipient().getName());
					model.getRecipient().setAdress(delivery.getRecipient().getAdress());
					model.getRecipient().setNumber(delivery.getRecipient().getNumber());
					model.getRecipient().setComplement(delivery.getRecipient().getComplement());
					model.getRecipient().setNeighborhood(delivery.getRecipient().getNeighborhood());
					
					model.setTax(delivery.getTax());
					model.setStatus(delivery.getStatus());
					model.setDateOrder(delivery.getDateOrder());
					model.setDateFinished(delivery.getDateFinished());
					
					return ResponseEntity.ok(model);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}
