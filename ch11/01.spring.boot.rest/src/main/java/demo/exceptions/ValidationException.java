package demo.exceptions;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Errors errors;

	public ValidationException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
