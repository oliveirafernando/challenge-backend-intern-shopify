package ca.shopify.backend.challenge.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.repository.ProductRepository;

@Service
public class ProductService extends AbstractService<Product> {

	private ShopService shopService;
	private ProductRepository productRepository;

	protected ProductService(ProductRepository repository, ShopService shopService) {
		super(repository);
		productRepository = repository;
		this.shopService = shopService;
	}

	@Override
	public Product validate(Product product) throws EntityValidationException {
		if (product == null) {
			throw new EntityValidationException("The Product entity cannot be null.");
		}
		if (product.getName() == null || product.getName().isEmpty()) {
			throw new EntityValidationException("The Product name is mandatory.");
		}

		if (product.getDollarValue() == null) {
			throw new EntityValidationException("The Product dollar value is mandatory.");
		}

		if (product.getDollarValue().compareTo(new BigDecimal(0.0)) < 0) {
			throw new EntityValidationException("The Product dollar value must be more than or equals to 0");
		}

		if (product.getShop() == null || product.getShop().getId() == null) {
			throw new EntityValidationException("The Product must have a previously created Shop");
		} else {
			try {
				product.setShop(this.shopService.findById(product.getShop().getId()));
			} catch (EntityValidationException e) {
				throw new EntityValidationException("The Shop of Product was not previously created.");
			}
		}
		return product;
	}

	public List<Product> getProductsByShopId(Long id) throws EntityValidationException {
		if (id == null) {
			throw new EntityValidationException("Id cannot be null.");
		}

		List<Product> products = this.productRepository.findAllProductsByShopId(id);
		if (products == null) {
			products = new LinkedList<Product>();
		}
		return products;
	}

	@Override
	protected Product setId(Product entity, Long id) {
		entity.setId(id);
		return entity;
	}

	@Override
	protected Long getId(Product entity) {
		return entity.getId();
	}

	@Override
	protected String getEntityName() {
		return Product.class.getSimpleName();
	}

}
