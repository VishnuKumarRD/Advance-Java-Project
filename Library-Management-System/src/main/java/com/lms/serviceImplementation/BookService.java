package com.lms.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.BookIdNotExistException;
import com.lms.exception.BookIdNotFoundException;
import com.lms.exception.LibraryIdNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.IBookService;
import com.lms.util.ApiResponse;

@Service
public class BookService implements IBookService{

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	LibraryRepository libraryRepository;
	ApiResponse<Book> apiResponse=new ApiResponse<>();
	@Override
	public ResponseEntity<ApiResponse<Book>> saveBook(BookDto bookDto) {
		// TODO Auto-generated method stub
		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
		//		book.setReturnTime(LocalDateTime.now());
		apiResponse.setMessage("Book Saved Successfully!");
		apiResponse.setData(book);
		apiResponse.setStatusCode(HttpStatus.CREATED.value());// 200 ok
		return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> findBookById(int bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optional=bookRepository.findById(bookId);
		if(optional.isPresent()) {
			Book book=optional.get();
			apiResponse.setMessage("Book "+book.getBookId()+" found Successfully!");
			apiResponse.setData(book);
			apiResponse.setStatusCode(HttpStatus.FOUND.value());// 200 ok
			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.FOUND);
		}
		else {
			//			apiResponse.setMessage("Book Not Found !");
			//			apiResponse.setData(null);
			//			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());// 400 bad gateway
			//			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.NOT_FOUND);

			throw new BookIdNotFoundException("Book Not Found !");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> updateBook(BookDto bookDto) {
		// TODO Auto-generated method stub
		Book book=modelMapper.map(bookDto, Book.class);
		if(bookRepository.existsById(book.getBookId())) {
			bookRepository.save(book);
			apiResponse.setMessage("Book "+book.getBookId()+" Updated Successfully!");
			apiResponse.setData(book);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.OK);
		}
		else {
			//			apiResponse.setMessage("Book "+book.getBookId()+" Not in Stock !");
			//			apiResponse.setData(book);
			//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			//			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.BAD_REQUEST);
			throw new BookIdNotExistException("Book "+book.getBookId()+" Not InStock !") ;
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> deleteBook(int bookId) {
		// TODO Auto-generated method stub
		if(bookRepository.existsById(bookId)) {
			bookRepository.deleteById(bookId);
			apiResponse.setMessage("Book Deleted Successfully!");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.OK);
		}
		else {
			//			apiResponse.setMessage("Book Not Exists");
			//			apiResponse.setData(null);
			//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			//			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.BAD_REQUEST);
			throw new BookIdNotExistException("Book not InStock !");
		}
	}

	@Override
	public List<Book> fetchAllId() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> displayBooksFromLibrary(int libraryId) {
		// TODO Auto-generated method stub
		Optional<Library> optionalLibrary=libraryRepository.findById(libraryId);
		ApiResponse<List<Book>> apiResponseList=new ApiResponse<>();
		if(optionalLibrary.isPresent()) {
			Library library=optionalLibrary.get();
			List<Book> bookList=library.getBookList();
			if(bookList.isEmpty()) {
				throw new BookIdNotFoundException("No Books Present in this Library");
			}
			else {
				apiResponseList.setMessage("Book Details Based on Library id : "+library.getLibraryId());	
				apiResponseList.setData(bookList);
				apiResponseList.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ApiResponse<List<Book>>>(apiResponseList,HttpStatus.OK);
			}
		}
		else {
			throw new LibraryIdNotFoundException("Library id is not Found!");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Book> bookList=bookRepository.findByAuthor(author);
		//entity booklist has to be converted into book response list
		//create a list for BookResponse
		List<BookResponse> bookResponseList=new ArrayList<>();
		for(Book book:bookList) {
			bookResponseList.add(modelMapper.map(book,BookResponse.class));
		}
		
		ApiResponse<List<BookResponse>> apiResponseList=new ApiResponse<>();
		if(bookList.isEmpty()) {
			throw new BookIdNotFoundException("No Books Present in this Library");
		}
		else {
			apiResponseList.setMessage("BookList Details Based on Author : "+author+" fetched Successfully!");	
			apiResponseList.setData(bookResponseList);
			apiResponseList.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponseList,HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByTitle(String title) {
		// TODO Auto-generated method stub
		List<Book> bookList=bookRepository.findByTitle(title);
		List<BookResponse> bookResponseList=new ArrayList<>();
		for(Book book:bookList) {
			bookResponseList.add(modelMapper.map(book,BookResponse.class));
		}
		
		ApiResponse<List<BookResponse>> apiResponseList=new ApiResponse<>();
		if(bookList.isEmpty()) {
			throw new BookIdNotFoundException("No Books Present in this Book title");
		}
		else {
			apiResponseList.setMessage("BookList Details Based on title : "+title+" fetched Successfully!");	
			apiResponseList.setData(bookResponseList);
			apiResponseList.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponseList,HttpStatus.OK);
		}
		
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthorAndTitle(String author, String title) {
		// TODO Auto-generated method stub
		List<Book> bookList=bookRepository.findByAuthorAndTitle(author, title);
		List<BookResponse> bookResponseList=new ArrayList<>();
		for(Book book:bookList) {
			bookResponseList.add(modelMapper.map(book, BookResponse.class));
		}
		
		ApiResponse<List<BookResponse>> apiResponseList=new ApiResponse<>();
		if(bookList.isEmpty()) {
			throw new BookIdNotFoundException("No Books Present in this Book title");
		}
		else {
			apiResponseList.setMessage("BookList Details Based on Book Title : "+title+" and Author : "+author+" fetched Successfully!");	
			apiResponseList.setData(bookResponseList);
			apiResponseList.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponseList,HttpStatus.OK);
		}
	}







}
