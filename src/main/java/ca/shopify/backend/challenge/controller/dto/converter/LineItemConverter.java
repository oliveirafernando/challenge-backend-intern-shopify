package ca.shopify.backend.challenge.controller.dto.converter;

import ca.shopify.backend.challenge.controller.dto.LineItemDTO;
import ca.shopify.backend.challenge.model.LineItem;

public class LineItemConverter implements DTOConverter<LineItem, LineItemDTO> {

	private ProductConverter productConverter = new ProductConverter();

	@Override
	public LineItemDTO apply(LineItem entity) {
		LineItemDTO dto = new LineItemDTO();
		dto.setId(entity.getId());
		dto.setAmount(entity.getAmount());
		
		if(entity.getProduct() != null && entity.getProduct().getShop() != null) {
			entity.getProduct().setShop(null);
		}
		dto.setProductDTO(productConverter.apply(entity.getProduct()));

		return dto;
	}

	@Override
	public LineItem unapply(LineItemDTO dto) {
		LineItem lineItem = new LineItem();
		lineItem.setId(dto.getId());
		lineItem.setDollarValue(dto.getDollarValue());
		lineItem.setProduct(productConverter.unapply(dto.getProductDTO()));

		return lineItem;
	}
}
