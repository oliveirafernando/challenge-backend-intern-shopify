package ca.shopify.backend.challenge.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;


public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Getter
	@Setter
	private Long id;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
//	@Getter
//	@Setter
//	private Set<LineItem> lineItems;

//	@Transient
//	private BigDecimal dollarValue;

//	public BigDecimal getDollarValue() {
//		return this.lineItems
//				.stream()
//				.map(li -> li.getProduct().getDollarValue())
//				.reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
//	}
}
