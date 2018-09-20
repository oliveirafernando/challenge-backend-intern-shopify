package ca.shopify.backend.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.shopify.backend.challenge.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
