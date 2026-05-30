package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.service.IUserService;
import com.lms.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	
	@Operation(operationId="CreateUser",summary="Adding User",
			description="This rest endpoint is used to create and add one User")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="201",
			description="creates and returns User entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Invalid Address Id")})
	@PostMapping("/{addressId}")
	public ResponseEntity<ApiResponse<User>> saveUser(@RequestBody UserDto userDto,@PathVariable int addressId) {
		return userService.saveUser(userDto, addressId);
	}
	
	
	@Operation(operationId="fetchUser",summary="Fetching One User",
			description="This rest endpoint is used to fetch one User by using User Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="302",
			description="fetching User using User Id and returns User entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="User is not found for this Id")})
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponse<User>> findByUserId(@PathVariable int userId) {
		return userService.findByUserId(userId);
	}
	
	
	@Operation(operationId="UpdateUser",summary="Update User",
			description="This rest endpoint is used to update User")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Updated and returns User entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="400",
			description="User not exist")})
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}
	
	
	@Operation(operationId="DeleteUser",summary="Delete One User",
			description="This rest endpoint is used to delete one User by using User Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="delete User using User Id and returns User entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="User is not found for this Id")})
	@DeleteMapping
	public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
	
	
	@Operation(operationId="FetchAllUser",summary="FetchAll User",
			description="This rest endpoint is used to FetchAll Users")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Fetched All Users Successfully")})
	@GetMapping("/fetchAll")
	public List<User> fetchAllId() {
		return userService.fetchAllId();
	}
	
	
	
	@Operation(operationId="borrowBook",summary="Borrow Book by User",
			description="This rest endpoint is used to Borrow the book by the User by using userId and Book Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Borrowed book by the User and return User Entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="user/book is not found for this Id")})
	@PutMapping("/borrow/{userId}/{bookId}")
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(@PathVariable int userId,@PathVariable int bookId) {
		return userService.borrowBookByUser(userId, bookId);
	}
	
	
	@Operation(operationId="returnBook",summary="return Book by User",
			description="This rest endpoint is used to return the book by the User by using Book Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="return book by the User and return User Entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="book is not found for this Id")})
	@PutMapping("/return/{bookId}")
	public ResponseEntity<ApiResponse<User>> returnBookByUser(@PathVariable int bookId) {
		return userService.returnBookByUser(bookId);
	}
	
}
