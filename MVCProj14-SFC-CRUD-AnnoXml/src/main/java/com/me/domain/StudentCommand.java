package com.me.domain;

import lombok.Data;

@Data

public class StudentCommand {
	public StudentCommand(){
		System.out.println("studentCOmmand constructor...........");
	}
	private Integer id;
	private String name;
	private String address;
	private Integer age;
}
