package br.com.app.domain.vote.integration.cpf.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CpfIntegrationException extends RuntimeException {
	
	private static final long serialVersionUID = 2254179724484918443L;
	
	final String errorCode;
	
	public CpfIntegrationException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
