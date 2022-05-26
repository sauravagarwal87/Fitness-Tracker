package com.capgemini.fitnesstracker.demo.exception;



public class InvalidDateDifferenceException extends RuntimeException {
	
	private static final long serialVersionUID = -5284904993921797337L;

	public InvalidDateDifferenceException() {
		super();
	}

	public InvalidDateDifferenceException(String message) {
		super(message);
	}

}