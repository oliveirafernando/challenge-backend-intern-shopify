package ca.shopify.backend.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_shop")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
//	@Getter
//	@Setter
//	private Set<Product> products;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
//	@Getter
//	@Setter
//	private Set<Order> orders;

}
