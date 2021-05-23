package com.rafaelcostab.delivery.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rafaelcostab.delivery.domain.exception.BusinessException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "deliveries", schema = "public")
public class Delivery {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;
	
	@Embedded
	private Recipient recipient;
	
	private BigDecimal tax;

	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
	private List<Occurrence> occurrences = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
	private OffsetDateTime dateOrder;
	
	private OffsetDateTime dateFinished;

	public Occurrence addOccurrence(String description) {
		Occurrence occurrence = new Occurrence();
		occurrence.setDescription(description);
		occurrence.setDateRegister(OffsetDateTime.now());
		occurrence.setDelivery(this);
		
		this.getOccurrences().add(occurrence);
		
		return occurrence;
	}

	public void finish() {
		
		if (cannotBeFinalized()) {
			throw new BusinessException("Entrega não pode ser finalizada");
		}
		
		setStatus(DeliveryStatus.FINISHED);
		setDateFinished(OffsetDateTime.now());
	}

	public boolean canBeFinalized() {
		return DeliveryStatus.PENDING.equals(getStatus());
	}
	
	public boolean cannotBeFinalized() {
		return !canBeFinalized();
	}
	
}