package com.capgemini.customer.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.entity.ErrorMessage;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerIdAlreadyExistException;
import com.capgemini.customer.exception.CustomerNotFoundException;

@ControllerAdvice
@RestController
public class ExceptionController  {

		@ExceptionHandler(value = CustomerNotFoundException.class)
		public @ResponseBody ResponseEntity<ErrorMessage> customerNotFoundException(
				CustomerNotFoundException customerNotFoundException, HttpServletRequest request) {
			ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(),
					customerNotFoundException.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(value = AuthenticationFailedException.class)
		public @ResponseBody ResponseEntity<ErrorMessage> authFailedException(
				AuthenticationFailedException customerNotFoundException, HttpServletRequest request) {
			ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(),
					customerNotFoundException.getMessage(), LocalDateTime.now(),  HttpStatus.FORBIDDEN);
			return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
		}

		@ExceptionHandler(value = CustomerIdAlreadyExistException.class)
		public @ResponseBody ResponseEntity<ErrorMessage> registrationFailedException(
				CustomerIdAlreadyExistException customerNotFoundException, HttpServletRequest request) {
			ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(),
					customerNotFoundException.getMessage(), LocalDateTime.now(),  HttpStatus.FOUND);
			System.out.println(errorMessage);
			return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FOUND);
		}

}

