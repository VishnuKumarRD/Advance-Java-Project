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

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.IAddressService;
import com.lms.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
//common url
@RequestMapping("/address")
public class AddressController {
	@Autowired
	IAddressService addressService;
	
	
	@Operation(operationId="CreateAddress",summary="Adding Address",
				description="This rest endpoint is used to create and add one address")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="201",
				description="creates and returns address entity")})
	@PostMapping
	public ResponseEntity<ApiResponse<Address>> saveAddress(@RequestBody AddressDto addressDto) {
		return addressService.saveAddress(addressDto);
	}
	
	
	@Operation(operationId="fetchAddress",summary="Fetching One Address",
			description="This rest endpoint is used to fetch one address by using address Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="302",
			description="fetching address using address Id and returns address entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Address is not found for this Id")})
	@GetMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Address>> findAddressById(@PathVariable int addressId) {
		return addressService.findAddressById(addressId);
	}
	
	
	@Operation(operationId="UpdateAddress",summary="Update Address",
			description="This rest endpoint is used to update address")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Updated and returns address entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="400",
			description="address not exist")})
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<Address>> updateAddress(@RequestBody AddressDto addressDto) {
		return addressService.updateAddress(addressDto);
	}
	
	
	@Operation(operationId="DeleteAddress",summary="Delete One Address",
			description="This rest endpoint is used to delete one address by using address Id")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="delete address using address Id and returns address entity"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",
			description="Address is not found for this Id")})
	@DeleteMapping("/id/{addressId}")
	public ResponseEntity<ApiResponse<Address>> deleteAddress(@PathVariable int addressId) {
		return addressService.deleteAddress(addressId);
	}
	
	
	@Operation(operationId="FetchAllAddress",summary="FetchAll Address",
			description="This rest endpoint is used to FetchAll Addresses")
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",
			description="Fetched All Addresses Successfully")})
	@GetMapping("/fetchAll")
	public List<Address> fetchAllId(){
		return addressService.fetchAllId();
	}
	
	
}
