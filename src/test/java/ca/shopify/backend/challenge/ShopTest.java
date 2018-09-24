package ca.shopify.backend.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.ShopService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopTest {

	private static final String FAKE_NAME_VALUE = "New Shop";

	private static final String NAME_IS_MANDATORY = "The Shop name is mandatory.";

	@Autowired
	private ShopService shopService;

	@Test
	public void validateCreate() {
		try {
			Long countBefore = this.shopService.countAll();

			Shop shop = new Shop();
			shop.setName(FAKE_NAME_VALUE);
			Shop createdShop = this.shopService.create(shop);
			Shop shopFound = this.shopService.findById(createdShop.getId());

			assertEquals(FAKE_NAME_VALUE, shopFound.getName());
			assertEquals(new Long(countBefore + 1), this.shopService.countAll());
		} catch (EntityValidationException e) {
			fail("Valid Shop was not created");
		}
	}

	@Test(expected = EntityValidationException.class)
	public void validateMandatoryRequirements() throws EntityValidationException {
		Shop shop = new Shop();
		try {
			this.shopService.create(shop);
		} catch (EntityValidationException e) {
			assertEquals(NAME_IS_MANDATORY, e.getMessage());
			throw e;
		}
	}
}
