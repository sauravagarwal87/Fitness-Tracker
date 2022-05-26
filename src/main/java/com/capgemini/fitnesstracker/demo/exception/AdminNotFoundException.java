package com.capgemini.fitnesstracker.demo.exception;

public class AdminNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1077081933318317917L;

	public AdminNotFoundException() {
		super();
	}

	public AdminNotFoundException(String message) {
		super(message);
	}

}
