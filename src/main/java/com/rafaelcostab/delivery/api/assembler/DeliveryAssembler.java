package com.rafaelcostab.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rafaelcostab.delivery.api.model.DeliveryModel;
import com.rafaelcostab.delivery.api.model.input.DeliveryInput;
import com.rafaelcostab.delivery.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryAssembler {
	
	private ModelMapper modelMapper;
	
	public DeliveryModel toModel(Delivery delivery) {
		return modelMapper.map(delivery, DeliveryModel.class);
	}
	
	public List<DeliveryModel> toCollectionModel(List<Delivery> deliveries){
		return deliveries.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Delivery toEntity(DeliveryInput deliveryInput) {
		return modelMapper.map(deliveryInput, Delivery.class);
	}
}
