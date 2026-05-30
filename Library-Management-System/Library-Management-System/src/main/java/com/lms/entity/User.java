package com.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="user_details")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int userId;
	private String userName;
	private long phoneNumber;
	private String email;
	
	@OneToOne
	private Address address;
}
