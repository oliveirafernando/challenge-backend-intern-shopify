package ca.shopify.backend.challenge.service;

import java.util.LinkedList;
import java.util.List;

public class PageableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> errors;

	public void addError(String error) {
		if (this.errors == null) {
			this.errors = new LinkedList<>();
		}

		this.errors.add(error);
	}

	@Override
	public String getMessage() {
		String fullMessage = this.errors.stream().reduce("", (p, q) -> p + "; " + q);
		fullMessage = fullMessage.substring(2, fullMessage.length());
		
		return fullMessage;
	}

	public Boolean isEmpty() {
		return this.errors == null || this.errors.size() == 0;
	}
}
