package ca.shopify.backend.challenge.controller.dto.converter;

import ca.shopify.backend.challenge.controller.dto.ProductDTO;
import ca.shopify.backend.challenge.model.Product;

public class ProductConverter implements DTOConverter<Product, ProductDTO> {

	private ShopConverter shopConverter;

	public ProductConverter() {
		this.shopConverter = new ShopConverter();
	}

	@Override
	public ProductDTO apply(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		if(entity.getShop() != null) {
			dto.setShopDTO(this.shopConverter.apply(entity.getShop()));			
		}
		
		dto.setDollarValue(entity.getDollarValue());
		return dto;
	}

	@Override
	public Product unapply(ProductDTO dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		
		if(dto.getShopDTO() != null) {
			product.setShop(this.shopConverter.unapply(dto.getShopDTO()));			
		}
		
		product.setDollarValue(dto.getDollarValue());
		return product;
	}
}
