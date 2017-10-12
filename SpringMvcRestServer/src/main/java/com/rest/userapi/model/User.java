package com.rest.userapi.model;

import lombok.Data;

@Data
public class User {
	

	    private String id;

	    private String phone;

	    private String username;

	    private String website;

	    private Address address;

	    private String email;

	    private Company company;

	    private String name;

}
