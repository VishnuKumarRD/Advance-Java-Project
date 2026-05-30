package com.lms.serviceImplementation;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.BookUnableToBorrowException;
import com.lms.exception.BookUnableToReturnException;
import com.lms.exception.UserIdNotExistException;
import com.lms.exception.UserIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IUserService;
import com.lms.util.ApiResponse;

@Service
public class UserService implements IUserService{
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	BookRepository bookRepository;
	
	ApiResponse<User> apiResponse=new ApiResponse<>();
	@Override
	public ResponseEntity<ApiResponse<User>> saveUser(UserDto userDto, int addressId) {
		// TODO Auto-generated method stub
		User user=modelMapper.map(userDto, User.class);
		Optional<Address> optional=addressRepository.findById(addressId);
		if(optional.isPresent()) {
			Address address=optional.get();
			user.setAddress(address);
			userRepository.save(user);
			apiResponse.setMessage("User Saved Successfully!");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.CREATED);
		}
		else {
//			apiResponse.setMessage("For this Address Id is not Found!");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.NOT_FOUND);
			
			throw new AddressIdNotFoundException("Invalid Address Id");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> findByUserId(int userId) {
		// TODO Auto-generated method stub
		Optional<User> optional=userRepository.findById(userId);
		if(optional.isPresent()) {
			User user=optional.get();
			apiResponse.setMessage("User "+user.getUserId()+" Id Found Successfully!");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.FOUND);
		}
		else {
//			apiResponse.setMessage("User Id is not Found!");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.NOT_FOUND);
			throw new UserIdNotFoundException("User Id is not Found!");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> updateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=modelMapper.map(userDto, User.class);
		if(userRepository.existsById(user.getUserId())) {
			userRepository.save(user);
			apiResponse.setMessage("User Updated Successfully!");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.OK);
		}
		else {
//			apiResponse.setMessage("User is Not Exists!");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.BAD_REQUEST);
			throw new UserIdNotExistException("User is not Exists!");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> deleteUser(int userId) {
		// TODO Auto-generated method stub
		if(userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			apiResponse.setMessage("User Deleted Successfully!");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.OK);
		}
		else {
//			apiResponse.setMessage("User Not Exists");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.BAD_REQUEST);
			throw new UserIdNotExistException("User is not Exists!");
		}
		
	}

	@Override
	public List<User> fetchAllId() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId, int bookId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(userId);
		Optional<Book> optionalBook=bookRepository.findById(bookId);
		//1.book is present 2. user is present 3. borrowed should be false
		if((optionalUser.isPresent() && optionalBook.isPresent() && (!optionalBook.get().isBorrowed()))){
			User user=optionalUser.get();
			Book book=optionalBook.get();
			
			//set book to user
			book.setUser(user);
			book.setBorrowedTime(LocalDateTime.now());
			book.setBorrowed(true);
			
			//update book
			bookRepository.save(book);
			apiResponse.setMessage("User Borrowed book Successfully!");
			apiResponse.setData(user);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.OK);
			
		}
		else {
			throw new BookUnableToBorrowException("Invalid Book id/User id or book is already borrowed");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> returnBookByUser(int bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook=bookRepository.findById(bookId);
		
		if((optionalBook.isPresent()) && (optionalBook.get().isBorrowed())) {
			Book book=optionalBook.get();
			book.setUser(null);
			book.setBorrowed(false);
			book.setReturnTime(LocalDateTime.now());
			
			bookRepository.save(book);
			apiResponse.setMessage("User Returned book Successfully!");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<User>>(apiResponse,HttpStatus.OK);
			
		}
		else {
			throw new BookUnableToReturnException("Invalid Book id/User id or book is not borrowed");
		}
	}

}
