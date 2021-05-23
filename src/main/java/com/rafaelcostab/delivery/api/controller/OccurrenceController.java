package com.rafaelcostab.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcostab.delivery.api.assembler.OccurrenceAssembler;
import com.rafaelcostab.delivery.api.model.OccurrenceModel;
import com.rafaelcostab.delivery.api.model.input.OccurrenceInput;
import com.rafaelcostab.delivery.domain.model.Delivery;
import com.rafaelcostab.delivery.domain.model.Occurrence;
import com.rafaelcostab.delivery.domain.service.DeliverySearchService;
import com.rafaelcostab.delivery.domain.service.OccurrenceRegisterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

	private DeliverySearchService deliverySearchService;
	private OccurrenceRegisterService ocurrenceRegisterService;
	private OccurrenceAssembler occurrenceAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceModel register(@PathVariable Long deliveryId,
			@Valid @RequestBody OccurrenceInput occurrenceInput) {
		Occurrence ocurrenceRegisted = ocurrenceRegisterService
				.register(deliveryId, occurrenceInput.getDescription());
		
		return occurrenceAssembler.toModel(ocurrenceRegisted);
		
	}
	
	@GetMapping
	public List<OccurrenceModel> find(@PathVariable Long deliveryId){
		Delivery delivery = deliverySearchService.find(deliveryId);
		
		return occurrenceAssembler.toCollectionModel(delivery.getOccurrences());
	}
	
}
