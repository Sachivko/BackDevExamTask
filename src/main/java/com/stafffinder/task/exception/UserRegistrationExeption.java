package com.stafffinder.task.exception;

@SuppressWarnings("serial")
public class UserRegistrationExeption extends Exception {

	public UserRegistrationExeption() {
		super();
	}

	public UserRegistrationExeption(String message) {
		super(message);
	}

	public UserRegistrationExeption(Throwable cause) {
		super(cause);
	}

	public UserRegistrationExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public UserRegistrationExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}