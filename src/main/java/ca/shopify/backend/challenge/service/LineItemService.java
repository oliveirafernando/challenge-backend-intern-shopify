package ca.shopify.backend.challenge.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ca.shopify.backend.challenge.model.LineItem;
import ca.shopify.backend.challenge.model.Product;

@Service
public class LineItemService extends AbstractService<LineItem> {

	private static final Integer QTD_MIN_AMOUNT = 1;

	@Autowired
	private ProductService productService;

	protected LineItemService(JpaRepository<LineItem, Long> repository) {
		super(repository);
	}

	@Override
	public LineItem validate(LineItem lineItem) throws EntityValidationException {
		if (lineItem == null) {
			throw new EntityValidationException("The Line Item entity cannot be null.");
		}

		if (lineItem.getAmount() == null) {
			throw new EntityValidationException("The Line Item amount cannot be null.");
		}

		if (lineItem.getAmount() < QTD_MIN_AMOUNT) {
			throw new EntityValidationException("The Line Item amount must be more than or equals to " + QTD_MIN_AMOUNT + ".");
		}
		
		if(lineItem.getDollarValue() == null) {
			throw new EntityValidationException("The Line Item must have a dollar value.");
		}

		if (lineItem.getProduct() == null || lineItem.getProduct().getId() == null) {
			throw new EntityValidationException("The Line Item must have a previously created Product.");
		} else {
			Product product = this.productService.findById(lineItem.getProduct().getId());
			
			if (product == null) {
				throw new EntityValidationException("The Product of Line Item was not previously created.");
			} 
			
			if(!product.getDollarValue().equals(lineItem.getDollarValue().divide(new BigDecimal(lineItem.getAmount())))){
				throw new EntityValidationException("The Line Item dollar value must be equals to the result of (amount * product_dollar_value).");
			}
		}

		return lineItem;
	}

	@Override
	protected LineItem setId(LineItem entity, Long value) {
		entity.setId(value);
		return entity;
	}

	@Override
	protected Long getId(LineItem entity) {
		return entity.getId();
	}

	@Override
	protected String getEntityName() {
		return LineItem.class.getSimpleName();
	}

}
