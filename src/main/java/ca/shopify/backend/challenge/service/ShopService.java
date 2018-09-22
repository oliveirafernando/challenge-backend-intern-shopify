package ca.shopify.backend.challenge.service;

import org.springframework.stereotype.Service;

import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.repository.ShopRepository;

@Service
public class ShopService extends AbstractService<Shop> {

	protected ShopService(ShopRepository repository) {
		super(repository);
	}

	@Override
	protected Shop validate(Shop shop) throws EntityValidationException {
		if (shop == null) {
			throw new EntityValidationException("Shop entity cannot be null.");
		}
		if (shop.getName() == null || shop.getName().isEmpty()) {
			throw new EntityValidationException("Name field cannot be null.");
		}

		return shop;
	}

	@Override
	protected Shop setId(Shop entity, Long id) {
		entity.setId(id);
		return entity;
	}

	@Override
	protected Long getId(Shop entity) {
		return entity.getId();
	}

	@Override
	protected String getEntityName() {
		return Shop.class.getSimpleName();
	}
}
