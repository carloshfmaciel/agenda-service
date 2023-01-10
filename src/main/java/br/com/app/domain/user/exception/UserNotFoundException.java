package br.com.app.domain.user.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3404113689547575813L;
	
	final String errorCode;
	
	public UserNotFoundException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
