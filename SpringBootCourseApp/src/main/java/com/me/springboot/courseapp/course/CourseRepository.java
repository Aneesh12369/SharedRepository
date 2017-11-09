package com.me.springboot.courseapp.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface CourseRepository extends CrudRepository<Course, Integer>{
	
	public List<Course> findByTopicId(Integer id);

}
