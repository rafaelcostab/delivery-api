package com.rafaelcostab.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rafaelcostab.delivery.api.model.OccurrenceModel;
import com.rafaelcostab.delivery.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OccurrenceAssembler {
	private ModelMapper modelMapper;
	
	public OccurrenceModel toModel(Occurrence occurrence) {
		return modelMapper.map(occurrence, OccurrenceModel.class);
	}
	
	public List<OccurrenceModel> toCollectionModel(List<Occurrence> occurreces){
		return occurreces.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}
