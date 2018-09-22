package ca.shopify.backend.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.shopify.backend.challenge.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
