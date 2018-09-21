package ca.shopify.backend.challenge.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import ca.shopify.backend.challenge.model.Shop;

public interface ShopService {

	Page<Shop> getAllShops(int pageNumber, int count) throws PageableException;

	Optional<Shop> findById(Long id);
}
