package ca.shopify.backend.challenge.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ca.shopify.backend.challenge.model.LineItem;
import ca.shopify.backend.challenge.model.Order;
import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.model.StatusOrderEnum;
import ca.shopify.backend.challenge.repository.LineItemRepository;
import ca.shopify.backend.challenge.repository.OrderRepository;
import ca.shopify.backend.challenge.repository.ProductRepository;
import ca.shopify.backend.challenge.repository.ShopRepository;

@Component
public class ChallengeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private LineItemRepository lineItemRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {
		// ===== SHOPS =====
		Shop shopShopify = new Shop();
		shopShopify.setName("Shopify Shop");
		this.shopRepository.save(shopShopify);

		Shop anotherShop = new Shop();
		anotherShop.setName("Another Shop");
		this.shopRepository.save(anotherShop);

		// ===== PRODUCTS =====
		Product productTShirt = new Product();
		productTShirt.setName("Shopify T-shirt");
		productTShirt.setShop(shopShopify);
		productTShirt.setDollarValue(new BigDecimal(20.20));
		this.productRepository.save(productTShirt);

		Product randomProduct = new Product();
		randomProduct.setName("Random Product");
		randomProduct.setShop(anotherShop);
		randomProduct.setDollarValue(new BigDecimal(50.25));
		this.productRepository.save(randomProduct);

		Product productSweater = new Product();
		productSweater.setName("Shopify Sweater");
		productSweater.setShop(shopShopify);
		productSweater.setDollarValue(new BigDecimal(100.49));
		this.productRepository.save(productSweater);

		Product productGeekAccessory = new Product();
		productGeekAccessory.setName("Shopify Geek Accessory");
		productGeekAccessory.setShop(shopShopify);
		productGeekAccessory.setDollarValue(new BigDecimal(0.25));
		this.productRepository.save(productGeekAccessory);

		// ===== ORDERS =====
		int qtdItem1 = 10;
		int qtdItem2 = 2;
		int qtdItem3 = 4;
		
		Order orderAllProductsShopify = new Order();
		orderAllProductsShopify.setStatus(StatusOrderEnum.REALIZED);
		orderAllProductsShopify.setDateTime(LocalDateTime.now());
		orderAllProductsShopify.setDollarValue(
				productTShirt.getDollarValue().multiply(new BigDecimal(qtdItem1))
				.add(productSweater.getDollarValue().multiply(new BigDecimal(qtdItem2)))
				.add(productGeekAccessory.getDollarValue().multiply(new BigDecimal(qtdItem3)))
			);
		this.orderRepository.save(orderAllProductsShopify);

		LineItem item1 = new LineItem();
		item1.setAmount(qtdItem1);
		item1.setProduct(productTShirt);
		item1.setOrder(orderAllProductsShopify);
		this.lineItemRepository.save(item1);

		LineItem item2 = new LineItem();
		item2.setAmount(qtdItem2);
		item2.setProduct(productSweater);
		item2.setOrder(orderAllProductsShopify);
		this.lineItemRepository.save(item2);

		LineItem item3 = new LineItem();
		item3.setAmount(qtdItem3);
		item3.setProduct(productGeekAccessory);
		item3.setOrder(orderAllProductsShopify);
		this.lineItemRepository.save(item3);

		this.lineItemRepository.saveAll(orderAllProductsShopify.getLineItems());
		this.orderRepository.save(orderAllProductsShopify);

		// ===== COUNT ALL =====
		System.out.println("[Shop] Count: " + this.shopRepository.count());
		System.out.println("[Product] Count: " + this.productRepository.count());
		System.out.println("[Order] Count: " + this.orderRepository.count());
		System.out.println("[LineItem] Count: " + this.lineItemRepository.count());
	}
}
