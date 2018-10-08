package com.capgemini.customer.exception;

public class CustomerIdAlreadyExistException extends RuntimeException{
	
		public CustomerIdAlreadyExistException(String message) {
			super(message);
		}

}
