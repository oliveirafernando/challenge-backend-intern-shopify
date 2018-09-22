package ca.shopify.backend.challenge.controller.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class ProductDTO{

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private ShopDTO shop;

	@Getter
	@Setter
	private BigDecimal dollarValue;

}
