package com.rest.userapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value={"errorCode","description","data"})
public class ResponseBean<T> {
	
	private Integer errorCode;
	private String description;
//	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value="data")
	private T t;
	

}
