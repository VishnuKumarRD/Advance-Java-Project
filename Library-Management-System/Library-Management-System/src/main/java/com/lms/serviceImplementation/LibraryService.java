package com.lms.serviceImplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.BookUnableToAddToLibraryException;
import com.lms.exception.LibraryIdNotExistException;
import com.lms.exception.LibraryIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.ILibraryService;
import com.lms.util.ApiResponse;

@Service
public class LibraryService implements ILibraryService{
	@Autowired
	LibraryRepository libraryRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	BookRepository bookRepository;
	
	ApiResponse<Library> apiResponse=new ApiResponse<>();
	
	
	@Override
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto, int addressId) {
		// convert libraryDto to library entity
		Library library=modelMapper.map(libraryDto, Library.class);
		Optional<Address> optionalAddress=addressRepository.findById(addressId);
		if(optionalAddress.isPresent()) {
			//set address to library
			Address address=optionalAddress.get();
			library.setAddress(address);
			//save library
			libraryRepository.save(library);
			apiResponse.setMessage("Library Saved Successfully!");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.CREATED.value());// 200 ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.CREATED);
			
		}
		else {
//			apiResponse.setMessage("Address for this Id not Found,Library Not saved");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.NOT_FOUND);
			
			throw new AddressIdNotFoundException("Invalid Address Id");
		}
		
	}

	
	@Override
	public ResponseEntity<ApiResponse<Library>> findLibraryById(int libraryId) {
		// TODO Auto-generated method stub
		Optional<Library> optional=libraryRepository.findById(libraryId);
		if(optional.isPresent()) {
			Library library=optional.get();
			apiResponse.setMessage("Library "+library.getLibraryId()+" Found Successfully!");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.FOUND.value());// 200 ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.FOUND);
		}
		else {
//			apiResponse.setMessage("Library Id is not Found!");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.NOT_FOUND);
			throw new LibraryIdNotFoundException("Library Id is not Found!");
		}
	}

	
	@Override
	public ResponseEntity<ApiResponse<Library>> updateLibrary(LibraryDto libraryDto) {
		// TODO Auto-generated method stub
		Library library=modelMapper.map(libraryDto, Library.class);
		if(libraryRepository.existsById(library.getLibraryId())) {
			libraryRepository.save(library);
			apiResponse.setMessage("Library "+library.getLibraryId()+" Updated Successfully!");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
		}
		else {
//			apiResponse.setMessage("Library "+library.getLibraryId()+" Not Exists");
//			apiResponse.setData(library);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.BAD_REQUEST);
			throw new LibraryIdNotExistException("Library "+library.getLibraryId()+" not Exists!");
		}
	}

	
	@Override
	public ResponseEntity<ApiResponse<Library>> deleteLibrary(int libraryId) {
		// TODO Auto-generated method stub
		if(libraryRepository.existsById(libraryId)) {
			libraryRepository.deleteById(libraryId);
			apiResponse.setMessage("Library Deleted Successfully!");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
		}
		else {
//			apiResponse.setMessage("Library Not Exists");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.BAD_REQUEST);
			throw new LibraryIdNotExistException("Library Not Exists");
		}
	}

	
	@Override
	public List<Library> fetchAllId() {
		// TODO Auto-generated method stub
		return libraryRepository.findAll();
	}


	@Override
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int bookId) {
		// TODO Auto-generated method stub
		Optional<Library> optionalLibrary=libraryRepository.findById(libraryId);
		Optional<Book> optionalBook=bookRepository.findById(bookId);
		//Validate both
		if(optionalLibrary.isPresent() && optionalBook.isPresent()) {
			Library library=optionalLibrary.get();
			Book book=optionalBook.get();
			
			//fetch list of books from library
			List<Book> bookList=library.getBookList();
			
			if(bookList!=null) {
				//new list
				bookList=new ArrayList<>();
			}
			//add book object to the list 
			bookList.add(book);
			
			//set list to library
			library.setBookList(bookList);
			
			//update library
			libraryRepository.save(library);
			apiResponse.setMessage("Library Saved Successfully!");
			apiResponse.setData(library);
			apiResponse.setStatusCode(HttpStatus.CREATED.value());// 200 ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.CREATED);
			
		}
		else {
			throw new BookUnableToAddToLibraryException("Library Id / Book Id not Available!");
		}
	}
	
	
	
	

}
