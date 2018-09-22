package ca.shopify.backend.challenge.controller.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import ca.shopify.backend.challenge.controller.dto.LineItemDTO;
import ca.shopify.backend.challenge.controller.dto.OrderDTO;
import ca.shopify.backend.challenge.model.LineItem;
import ca.shopify.backend.challenge.model.Order;

public class OrderConverter implements DTOConverter<Order, OrderDTO> {

	private LineItemConverter lineItemConverter = new LineItemConverter();

	@Override
	public OrderDTO apply(Order entity) {
		OrderDTO dto = new OrderDTO();
		dto.setId(entity.getId());
		dto.setDateTime(entity.getDateTime());
		dto.setDollarValue(entity.getDollarValue());

		if (entity.getLineItems() != null) {
			List<LineItemDTO> lineItems = entity.getLineItems().stream().map(li -> lineItemConverter.apply(li))
					.collect(Collectors.toList());

			dto.setLineItemsDTO(lineItems);
		}

		return dto;
	}

	@Override
	public Order unapply(OrderDTO dto) {
		Order order = new Order();
		order.setId(dto.getId());
		order.setDateTime(dto.getDateTime());
		order.setDollarValue(dto.getDollarValue());

		if (dto.getDollarValue() != null) {
			List<LineItem> lineItems = dto.getLineItemsDTO().stream().map(li -> lineItemConverter.unapply(li))
					.collect(Collectors.toList());

			order.setLineItems(lineItems);
		}

		return order;
	}

}
