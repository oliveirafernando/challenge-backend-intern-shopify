package ca.shopify.backend.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.shopify.backend.challenge.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
