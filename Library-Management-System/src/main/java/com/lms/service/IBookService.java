package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.util.ApiResponse;

public interface IBookService {
	public ResponseEntity<ApiResponse<Book>> saveBook(BookDto bookDto);
	
	public ResponseEntity<ApiResponse<Book>> findBookById(int bookId);
	
	public ResponseEntity<ApiResponse<Book>> updateBook(BookDto bookDto);
	
	public ResponseEntity<ApiResponse<Book>> deleteBook(int bookId);
	
	List<Book> fetchAllId();
	
	
	//Filtering part
	public ResponseEntity<ApiResponse<List<Book>>> displayBooksFromLibrary(int libraryId);
	
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author);
	
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(String title);
	
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(String author,String title);
}
