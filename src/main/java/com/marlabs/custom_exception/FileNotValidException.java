package com.marlabs.custom_exception;

public class FileNotValidException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FileNotValidException(String message) {
		super(message);
	}
	

}
