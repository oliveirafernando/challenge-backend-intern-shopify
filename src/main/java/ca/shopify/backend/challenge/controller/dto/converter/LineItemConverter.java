package ca.shopify.backend.challenge.controller.dto.converter;

import ca.shopify.backend.challenge.controller.dto.LineItemDTO;
import ca.shopify.backend.challenge.model.LineItem;
import ca.shopify.backend.challenge.model.Product;

public class LineItemConverter implements DTOConverter<LineItem, LineItemDTO> {

	@Override
	public LineItemDTO apply(LineItem entity) {
		LineItemDTO dto = new LineItemDTO();
		dto.setId(entity.getId());
		dto.setAmount(entity.getAmount());
		dto.setDollarValue(entity.getDollarValue());

		if (entity.getProduct() != null && entity.getProduct().getShop() != null) {
			entity.getProduct().setShop(null);
		}
		dto.setProductId(entity.getProduct().getId());

		return dto;
	}

	@Override
	public LineItem unapply(LineItemDTO dto) {
		LineItem lineItem = new LineItem();
		lineItem.setId(dto.getId());
		lineItem.setDollarValue(dto.getDollarValue());
		lineItem.setAmount(dto.getAmount());

		Product product = new Product();
		product.setId(dto.getProductId());
		lineItem.setProduct(product);

		return lineItem;
	}
}
