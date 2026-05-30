package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.lms.dto.LibraryDto;

import com.lms.entity.Library;
import com.lms.util.ApiResponse;

public interface ILibraryService {
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto,int addressId);
	
	public ResponseEntity<ApiResponse<Library>> findLibraryById(int libraryId);
	
	public ResponseEntity<ApiResponse<Library>> updateLibrary(LibraryDto libraryDto);
	
	public ResponseEntity<ApiResponse<Library>> deleteLibrary(int libraryId);
	
	List<Library> fetchAllId();
	
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId,int bookId);
}
