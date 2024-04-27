package com.lcwd.user.service.exception;

public class ResourceNotFondException extends RuntimeException {

	public ResourceNotFondException() {
		super("Resource Not found Exception");
	}

	public ResourceNotFondException(String msg) {
		super(msg);
	}

}
