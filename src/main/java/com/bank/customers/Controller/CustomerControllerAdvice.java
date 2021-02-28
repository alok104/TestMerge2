package com.bank.customers.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.customers.constants.Constant;
import com.bank.customers.errors.ApiError;
import com.bank.customers.exception.CustomersException;

@ControllerAdvice
public class CustomerControllerAdvice {
	@ExceptionHandler({ CustomersException.class })
	public ResponseEntity<?> handleApplicationException(CustomersException ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(Constant.ERROR_MESSAGE);
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
