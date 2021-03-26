package com.stackroute.paymentservice.exceptions;



public class MethodNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public MethodNotFoundException(String message) {
		super(message);
	}

}
