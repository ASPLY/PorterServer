package org.a_sply.porter.controller;

import java.util.List;

import org.a_sply.porter.dto.validation.ValidationErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Petri Kainulainen
 */
public class BaseController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BaseController.class);

	private MessageSource messageSource;

	public BaseController() {
	}

	@Autowired
	public BaseController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(org.springframework.validation.BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO processValidationError(BindException ex) {
		LOGGER.debug("Handling form validation error");

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

//	@ExceptionHandler(UnauthorizedException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public ValidationErrorDTO processValidationError(UnauthorizedException ex) {
//		LOGGER.debug("Handling form validation error");
//
//		ValidationErrorDTO dto = new ValidationErrorDTO();
//		dto.addFieldError("apiKey", ex.getLocalizedMessage());
//
//		return dto;
//	}

	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = fieldError.getDefaultMessage();
			LOGGER.debug("Adding error message: {} to field: {}",
					localizedErrorMessage, fieldError.getField());
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}

	// private ValidationErrorDTO processFieldErrors(List<FieldError>
	// fieldErrors) {
	// ValidationErrorDTO dto = new ValidationErrorDTO();
	//
	// for (FieldError fieldError: fieldErrors) {
	// String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
	// LOGGER.debug("Adding error message: {} to field: {}",
	// localizedErrorMessage, fieldError.getField());
	// dto.addFieldError(fieldError.getField(), localizedErrorMessage);
	// }
	//
	// return dto;
	// }
	//
	// private String resolveLocalizedErrorMessage(FieldError fieldError) {
	// Locale currentLocale = LocaleContextHolder.getLocale();
	// String localizedErrorMessage = messageSource.getMessage(fieldError,
	// currentLocale);
	//
	// //If a message was not found, return the most accurate field error code
	// instead.
	// //You can remove this check if you prefer to get the default error
	// message.
	// if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
	// String[] fieldErrorCodes = fieldError.getCodes();
	// localizedErrorMessage = fieldErrorCodes[0];
	// }
	//
	// return localizedErrorMessage;
	// }
}
