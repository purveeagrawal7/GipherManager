package com.stackroute.giphermanager.util.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "GIF already Bookmarked")
public class GifAlreadyBookmarkedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GifAlreadyBookmarkedException(String message) {
	        super(message);
	}
	}


