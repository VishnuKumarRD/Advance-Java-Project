package com.lms.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.exception.AddressIdNotExistException;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.service.IAddressService;
import com.lms.util.ApiResponse;

@Service
public class AddressService implements IAddressService{
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AddressRepository addressRepository;
	
	ApiResponse<Address> apiResponse=new ApiResponse<>();
	@Override
	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto) {
		Address address=modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);
		apiResponse.setMessage("Address Saved Successfully!");
		apiResponse.setData(address);
		apiResponse.setStatusCode(HttpStatus.CREATED.value());// 200 ok
		return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId) {
		Optional<Address> optional=addressRepository.findById(addressId);
		if(optional.isPresent()) {
			Address address=optional.get();
			apiResponse.setMessage("Address "+address.getAddressId()+" found Successfully!");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.FOUND.value());// 200 ok
			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.FOUND);
		}
		else {
//			apiResponse.setMessage("Address Not Found !");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());// 400 bad gateway
//			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.NOT_FOUND);
			
			throw new AddressIdNotFoundException("Invalid Address Id");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> updateAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		Address address=modelMapper.map(addressDto, Address.class);
		if(addressRepository.existsById(address.getAddressId())) {
			addressRepository.save(address);
			apiResponse.setMessage("Address "+address.getAddressId()+" Updated Successfully!");
			apiResponse.setData(address);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.OK);
		}
		else {
//			apiResponse.setMessage("Address "+address.getAddressId()+" Not Exists");
//			apiResponse.setData(address);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.BAD_REQUEST);
			
			throw new AddressIdNotExistException("Address "+address.getAddressId()+" Not Exists!");
			
			
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Address>> deleteAddress(int addressId) {
		// TODO Auto-generated method stub
		if(addressRepository.existsById(addressId)) {
			addressRepository.deleteById(addressId);
			apiResponse.setMessage("Address Deleted Successfully!");
			apiResponse.setData(null);
			apiResponse.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.OK);
		}
		else {
//			apiResponse.setMessage("Address Not Exists");
//			apiResponse.setData(null);
//			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.BAD_REQUEST);
			
			throw new AddressIdNotExistException("Address Not Exist!");
		}
	}

	@Override
	public List<Address> fetchAllId() {
		// TODO Auto-generated method stub
			return addressRepository.findAll();	
	}
}