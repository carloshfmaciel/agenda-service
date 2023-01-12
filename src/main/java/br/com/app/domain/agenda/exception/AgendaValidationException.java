package br.com.app.domain.agenda.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgendaValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 8617674584900977266L;
	
	final String errorCode;
	
	public AgendaValidationException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
