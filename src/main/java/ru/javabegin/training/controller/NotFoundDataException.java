package ru.javabegin.training.controller;

public class NotFoundDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;

	public NotFoundDataException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundDataException(String message) {
		super(message);
		this.message = message;
   	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	

}
