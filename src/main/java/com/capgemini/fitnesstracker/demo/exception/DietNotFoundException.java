package com.capgemini.fitnesstracker.demo.exception;

public class DietNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1077081933318317917L;

	public DietNotFoundException() {
		super();
	}

	public DietNotFoundException(String message) {
		super(message);
	}

}
