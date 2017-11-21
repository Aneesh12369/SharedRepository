package com.rest.userapi.model;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class User {
	
		
		@Min(value=150,message="should be > 150")
	    private String id;

	    private String phone;

	    private String username;

	    private String website;

	    private Address address;

	    private String email;

	    private Company company;

	    private String name;

}
