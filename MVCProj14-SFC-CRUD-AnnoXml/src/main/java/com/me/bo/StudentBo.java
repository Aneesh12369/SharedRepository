package com.me.bo;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentBo implements Serializable{
	private Integer id;
	private String name;
	private String address;
	private Integer age;
}
