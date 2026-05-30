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
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="task_id_generator")
	@SequenceGenerator(name="task_id_generator",initialValue=101,allocationSize=1)
	private int taskId;
	private String taskName;
	private int duration;
	private Status status;
}
