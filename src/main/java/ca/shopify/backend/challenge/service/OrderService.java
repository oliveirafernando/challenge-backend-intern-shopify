package ca.shopify.backend.challenge.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.shopify.backend.challenge.model.LineItem;
import ca.shopify.backend.challenge.model.Order;
import ca.shopify.backend.challenge.model.StatusOrderEnum;
import ca.shopify.backend.challenge.repository.OrderRepository;

@Service
public class OrderService extends AbstractService<Order> {

	private LineItemService lineItemService;
	
	private OrderRepository orderRepository;

	protected OrderService(OrderRepository orderRepository, LineItemService lineItemService) {
		super(orderRepository);
		
		this.orderRepository = orderRepository;
		this.lineItemService = lineItemService;
	}

	@Transactional
	@Override
	public Order create(Order entity) throws EntityValidationException {
		if (entity != null && entity.getLineItems() != null) {
			entity.getLineItems().forEach(li -> li.setId(null));
		}

		Order orderPersisted = super.create(entity);
		orderPersisted.setStatus(StatusOrderEnum.REALIZED);
		for (LineItem li : orderPersisted.getLineItems()) {
			li.setOrder(orderPersisted);
			lineItemService.create(li);
		}
		return orderPersisted;

	}

	@Override
	public Order validate(Order order) throws EntityValidationException {
		if (order == null) {
			throw new EntityValidationException("The Order entity cannot be null.");
		}
		order.setDateTime(LocalDateTime.now());

		if (order.getLineItems() == null || order.getLineItems().isEmpty()) {
			throw new EntityValidationException("The Order must have one or more Line Item.");
		} else {
			for (LineItem lineItem : order.getLineItems()) {
				try {
					this.lineItemService.validate(lineItem);
				} catch (EntityValidationException e) {
					throw new EntityValidationException("The Order was not validated: " + e.getMessage());
				}
			}
		}

		if (order.getDollarValue() != null) {
			BigDecimal sumLineItems = order.getLineItems().stream().map(li -> li.getDollarValue())
					.reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
			if (!sumLineItems.equals(order.getDollarValue())) {
				throw new EntityValidationException(
						"The Order dollar value must be equals to the sum of all dollar values of Line Items.");
			}

		}

		return order;
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

	public Order cancelOrder(Long id) throws EntityValidationException {
		Order orderCanceled = this.findById(id);
		orderCanceled.setStatus(StatusOrderEnum.CANCELED);
		return this.update(orderCanceled);
	}
}
