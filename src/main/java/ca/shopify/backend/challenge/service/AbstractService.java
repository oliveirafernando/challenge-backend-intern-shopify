package ca.shopify.backend.challenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T> implements GenericService<T> {

	protected JpaRepository<T, Long> repository;

	protected AbstractService(JpaRepository<T, Long> repository) {
		this.repository = repository;
	}

	@Override
	public List<T> getAllPaginated(int pageNumber, int count) throws PageableException {
		this.validatePageParameterRequest(pageNumber, count);
		return repository.findAll(PageRequest.of(pageNumber, count)).stream().collect(Collectors.toList());
	}

	@Override
	public T findById(Long id) throws EntityValidationException {
		if (id == null) {
			throw new EntityValidationException("Id cannot be null.");
		}

		Optional<T> entityFound = this.repository.findById(id);
		if (!entityFound.isPresent()) {
			throw new EntityValidationException(getEntityName() + " not found.");
		}
		return entityFound.get();
	}

	@Override
	public Long countAll() {
		return this.repository.count();
	}

	@Override
	public T create(T entity) throws EntityValidationException {
		entity = this.validate(entity);
		entity = setId(entity, null);
		return this.repository.save(entity);
	}

	public T update(T entity) throws EntityValidationException {
		entity = this.validate(entity);
		this.findById(this.getId(entity));
		return this.repository.save(entity);
	}

	public void delete(Long id) throws EntityValidationException {
		T entity = this.findById(id);

		try {
			this.repository.deleteById(getId(entity));
		} catch (DataIntegrityViolationException e) {
			StringBuilder builder = new StringBuilder();
			builder.append(getEntityName()).append(" could not be deleted. First, delete linked data to the ").append(getEntityName());
			throw new EntityValidationException(builder.toString());
		}
	}
	
	protected abstract T validate(T entity) throws EntityValidationException;
	protected abstract T setId(T entity, Long value);
	protected abstract Long getId(T entity);
	protected abstract String getEntityName();
}
