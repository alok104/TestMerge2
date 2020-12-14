package com.analyzer.ms.co2analyzer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.analyzer.ms.co2analyzer.errors.ApiError;
import com.analyzer.ms.co2analyzer.exception.Co2AnalyzerException;

@ControllerAdvice
public class Co2AnalyzerControllerAdvice {
	@ExceptionHandler({ Co2AnalyzerException.class })
	public ResponseEntity<?> handleApplicationException(Co2AnalyzerException ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
}
