package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.util.ApiResponse;

public interface IAddressService {
	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto);
	
	public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId);
	
	public ResponseEntity<ApiResponse<Address>> updateAddress(AddressDto addressDto);
	
	public ResponseEntity<ApiResponse<Address>> deleteAddress(int addressId);
	
	List<Address> fetchAllId();
}
