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

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.service.IBookService;
import com.lms.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	IBookService bookService;
	
	@Operation(operationId="Create Book",summary="Adding Book",
			description="This rest endpoint is used to create and add one Book")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="201",
			description="creates and returns Book entity")})
	@PostMapping
	public ResponseEntity<ApiResponse<Book>> saveBook(@RequestBody BookDto bookDto) {
		return bookService.saveBook(bookDto);
	}
	
	
	@Operation(operationId="fetchBook",summary="Fetching One Book",
			description="This rest endpoint is used to fetch one Book by using Book Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="302",
			description="fetching Book using Book Id and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Book is not found for this Id")})
	@GetMapping("/{bookid}")
	public ResponseEntity<ApiResponse<Book>> findBookById(@PathVariable int bookId){
		return bookService.findBookById(bookId);
	}
	
	
	@Operation(operationId="UpdateBook",summary="Update Book",
			description="This rest endpoint is used to update Book")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Updated and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="400",
			description="Book not exist")})
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<Book>> updateBook(@RequestBody BookDto bookDto){
		return bookService.updateBook(bookDto);
	}
	
	
	@Operation(operationId="DeleteBook",summary="Delete One Book",
			description="This rest endpoint is used to delete one Book by using Book Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="delete Book using Book Id and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Book is not found for this Id")})
	@DeleteMapping("/{bookId}")
	public ResponseEntity<ApiResponse<Book>> deleteBook(@PathVariable int bookId){
		return bookService.deleteBook(bookId);
	}
	
	@Operation(operationId="FetchAllBook",summary="FetchAll Book",
			description="This rest endpoint is used to FetchAll Books")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Fetched All Books Successfully")})
	@GetMapping("/fetchAll")
	public List<Book> fetchAllId(){
		return bookService.fetchAllId();
	}
	
	
	@Operation(operationId="DisplayBooksFromLibrary",summary="Display via Library",
			description="This rest endpoint is used to display books from library by using library id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="display book from library  and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Library Id is not Exist")})
	@GetMapping("library/{libraryId}")
	public ResponseEntity<ApiResponse<List<Book>>> displayBooksFromLibrary(@PathVariable int libraryId) {
		return bookService.displayBooksFromLibrary(libraryId);
	}
	
	
	@Operation(operationId="DisplayBooksByAuthor",summary="Display via Author",
			description="This rest endpoint is used to display books from library")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="display books By Author and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Author name is Not Exist")})
	@GetMapping("author/{author}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(@PathVariable String author) {
		return bookService.displayBookByAuthor(author);
	}
	
	
	
	@Operation(operationId="DisplayBooksByTitle",summary="Display via Title",
			description="This rest endpoint is used to display books By Title ")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="display books By Title and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Title is not Found")})
	@GetMapping("title/{title}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(@PathVariable String title) {
		return bookService.displayBookByTitle(title);
	}
	
	
	@Operation(operationId="DisplayBooksByAuthorAndTitle",summary="Display via Title & Author",
			description="This rest endpoint is used to display books By Author and Title")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="display books By Author/Title and returns Book entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Author/Title is Not Exist")})
	@GetMapping("/{author}/{title}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title) {
		return bookService.displayBookByAuthorAndTitle(author, title);
	}
}
