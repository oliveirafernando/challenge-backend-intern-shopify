package ca.shopify.backend.challenge.controller.dto;

public interface DTOConverter<T, K> {

	K apply(T entity);

	T unapply(K dto);
}
