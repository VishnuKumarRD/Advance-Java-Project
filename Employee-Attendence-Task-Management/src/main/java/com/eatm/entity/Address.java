package com.eatm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="address_id_generator")
	@SequenceGenerator(name="address_id_generator",initialValue=301,allocationSize=1)
	private int addressId;
	private int houseNumber;
	private String street;
	private String city;
	private String state;
	private String country;
	private long pincode;
	
}
