package com.varejo360.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 5764269740179322449L;

	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}
}
