package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.util.ApiResponse;

public interface IUserService {
	public ResponseEntity<ApiResponse<User>> saveUser(UserDto userDto,int addressId);
	
	public ResponseEntity<ApiResponse<User>> findByUserId(int userId);
	
	public ResponseEntity<ApiResponse<User>> updateUser(UserDto userDto);
	
	public ResponseEntity<ApiResponse<User>> deleteUser(int userId);
	
	public List<User> fetchAllId();
	
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId,int bookId);
	
	public ResponseEntity<ApiResponse<User>> returnBookByUser(int bookId);
}
