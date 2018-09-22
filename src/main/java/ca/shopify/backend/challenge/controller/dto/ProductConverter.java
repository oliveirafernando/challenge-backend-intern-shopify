package ca.shopify.backend.challenge.controller.dto;

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
			dto.setShop(this.shopConverter.apply(entity.getShop()));			
		}
		
		dto.setDollarValue(entity.getDollarValue());
		return dto;
	}

	@Override
	public Product unapply(ProductDTO dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		
		if(dto.getShop() != null) {
			product.setShop(this.shopConverter.unapply(dto.getShop()));			
		}
		
		product.setDollarValue(dto.getDollarValue());
		return product;
	}
}
