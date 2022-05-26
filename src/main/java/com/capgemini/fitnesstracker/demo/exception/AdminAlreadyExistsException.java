package com.capgemini.fitnesstracker.demo.exception;

public class AdminAlreadyExistsException extends RuntimeException {

private static final long serialVersionUID = 4862786405787654481L;
	
	public AdminAlreadyExistsException() {
		super();
	}

	public AdminAlreadyExistsException(String message) {
		super(message);
	}
}
