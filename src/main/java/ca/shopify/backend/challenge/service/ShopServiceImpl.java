package ca.shopify.backend.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService {

	private static final int MIN_PAGE_NUMBER = 0;
	private static final int MIN_COUNT = 1;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public Page<Shop> getAllShops(int pageNumber, int count) throws PageableException {
		PageableException pageableException = new PageableException();

		if (pageNumber < MIN_PAGE_NUMBER) {
			pageableException.addError("Page number must be more than or equals to 0");
		} if (count < MIN_COUNT) {
			pageableException.addError("Count must be more than 0");
		}

		if (!pageableException.isEmpty()) {
			throw pageableException;
		}

		return shopRepository.findAll(PageRequest.of(pageNumber, count));
	}

	@Override
	public Optional<Shop> findById(Long id) {
		return this.shopRepository.findById(id);
	}

}
