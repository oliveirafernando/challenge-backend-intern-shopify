package ca.shopify.backend.challenge.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Integer amount;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "product_id", nullable = false)
//	@Getter
//	@Setter
//	private Product product;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "order_id", nullable = false)
//	@Getter
//	@Setter
//	private Order order;

	@Transient
	private BigDecimal dollarValue;

	public LineItem(Integer amount, Product product) {
		this.amount = amount;
//		this.product = product;
//		this.dollarValue = this.product.getDollarValue();
	}
}
