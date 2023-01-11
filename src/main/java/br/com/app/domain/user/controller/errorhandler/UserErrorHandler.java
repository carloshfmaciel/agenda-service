package br.com.app.domain.user.controller.errorhandler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.app.domain.user.exception.UserNotFoundException;
import br.com.app.infrastructure.exception.ErrorResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UserErrorHandler {

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage(),
				ex.getMessage());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse unexpectedException(IllegalArgumentException ex) {
		log.error(ex.getMessage(), ex);
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getMessage(),
				ex.getMessage());
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse unexpectedException(Exception ex) {
		log.error(ex.getMessage(), ex);
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), ex.getMessage(),
				ex.getMessage());
	}
}
