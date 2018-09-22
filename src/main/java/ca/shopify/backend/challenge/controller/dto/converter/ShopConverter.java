package ca.shopify.backend.challenge.controller.dto.converter;

import ca.shopify.backend.challenge.controller.dto.ShopDTO;
import ca.shopify.backend.challenge.model.Shop;

public class ShopConverter implements DTOConverter<Shop, ShopDTO> {

	@Override
	public ShopDTO apply(Shop entity) {
		ShopDTO dto = new ShopDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public Shop unapply(ShopDTO dto) {
		Shop shop = new Shop();
		shop.setId(dto.getId());
		shop.setName(dto.getName());
		return shop;
	}

}
