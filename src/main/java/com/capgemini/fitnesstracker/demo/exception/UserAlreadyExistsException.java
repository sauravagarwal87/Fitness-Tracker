package com.capgemini.fitnesstracker.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4862786405787654481L;
	
	public UserAlreadyExistsException() {
		super();
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
