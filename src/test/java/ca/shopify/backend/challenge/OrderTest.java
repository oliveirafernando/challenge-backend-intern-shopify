package ca.shopify.backend.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.shopify.backend.challenge.model.LineItem;
import ca.shopify.backend.challenge.model.Order;
import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.OrderService;
import ca.shopify.backend.challenge.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

	private static final Long ID_PRODUCT_PREV_CREATED = new Long(1);

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Test
	public void validateCreate() {
		try {
			Long countBefore = this.orderService.countAll();

			Product product = this.productService.findById(ID_PRODUCT_PREV_CREATED);

			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			lineItem.setAmount(2);

			Order order = new Order();
			order.setDollarValue(product.getDollarValue().multiply(new BigDecimal(lineItem.getAmount())));
			order.setLineItems(Arrays.asList(lineItem));
			
			this.orderService.create(order);

			assertEquals(new Long(countBefore + 1), this.orderService.countAll());
		} catch (EntityValidationException e) {
			fail("Valid Order was not created");
		}
	}
}
