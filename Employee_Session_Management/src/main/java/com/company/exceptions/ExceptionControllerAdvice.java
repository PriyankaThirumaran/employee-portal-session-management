package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.domain.ErrorMessage;

@RestControllerAdvice                                //global exception handling
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(NoSuchEmployeeException.class)
	public ResponseEntity<ErrorMessage> exceptionHAndlerEmployee(NoSuchEmployeeException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmpUsernameAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHAndlerEmpUsername(EmpUsernameAlreadyExistsException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmpEmailAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHAndlerEmpEmail(EmpEmailAlreadyExistsException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmpPhoneNoAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> exceptionHAndlerEmpPhoneNo(EmpPhoneNoAlreadyExistsException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ErrorMessage> InvalidPasswordException(InvalidPasswordException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(PleaseLoginException.class)
	public ResponseEntity<ErrorMessage> PleaseLoginException(PleaseLoginException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(SessionTimedOutException.class)
	public ResponseEntity<ErrorMessage> SessionTimedOutException(SessionTimedOutException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> AccessDeniedException(AccessDeniedException exception){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

}
