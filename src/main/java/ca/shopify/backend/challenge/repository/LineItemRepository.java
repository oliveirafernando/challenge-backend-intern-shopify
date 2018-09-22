package ca.shopify.backend.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.shopify.backend.challenge.model.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

}
