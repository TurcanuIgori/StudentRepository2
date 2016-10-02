package ru.javabegin.training.controller;

public class NullDataException extends RuntimeException{
	
	private String message;
	
	public NullDataException(String message) {
		super();
		this.message = message;
	}

	public NullDataException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
