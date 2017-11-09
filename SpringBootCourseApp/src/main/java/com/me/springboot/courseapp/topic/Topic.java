package com.me.springboot.courseapp.topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
	@Id
	@Min(value=100,message="This should be lessthan 100")
	private Integer id;
	private String name;
	private String description;

}
