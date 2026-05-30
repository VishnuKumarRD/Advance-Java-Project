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

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.service.ILibraryService;
import com.lms.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/library")
public class LibraryController {
	@Autowired
	ILibraryService libraryService;
	
	@Operation(operationId="CreateLibrary",summary="Adding Library",
			description="This rest endpoint is used to create and add one Library")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="201",
			description="creates and returns Library entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Invalid Address Id")})
	@PostMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Library>> saveLibrary(@RequestBody LibraryDto libraryDto,@PathVariable int addressId) {
		return libraryService.saveLibrary(libraryDto, addressId);
	}
	
	
	@Operation(operationId="fetchLibrary",summary="Fetching One Library",
			description="This rest endpoint is used to fetch one Library by using Library Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="302",
			description="fetching Library using Library Id and returns Library entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Library is not found for this Id")})
	@GetMapping("/{libraryId}")
	public ResponseEntity<ApiResponse<Library>> findLibraryById(@PathVariable int libraryId) {
		return libraryService.findLibraryById(libraryId);
	}
	
	
	@Operation(operationId="UpdateLibrary",summary="Update Library",
			description="This rest endpoint is used to update Library")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Updated and returns Library entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="400",
			description="Library not exist")})
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<Library>> updateLibrary(@RequestBody LibraryDto libraryDto) {
		return libraryService.updateLibrary(libraryDto);
	}
	
	
	@Operation(operationId="DeleteLibrary",summary="Delete One Library",
			description="This rest endpoint is used to delete one Library by using Library Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="delete Library using Library Id and returns Library entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Library is not found for this Id")})
	@DeleteMapping("/{libraryId}")
	public ResponseEntity<ApiResponse<Library>> deleteLibrary(@PathVariable int libraryId) {
		return libraryService.deleteLibrary(libraryId);
	}
	
	
	@Operation(operationId="FetchAllLibrary",summary="FetchAll Library",
			description="This rest endpoint is used to FetchAll Libraries")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Fetched All Libraries Successfully")})
	@GetMapping("/fetchAll")
	public List<Library> fetchAllId() {
		return libraryService.fetchAllId();
	}
	
	
	
	@Operation(operationId="AddBook",summary="Add Book to Library",
			description="This rest endpoint is used to add Book to library by using library Id and Book Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="302",
			description="Add book to library and return library Entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="library/book is not found for this Id")})
	@PutMapping("/{libraryId}/{bookId}")
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(@PathVariable int libraryId,@PathVariable  int bookId) {
		return libraryService.addBookToLibrary(libraryId, bookId);
	}
}
