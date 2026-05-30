package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.lms.util.ApiResponse;

@ControllerAdvice//helps to recognize this class as exception handling class
public class LibraryManagementExceptionHandler {
	
	//Address
	@ExceptionHandler(AddressIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleAddressIdNotFoundException(AddressIdNotFoundException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("Address for this Id is not Found!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(AddressIdNotExistException.class)
	public ResponseEntity<ApiResponse<String>> handleAddressIdNotExistException(AddressIdNotExistException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("Invalid Address!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	//Book
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleBookIdNotFoundException(BookIdNotFoundException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("Book for this Id is not Found!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BookIdNotExistException.class)
	public ResponseEntity<ApiResponse<String>> handleBookIdNotExistException(BookIdNotExistException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("Book Unavailable!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	//library
	@ExceptionHandler(LibraryIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleLibraryIdNotFoundException(LibraryIdNotFoundException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("Library for this Id is not Found!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(LibraryIdNotExistException.class)
	public ResponseEntity<ApiResponse<String>> handleLibraryIdNotExistException(LibraryIdNotExistException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("Library is not Exist!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	//User 
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleUserIdNotFoundException(UserIdNotFoundException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("User for this Id is not Found!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserIdNotExistException.class)
	public ResponseEntity<ApiResponse<String>> handleUserIdNotExistException(UserIdNotExistException exception){
		ApiResponse<String> apiResponse=new ApiResponse<>();
		apiResponse.setMessage("User not Exist!");
		apiResponse.setData(exception.getMessage());
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);
	}
}
