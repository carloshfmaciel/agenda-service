package br.com.app.domain.vote.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteValidationException extends RuntimeException {

	private static final long serialVersionUID = 6561551579886936533L;
	final String errorCode;
	
	public VoteValidationException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
