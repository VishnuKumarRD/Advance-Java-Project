package com.eatm.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendenceDto {
	private int attendenceId;
	private LocalDateTime loginTime;
	private LocalDateTime logoutTime;
}
