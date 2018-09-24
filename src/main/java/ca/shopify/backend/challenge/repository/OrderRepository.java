package ca.shopify.backend.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ca.shopify.backend.challenge.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

//	@Query()
//	List<Order> findAllOrdersByShopId(Long shopId);

}
