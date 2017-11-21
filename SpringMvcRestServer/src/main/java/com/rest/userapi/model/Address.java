package com.rest.userapi.model;

import lombok.Data;

@Data
public class Address {
	
	private Geo geo;

    private String zipcode;

    private String street;

    private String suite;

    private String city;
}
