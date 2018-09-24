package ca.shopify.backend.challenge.controller.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class LineItemDTO {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Long productId;

	@Getter
	@Setter
	private Integer amount;

	@Getter
	@Setter
	private BigDecimal dollarValue;
}