package com.capgemini.fitnesstracker.demo.exception;

public class ReportNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1077081933318317917L;

	public ReportNotFoundException() {
		super();
	}

	public ReportNotFoundException(String message) {
		super(message);
	}

}
