package ca.shopify.backend.challenge.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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
	@JoinColumn(name = "order_fk")
	@NotNull
	@Getter
	@Setter
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_fk", nullable = false)
	@NotNull
	@Getter
	@Setter
	private Product product;

	@Column(name = "amount")
	@NotNull
	@Getter
	@Setter
	private Integer amount;

	@Transient
	@Setter
	private BigDecimal dollarValue;

	public BigDecimal getDollarValue() {
		if (this.dollarValue == null && this.product != null && this.product.getDollarValue() != null
				&& this.amount != null) {
			this.dollarValue = this.product.getDollarValue().multiply(new BigDecimal(this.amount));
		}
		return dollarValue;
	}
}
