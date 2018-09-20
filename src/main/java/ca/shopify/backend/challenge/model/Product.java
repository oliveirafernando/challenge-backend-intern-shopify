package ca.shopify.backend.challenge.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id", nullable = false)
	@Getter
	@Setter
	private Shop shop;

//	@Getter
//	@Setter
//	private BigDecimal dollarValue;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
//	@Getter
//	@Setter
//	private Set<LineItem> lineItems;

}
