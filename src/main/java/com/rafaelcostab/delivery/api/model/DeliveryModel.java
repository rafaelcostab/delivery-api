package com.rafaelcostab.delivery.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.rafaelcostab.delivery.domain.model.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModel {
	
	private Long id;
	private ClientResumeModel client;
	private RecipientModel recipient;
	private BigDecimal tax;
	private DeliveryStatus status;
	private OffsetDateTime dateOrder;
	private OffsetDateTime dateFinished;
	
}
