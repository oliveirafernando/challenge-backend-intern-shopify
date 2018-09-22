package ca.shopify.backend.challenge.service;

import org.springframework.stereotype.Service;

import ca.shopify.backend.challenge.model.Order;
import ca.shopify.backend.challenge.repository.OrderRepository;

@Service
public class OrderService extends AbstractService<Order> {

	protected OrderService(OrderRepository repository) {
		super(repository);
	}

	@Override
	protected Order validate(Order entity) throws EntityValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Order setId(Order entity, Long id) {
		entity.setId(id);
		return entity;
	}

	@Override
	protected Long getId(Order entity) {
		return entity.getId();
	}

	@Override
	protected String getEntityName() {
		return Order.class.getSimpleName();
	}

}
