package ca.shopify.backend.challenge.controller.dto.converter;

import ca.shopify.backend.challenge.controller.dto.ProductDTO;
import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.model.Shop;

public class ProductConverter implements DTOConverter<Product, ProductDTO> {

	@Override
	public ProductDTO apply(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		if (entity.getShop() != null) {
			dto.setShopId(entity.getShop().getId());
		}

		dto.setDollarValue(entity.getDollarValue());
		return dto;
	}

	@Override
	public Product unapply(ProductDTO dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setDollarValue(dto.getDollarValue());

		if (dto.getShopId() != null) {
			Shop shop = new Shop();
			shop.setId(dto.getShopId());
			
			product.setShop(shop);
		}

		return product;
	}
}
