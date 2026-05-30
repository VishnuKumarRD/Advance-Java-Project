package com.eatm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	private int employeeId;
	private String employeeName;
	
	private String email;
	
	private String password;
	
	private long phoneNumber;
	private double salary;
	private String role;
}
