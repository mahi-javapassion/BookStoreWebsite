package com.bookstore.controller.admin.exception;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserExistsException() {
	}

	public UserExistsException(String message) {
		super(message);
	}

	public UserExistsException(String message, Throwable t) {
		super(message, t);
	}
}
