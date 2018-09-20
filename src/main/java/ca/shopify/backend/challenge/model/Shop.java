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

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
