package net.chrisgrollier.amsuite.osscat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Hidden;
import net.chrisgrollier.amsuite.osscat.model.ErrorView;
import net.chrisgrollier.amsuite.osscat.service.InvalidIdException;
import net.chrisgrollier.amsuite.osscat.service.ResourceNotFoundException;

@ControllerAdvice
@Hidden
public class ErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorView handleNotFound(final ResourceNotFoundException ex) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Resource not found exception.", ex);
		}
		return new ErrorView(ex);
	}

	@ExceptionHandler(InvalidIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorView handleInvalidId(final InvalidIdException ex) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Invalid id exception.", ex);
		}
		return new ErrorView(ex);
	}

}
