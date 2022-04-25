package com.stackroute.giphermanager.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User not found")
public class UserDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	public UserDoesNotExistException(String msg)
	{
		super(msg);
	}
}
