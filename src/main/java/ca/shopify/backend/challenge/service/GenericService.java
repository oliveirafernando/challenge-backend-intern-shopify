package ca.shopify.backend.challenge.service;

import java.util.List;

public interface GenericService<T> {

	Long countAll();

	List<T> getAllPaginated(int pageNumber, int count) throws PageableException;

	T findById(Long id) throws EntityValidationException;

	T create(T entity) throws EntityValidationException;
	
	void delete(Long id) throws EntityValidationException; 

	default void validatePageParameterRequest(int pageNumber, int count) throws PageableException {
		int MIN_PAGE_NUMBER = 0;
		int MIN_COUNT = 1;

		PageableException pageableException = new PageableException();

		if (pageNumber < MIN_PAGE_NUMBER) {
			pageableException.addError("Page number must be more than or equals to 0");
		}
		if (count < MIN_COUNT) {
			pageableException.addError("Count must be more than 0");
		}

		if (!pageableException.isEmpty()) {
			throw pageableException;
		}
	}

}
