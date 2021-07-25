package com.ecommerce.webapp.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class LoginIDNotFound extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public LoginIDNotFound(String message) {
		super(message);
	}
}