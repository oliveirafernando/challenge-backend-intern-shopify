package ca.shopify.backend.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.ProductService;
import ca.shopify.backend.challenge.service.ShopService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

	private static final Long ID_SHOP_PREV_CREATED = new Long(1);
	
	private static final String DOLLAR_VALUE_IS_MANDATORY = "The Product dollar value is mandatory.";
	private static final String NAME_IS_MANDATORY = "The Product name is mandatory.";
	private static final Object SHOP_VALUE_IS_MANDATORY = "The Product must have a previously created Shop";
	
	private static final BigDecimal FAKE_DOLLAR_VALUE = new BigDecimal("10.00");
	private static final String FAKE_PRODUCT_NAME = "Product 1";

	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductService productService;

	@Test
	public void validateCreate() {
		try {
			Long countBefore = this.productService.countAll();
		
			Product product = new Product();
			product.setName(FAKE_PRODUCT_NAME);
			product.setDollarValue(FAKE_DOLLAR_VALUE);
			product.setShop(shopService.findById(ID_SHOP_PREV_CREATED));

			Product persistedProduct = this.productService.create(product);
			Product productFound = this.productService.findById(persistedProduct.getId());

			assertEquals(FAKE_PRODUCT_NAME, productFound.getName());
			assertEquals(FAKE_DOLLAR_VALUE, productFound.getDollarValue());
			assertEquals(new Long(countBefore + 1), this.productService.countAll());
		} catch (EntityValidationException e) {
			fail("Valid Product was not created");
		}
	}

	@Test(expected = EntityValidationException.class)
	public void validateNameMandatory() throws EntityValidationException {
		Product product = new Product();
		try {
			this.productService.create(product);
		} catch (EntityValidationException e) {
			assertEquals(NAME_IS_MANDATORY, e.getMessage());
			throw e;
		}
	}

	@Test(expected = EntityValidationException.class)
	public void validateDollarValueMandatory() throws EntityValidationException {
		Product product = new Product();
		product.setName(FAKE_PRODUCT_NAME);
		try {
			this.productService.create(product);
		} catch (EntityValidationException e) {
			assertEquals(DOLLAR_VALUE_IS_MANDATORY, e.getMessage());
			throw e;
		}
	}

	@Test(expected = EntityValidationException.class)
	public void validateShopMandatory() throws EntityValidationException {
		Product product = new Product();
		product.setName(FAKE_PRODUCT_NAME);
		product.setDollarValue(FAKE_DOLLAR_VALUE);
		try {
			this.productService.create(product);
		} catch (EntityValidationException e) {
			assertEquals(SHOP_VALUE_IS_MANDATORY, e.getMessage());
			throw e;
		}
	}
}
