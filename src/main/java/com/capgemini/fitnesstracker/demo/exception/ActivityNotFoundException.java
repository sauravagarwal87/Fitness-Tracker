package com.capgemini.fitnesstracker.demo.exception;

public class ActivityNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 2571774748108454277L;

	public ActivityNotFoundException() {
		super();
	}

	public ActivityNotFoundException(String message) {
		super(message);
	}

}
