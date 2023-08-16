package com.ibm.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibm.app.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
		
	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
		ErrorResponse err = new ErrorResponse("DataNotFoundException", ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorResponse>(err, err.getHttpStatus());

	}
	
	@ExceptionHandler(value = CannotPersistInDatabaseException.class)
	public ResponseEntity<ErrorResponse> handleCannotPersistInDatabaseException(CannotPersistInDatabaseException ex) {
		ErrorResponse err = new ErrorResponse("CannotPersistInDatabaseException", ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorResponse>(err, err.getHttpStatus());

	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse err = new ErrorResponse("HttpMediaTypeNotSupportedException", 
				ex.getLocalizedMessage(), status);
		ex.printStackTrace();
		return new ResponseEntity<Object>((ErrorResponse)err, err.getHttpStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse err = new ErrorResponse("HttpMediaTypeNotAcceptableException", 
				ex.getLocalizedMessage(), status);
		ex.printStackTrace();
		return new ResponseEntity<Object>((ErrorResponse)err, err.getHttpStatus());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponse err = new ErrorResponse("NoHandlerFoundException", 
				ex.getLocalizedMessage(), status);
		ex.printStackTrace();
		return new ResponseEntity<Object>((ErrorResponse)err, err.getHttpStatus());
	}
	
	
	
}
