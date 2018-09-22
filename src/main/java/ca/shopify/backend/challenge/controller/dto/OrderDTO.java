package ca.shopify.backend.challenge.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class OrderDTO {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private LocalDateTime dateTime;

	@Getter
	@Setter
	private List<LineItemDTO> lineItemsDTO;

	@Getter
	@Setter
	private BigDecimal dollarValue;
}
