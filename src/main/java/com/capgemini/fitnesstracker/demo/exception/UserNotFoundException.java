package com.capgemini.fitnesstracker.demo.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1077081933318317917L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}

}
