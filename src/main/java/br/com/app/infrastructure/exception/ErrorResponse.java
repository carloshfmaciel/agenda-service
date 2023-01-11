package br.com.app.infrastructure.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
	  private int statusCode;
	  private LocalDateTime timestamp;
	  private String message;
	  private String errorCode;

}
