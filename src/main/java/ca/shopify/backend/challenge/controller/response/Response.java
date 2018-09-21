package ca.shopify.backend.challenge.controller.response;

import java.util.LinkedList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<String> errors;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		if (errors == null) {
			this.errors = new LinkedList<String>();
		}
		this.errors.add(error);
	}
}
