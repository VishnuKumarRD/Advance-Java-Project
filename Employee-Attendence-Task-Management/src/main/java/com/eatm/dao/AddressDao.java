package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Address;


@Repository
public class AddressDao {
	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;
	
	public Address saveAddress(Address address) {
		entityTransaction.begin();
		entityManager.persist(address);
		entityTransaction.commit();
		return address;
	}
	
	public Address updateAddress(Address address) {
		entityTransaction.begin();
		entityManager.merge(address);
		entityTransaction.commit();
		return address;
	}
	
	public Address deleteAddress(Address address) {
		entityTransaction.begin();
		entityManager.remove(address);
		entityTransaction.commit();
		return address;
	}
	
	public Address findAddressById(int addressId) {
		return entityManager.find(Address.class,addressId);
	}
	
	public List<Address> findAllAddress(){
		Query query=entityManager.createQuery("select a from Address a");
		return query.getResultList();
	}
}
