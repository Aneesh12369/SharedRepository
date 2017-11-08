package com.me.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentDto implements Serializable{
	private Integer id;
	private String name;
	private String address;
	private Integer age;
}
