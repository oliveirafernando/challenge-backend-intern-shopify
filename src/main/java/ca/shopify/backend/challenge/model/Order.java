package ca.shopify.backend.challenge.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Column(name = "date_time")
	@Getter
	@Setter
	private LocalDateTime dateTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@Getter
	@Setter
	private Set<LineItem> lineItems;

	@Transient
	private BigDecimal dollarValue;

	public Order() {
		this.dateTime = LocalDateTime.now();
		this.lineItems = new LinkedHashSet<LineItem>();
	}

	public BigDecimal getDollarValue() {
		return this.lineItems
				.stream()
				.map(li -> li.getProduct().getDollarValue())
				.reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
	}
}
