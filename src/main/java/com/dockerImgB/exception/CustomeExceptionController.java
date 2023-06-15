package com.dockerImgB.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomeExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = EmployeeNotFoundBYIDException.class)
	public ResponseEntity<ExceptionDetails> employeeNotFoundException(EmployeeNotFoundBYIDException exception) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(exception.getMessage(), false);
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = AuthTokenInvalidException.class)
	public ResponseEntity<ExceptionDetails> exception(AuthTokenInvalidException exception) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(exception.getMessage(), false);
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.BAD_REQUEST);
	}

	/*
	 * @ExceptionHandler(value = Exception.class) public ResponseEntity<Object>
	 * exception(Exception exception, WebRequest request) { ExceptionDetails error =
	 * new ExceptionDetails(exception.getMessage(), request.getDescription(false));
	 * return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

}
