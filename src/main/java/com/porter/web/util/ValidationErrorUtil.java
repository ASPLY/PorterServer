package com.porter.web.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.porter.web.dto.ValidationErrorDTO;

public class ValidationErrorUtil {
	public static ResponseEntity<ValidationErrorDTO> extractValidationError(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
		for (FieldError fieldError : fieldErrors) {
			validationErrorDTO.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<ValidationErrorDTO>(validationErrorDTO, HttpStatus.BAD_REQUEST);
	}
}
