package com.mindex.challenge.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;



@RestControllerAdvice
public class BaseExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<?> handleNotFoundException(ResourceNotFoundException nfe, HttpServletRequest httpReq, HttpServletResponse httpRes) {
    	LOG.warn(nfe.getMessage());
        httpRes.setStatus(HttpStatus.NOT_FOUND.value()); // 404 (order or item not found)
        return new ResponseEntity(nfe.getMessage(),HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<?> handleNotFoundException(ResponseStatusException nfe, HttpServletRequest httpReq, HttpServletResponse httpRes) {
    	LOG.warn(nfe.getMessage());
        httpRes.setStatus(nfe.getStatus().value()); // 404 (order or item not found)
        return new ResponseEntity(nfe.getMessage(),nfe.getStatus());
    }
    
	@ExceptionHandler({Exception.class, RuntimeException.class})
    private ResponseEntity<?> handleException(Throwable e, HttpServletRequest httpReq, HttpServletResponse httpRes) {
    	LOG.error(e.getMessage());
        httpRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
        return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
