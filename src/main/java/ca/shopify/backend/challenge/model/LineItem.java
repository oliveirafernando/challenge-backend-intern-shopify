package ca.shopify.backend.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_line_item")
public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_fk", nullable = false)
	@Getter
	@Setter
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_fk", nullable = false)
	@Getter
	@Setter
	private Product product;
	
	@Column(name = "amount")
	@Getter
	@Setter
	private Integer amount;

//	@Transient
//	private BigDecimal dollarValue;

	public LineItem(Integer amount, Product product) {
		this.amount = amount;
		this.product = product;
//		this.dollarValue = this.product.getDollarValue();
	}
}
