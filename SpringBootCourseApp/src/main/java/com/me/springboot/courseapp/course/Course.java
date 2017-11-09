package com.me.springboot.courseapp.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.me.springboot.courseapp.topic.Topic;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Course {
	@Id
	@Min(value=100,message="This should be lessthan 100")
	private Integer id;
	private String name;
	private String description;
	@ManyToOne
	private Topic topic;

}
