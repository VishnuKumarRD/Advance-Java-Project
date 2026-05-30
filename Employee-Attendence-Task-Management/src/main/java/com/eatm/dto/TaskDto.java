package com.eatm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
	private int taskId;
	private String taskName;
	private int duration;
}
