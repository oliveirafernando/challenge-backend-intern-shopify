package ca.shopify.backend.challenge.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.repository.ProductRepository;
import ca.shopify.backend.challenge.repository.ShopRepository;

@Component
public class ChallengeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private ProductRepository productRepository;

//	@Autowired
//	private OrderRepository orderRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {
		Shop shopShopify = new Shop();
		shopShopify.setName("Shopify Shop");
		this.shopRepository.save(shopShopify);

		Product productTShirt = new Product();
		productTShirt.setName("Shopify T-shirt");
		productTShirt.setShop(shopShopify);
		this.productRepository.save(productTShirt);

		Product productSweater = new Product();
		productSweater.setName("Shopify Sweater");
		productSweater.setShop(shopShopify);
		this.productRepository.save(productSweater);

		Product productGeekAccessories = new Product();
		productGeekAccessories.setName("Shopify Geek Accessories");
		productGeekAccessories.setShop(shopShopify);
		this.productRepository.save(productGeekAccessories);

		System.out.println("[Shop] Count: " + this.shopRepository.count());
		System.out.println("[Produc] Count: " + this.productRepository.count());

//		Order orderAllProductsShopify = new Order();
//		orderAllProductsShopify.getLineItems().add(new LineItem(365, productTShirt));
//		orderAllProductsShopify.getLineItems().add(new LineItem(365 / 4, productSweater));
//		orderAllProductsShopify.getLineItems().add(new LineItem(5, productGeekAccessories));

//		this.orderRepository.save(orderAllProductsShopify);
//		System.out.println("[Order] Count: " + this.orderRepository.count());
	}
}
