package org.a_sply.porter.dto.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petri Kainulainen
 */
public class ValidationErrorDTO {

	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

	public ValidationErrorDTO() {

	}

	public void addFieldError(String path, String message) {
		FieldErrorDTO error = new FieldErrorDTO(path, message);
		fieldErrors.add(error);
	}

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}
}
